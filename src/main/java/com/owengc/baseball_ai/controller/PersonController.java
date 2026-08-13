package com.owengc.baseball_ai.controller;

import com.owengc.baseball_ai.dto.PersonSummary;
import com.owengc.baseball_ai.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonSummary> searchPlayers(@RequestParam String lastName) {
        return personService.searchByLastName(lastName);
    }
}