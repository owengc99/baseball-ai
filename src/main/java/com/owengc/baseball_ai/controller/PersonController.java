package com.owengc.baseball_ai.controller;

import com.owengc.baseball_ai.dto.*;
import com.owengc.baseball_ai.service.BattingService;
import com.owengc.baseball_ai.service.FieldingService;
import com.owengc.baseball_ai.service.PersonService;
import com.owengc.baseball_ai.service.PitchingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/people", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    private final PersonService personService;
    private final BattingService battingService;
    private final PitchingService pitchingService;
    private final FieldingService fieldingService;

    public PersonController(PersonService personService,
                            BattingService battingService,
                            PitchingService pitchingService,
                            FieldingService fieldingService
    ) {
        this.personService = personService;
        this.battingService = battingService;
        this.pitchingService = pitchingService;
        this.fieldingService = fieldingService;
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

    @GetMapping("/{playerId}/pitching")
    public List<PitchingSeason> getPitchingSeasons(@PathVariable String playerId) {
        return pitchingService.getPitchingSeasons(playerId);
    }

    @GetMapping("/{playerId}/fielding")
    public List<FieldingSeason> getFieldingSeasons(@PathVariable String playerId) {
        return fieldingService.getFieldingSeasons(playerId);
    }
}