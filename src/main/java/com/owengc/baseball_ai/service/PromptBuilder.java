package com.owengc.baseball_ai.service;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    private final SchemaProvider schemaProvider;

    public PromptBuilder(SchemaProvider schemaProvider) {
        this.schemaProvider = schemaProvider;
    }

    private static final String INSTRUCTIONS = """
            You translate questions about baseball into PostgreSQL queries.

            Rules:
            - Return ONE SQL statement and nothing else. No markdown fences, no explanation, no trailing semicolon commentary.
            - The statement must be a SELECT. Never write INSERT, UPDATE, DELETE, DROP, ALTER, CREATE, GRANT, or any other statement type.
            - Use only the tables and columns listed below. Do not invent names.
            - Always include a LIMIT of at most 100 rows.
            - Include player names (from people) when the answer is about players, not just player_id.
            - If the question cannot be answered from this schema, return exactly: UNANSWERABLE
            """;

    private static final String NOTES = """
            Domain notes:
            - ip_outs (pitching, teams) and inn_outs (fielding) are counts of OUTS, not innings. Innings = outs / 3. ERA = (er * 27) / ip_outs.
            - Columns named doubles and triples hold 2B and 3B (the numeric names aren't valid identifiers).
            - On batting, hr/h/bb/so/r are what the batter did. On pitching, hr/h/bb/r are what the pitcher ALLOWED; so is strikeouts thrown.
            - On teams, unsuffixed offensive columns (r, h, hr, bb, so) are what the team did; the -a suffixed ones (ra, ha, hra, bba, soa) are what it allowed.
            - A player traded mid-season has multiple rows per year, distinguished by stint. Sum across stints for a season total.
            - fielding has one row per position played, so a player can have several rows per team per season.
            - Primary keys are composite: batting/pitching are (player_id, year_id, stint, team_id); fielding adds position; teams is (team_id, year_id).
            - team_id is a season-specific code, not a franchise. Franchise identity is franch_id. Team names change over time, so join teams for the name in a given year.
            - CHAR(1) columns (bats, throws, div_id, div_win, wc_win, lg_win, ws_win) are blank-padded; compare with trim() or use = 'Y'.
            - Many stats are NULL in early eras rather than zero — sf before 1954, cs, ibb, gidp. Don't treat NULL as 0.
            - Player names live only in people; batting/pitching/fielding have player_id only, so join people for names.
            - Data covers 1871 through 2025.
            """;

    public String build(String question) {
        return """
                %s
                Schema:
                %s

                %s
                Question: %s
                """.formatted(INSTRUCTIONS, schemaProvider.getSchema(), NOTES, question);
    }
}