package com.owengc.baseball_ai.repository;

import com.owengc.baseball_ai.entity.Fielding;
import com.owengc.baseball_ai.entity.FieldingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldingRepository extends JpaRepository<Fielding, FieldingId> {
}