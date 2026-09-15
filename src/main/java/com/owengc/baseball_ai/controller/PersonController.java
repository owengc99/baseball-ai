package com.owengc.baseball_ai.controller;

import com.owengc.baseball_ai.dto.*;
import com.owengc.baseball_ai.service.BattingService;
import com.owengc.baseball_ai.service.FieldingService;
import com.owengc.baseball_ai.service.PersonService;
import com.owengc.baseball_ai.service.PitchingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping(value = "/api/people", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "People", description = "Player biographical data and career statistics")
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

    @Operation(summary = "Search players by last name")
    @GetMapping
    public List<PersonSummary> searchPlayers(@RequestParam String lastName) {
        return personService.searchByLastName(lastName);
    }

    @Operation(summary = "Get a player's biographical detail")
    @GetMapping("/{playerId}")
    public PersonDetail getPerson(@PathVariable String playerId) {
        return personService.getById(playerId);
    }

    @Operation(summary = "Get a player's batting career",
            description = "One entry per season, with per-stint breakdowns and summed totals. "
                    + "Totals are null only when every stint is null for that stat.")
    @GetMapping("/{playerId}/batting")
    public List<BattingSeason> getBattingSeasons(@PathVariable String playerId) {
        return battingService.getBattingSeasons(playerId);
    }

    @Operation(summary = "Get a player's pitching career",
            description = "ERA in totals is recomputed from summed earned runs and outs "
                    + "rather than averaging stint ERAs. BAOpp is null when a stint "
                    + "lacks the components to compute it.")
    @GetMapping("/{playerId}/pitching")
    public List<PitchingSeason> getPitchingSeasons(@PathVariable String playerId) {
        return pitchingService.getPitchingSeasons(playerId);
    }

    @Operation(summary = "Get a player's fielding career",
            description = "One entry per position per team per season. No season totals — "
                    + "games double-count multi-position appearances.")
    @GetMapping("/{playerId}/fielding")
    public List<FieldingSeason> getFieldingSeasons(@PathVariable String playerId) {
        return fieldingService.getFieldingSeasons(playerId);
    }
}