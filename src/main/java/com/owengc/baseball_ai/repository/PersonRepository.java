package com.owengc.baseball_ai.repository;

import com.owengc.baseball_ai.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {

    List<Person> findByNameLast(String nameLast);
}
