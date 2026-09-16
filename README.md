# baseball-ai

A REST API over the [Lahman Baseball Database](http://seanlahman.com/) (1871–2025), with a natural language query endpoint that translates plain-English questions into SQL.

## Stack

- Java 21, Spring Boot 4
- PostgreSQL 16, Liquibase for migrations
- Gemini for SQL generation
- OpenAPI docs via springdoc

## Running locally

**Prerequisites:** JDK 21+, PostgreSQL 16, a [Gemini API key](https://aistudio.google.com/).

**1. Create the database and roles**

```sql
CREATE DATABASE baseball_analytics;
CREATE USER baseball_app WITH PASSWORD 'baseball_local_dev';
GRANT ALL PRIVILEGES ON DATABASE baseball_analytics TO baseball_app;

-- Read-only role used by the natural language query endpoint
CREATE USER baseball_readonly WITH PASSWORD 'readonly_local_dev';
GRANT CONNECT ON DATABASE baseball_analytics TO baseball_readonly;
GRANT USAGE ON SCHEMA public TO baseball_readonly;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO baseball_readonly;
```

**2. Set the API key**

```bash
export GEMINI_API_KEY=your-key-here
```

The app fails to start without it. Database passwords default to the values above; override with `DB_PASSWORD` and `READONLY_DB_PASSWORD`.

**3. Run**

```bash
./mvnw spring-boot:run
```

Liquibase creates the schema on first start, but does not load data. Load the Lahman CSVs by passing the loader flags as program arguments:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--load-people,--load-teams,--load-batting,--load-pitching,--load-fielding"
```

Each flag is independent, so re-running with only some of them re-loads just those tables. Interactive docs at `http://localhost:8080/swagger-ui.html`.

## Endpoints

| Path | Description |
|---|---|
| `GET /api/people?lastName=` | Search players by last name |
| `GET /api/people/{playerId}` | Player biographical detail |
| `GET /api/people/{playerId}/batting` | Batting career, by season |
| `GET /api/people/{playerId}/pitching` | Pitching career, by season |
| `GET /api/people/{playerId}/fielding` | Fielding career, by season and position |
| `GET /api/teams` | All franchises |
| `GET /api/teams/{teamId}` | Every season under a team code |
| `GET /api/teams/{teamId}/{yearId}` | A single team-season |
| `GET /api/leaderboards/batting/{stat}` | Batting leaders, career or single-season |
| `POST /api/query` | Natural language question |

## Natural language queries

```bash
curl -X POST localhost:8080/api/query \
  -H "Content-Type: application/json" \
  -d '{"question":"which pitchers had an ERA under 2.00 in a season with at least 200 innings?"}'
```

The response includes the generated SQL alongside the results, so the query is inspectable rather than opaque.

### Guardrails

Running model-generated SQL against a database needs more than asking the model nicely. Four layers, in order of how much they actually protect:

**A read-only database role.** The endpoint uses a separate connection pool authenticated as `baseball_readonly`, which has `SELECT` and nothing else. Prompt injection, a validator gap, or an unanticipated construct can't write, because the connection has no write permission. This is the layer that holds when the others fail.

**Statement validation.** Rejects anything that isn't a single SELECT: chained statements, SQL comments, system catalog access, and a keyword blocklist. Useful for returning a clear error, but it's a blocklist and can't be proven exhaustive — which is why it isn't the primary defense.

**A statement timeout.** Five seconds, set on every connection in the read-only pool. A generated cross join can't tie up the database.

**A row cap.** The generated query is wrapped in an outer `LIMIT 100`. The prompt also asks for a limit, but a prompt is a request rather than a guarantee.

### Prompt construction

The schema half is read from `information_schema` at startup, so it can't drift from the actual database. The semantic half is hand-written — that `ip_outs` counts outs rather than innings, that `hr` on the batting table means home runs hit while on the pitching table it means home runs allowed, that a traded player has multiple rows per season. Without those notes the generated SQL is syntactically valid and quietly wrong.

## Tests

```bash
./mvnw test
```