package com.owengc.baseball_ai.repository;

import com.owengc.baseball_ai.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}
