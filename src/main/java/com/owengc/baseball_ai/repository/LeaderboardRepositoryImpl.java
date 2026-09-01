package com.owengc.baseball_ai.repository;

import com.owengc.baseball_ai.dto.LeaderboardEntry;
import com.owengc.baseball_ai.enums.BattingStat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaderboardRepositoryImpl implements LeaderboardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String CAREER_SQL = """
            SELECT * FROM (
                SELECT RANK() OVER (ORDER BY SUM(b.%1$s) DESC) AS rnk,
                        b.player_id  AS player_id,
                        p.name_first AS name_first,
                        p.name_last  AS name_last,
                        NULL::int    AS year_id,
                        SUM(b.%1$s)  AS total
                FROM batting b
                JOIN people p ON p.player_id = b.player_id
                WHERE b.%1$s IS NOT NULL
                GROUP BY b.player_id, p.name_first, p.name_last
            ) ranked
            WHERE rnk <= :limit
            ORDER BY rnk, name_last, name_first
            """;

    private static final String SEASON_SQL = """
            SELECT * FROM (
                SELECT RANK() OVER (ORDER BY SUM(b.%1$s) DESC) AS rnk,
                       b.player_id  AS player_id,
                       p.name_first AS name_first,
                       p.name_last  AS name_last,
                       b.year_id AS year_id,
                       SUM(b.%1$s)  AS total
                FROM batting b
                JOIN people p ON p.player_id = b.player_id
                WHERE b.%1$s IS NOT NULL
                GROUP BY b.player_id, p.name_first, p.name_last, b.year_id
            ) ranked
            WHERE rnk <= :limit
            ORDER BY rnk, name_last, name_first
            """;


    @Override
    public List<LeaderboardEntry> findBattingCareerLeaders(BattingStat stat, int limit) {
        return execute(CAREER_SQL.formatted(stat.getColumn()), limit);
    }

    @Override
    public List<LeaderboardEntry> findBattingSeasonLeaders(BattingStat stat, int limit) {
        return execute(SEASON_SQL.formatted(stat.getColumn()), limit);
    }

    @SuppressWarnings("unchecked")
    private List<LeaderboardEntry> execute(String sql, int limit) {
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("limit", limit);

        List<Object[]> rows = query.getResultList();
        return rows.stream()
                .map(r -> new LeaderboardEntry(
                        ((Number) r[0]).intValue(),
                        (String) r[1],
                        (String) r[2],
                        (String) r[3],
                        r[4] == null ? null : ((Number) r[4]).intValue(),
                        ((Number) r[5]).longValue()
                ))
                .toList();
    }


}