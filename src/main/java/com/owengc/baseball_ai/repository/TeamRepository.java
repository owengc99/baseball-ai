package com.owengc.baseball_ai.repository;

import com.owengc.baseball_ai.dto.FranchiseSummary;
import com.owengc.baseball_ai.entity.Team;
import com.owengc.baseball_ai.entity.TeamYearId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, TeamYearId> {
    List<Team> findByIdTeamIdOrderByIdYearId(String teamId);

    @Query("""
        SELECT new com.owengc.baseball_ai.dto.FranchiseSummary(
            t.id.teamId,
            MAX(t.name),
            MIN(t.id.yearId),
            MAX(t.id.yearId),
            COUNT(t)
        )
        FROM Team t
        GROUP BY t.id.teamId
        ORDER BY t.id.teamId
        """)
    List<FranchiseSummary> findFranchises();
}
