package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.dto.PersonSummary;
import com.owengc.baseball_ai.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonSummary> searchByLastName(String lastName) {
        return personRepository.findByNameLast(lastName)
                .stream()
                .map(person -> new PersonSummary(
                        person.getPlayerId(),
                        person.getNameFirst(),
                        person.getNameLast(),
                        person.getBirthYear(),
                        person.getDebut(),
                        person.getFinalGame()
                ))
                .toList();
    }
}
