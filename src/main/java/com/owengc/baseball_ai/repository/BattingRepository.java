package com.owengc.baseball_ai.repository;

import com.owengc.baseball_ai.entity.Batting;
import com.owengc.baseball_ai.entity.BattingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattingRepository extends JpaRepository<Batting, BattingId> {
}