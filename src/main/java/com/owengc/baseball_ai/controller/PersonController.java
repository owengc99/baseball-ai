package com.owengc.baseball_ai.controller;

import com.owengc.baseball_ai.dto.BattingSeason;
import com.owengc.baseball_ai.dto.PersonDetail;
import com.owengc.baseball_ai.dto.PersonSummary;
import com.owengc.baseball_ai.service.BattingService;
import com.owengc.baseball_ai.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService personService;
    private final BattingService battingService;

    public PersonController(PersonService personService, BattingService battingService) {
        this.personService = personService;
        this.battingService = battingService;
    }

    @GetMapping
    public List<PersonSummary> searchPlayers(@RequestParam String lastName) {
        return personService.searchByLastName(lastName);
    }

    @GetMapping("/{playerId}")
    public PersonDetail getPerson(@PathVariable String playerId) {
        return personService.getById(playerId);
    }

    @GetMapping("/{playerId}/batting")
    public List<BattingSeason> getBattingSeasons(@PathVariable String playerId) {
        return battingService.getBattingSeasons(playerId);
    }
}