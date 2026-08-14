package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.dto.PersonDetail;
import com.owengc.baseball_ai.dto.PersonSummary;
import com.owengc.baseball_ai.entity.Person;
import com.owengc.baseball_ai.exception.PersonNotFoundException;
import com.owengc.baseball_ai.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public PersonDetail getById(String id) {
        return personRepository.findById(id)
                .map(this::toDetail)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private PersonDetail toDetail(Person person) {
        PersonDetail.Death death = person.getDeathYear() == null
                ? null
                : new PersonDetail.Death(
                person.getDeathYear(),
                person.getDeathMonth(),
                person.getDeathDay(),
                person.getDeathCity(),
                person.getDeathState(),
                person.getDeathCountry()
        );

        return new PersonDetail(
                person.getPlayerId(),
                person.getNameFirst(),
                person.getNameLast(),
                person.getNameGiven(),
                new PersonDetail.Birth(
                        person.getBirthYear(),
                        person.getBirthMonth(),
                        person.getBirthDay(),
                        person.getBirthCity(),
                        person.getBirthState(),
                        person.getBirthCountry()
                ),
                death,
                new PersonDetail.Physical(
                        person.getWeight(),
                        person.getHeight(),
                        person.getBats(),
                        person.getThrowsHand()
                ),
                new PersonDetail.Career(
                        person.getDebut(),
                        person.getFinalGame()
                )
        );
    }
}
