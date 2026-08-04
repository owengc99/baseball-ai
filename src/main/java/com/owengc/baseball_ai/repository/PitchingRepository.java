package com.owengc.baseball_ai.repository;

import com.owengc.baseball_ai.entity.Pitching;
import com.owengc.baseball_ai.entity.PitchingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitchingRepository extends JpaRepository<Pitching, PitchingId> {
}