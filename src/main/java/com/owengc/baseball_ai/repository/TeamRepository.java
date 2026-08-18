package com.owengc.baseball_ai.repository;

import com.owengc.baseball_ai.entity.Team;
import com.owengc.baseball_ai.entity.TeamYearId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, TeamYearId> {
    List<Team> findByIdTeamIdOrderByIdYearId(String teamId);
}
