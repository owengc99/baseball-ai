package com.owengc.baseball_ai.repository;

import com.owengc.baseball_ai.entity.Team;
import com.owengc.baseball_ai.entity.TeamId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, TeamId> {
}
