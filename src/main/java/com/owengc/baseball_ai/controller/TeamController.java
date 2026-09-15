package com.owengc.baseball_ai.controller;

import com.owengc.baseball_ai.dto.FranchiseSummary;
import com.owengc.baseball_ai.dto.TeamDetail;
import com.owengc.baseball_ai.dto.TeamSummary;
import com.owengc.baseball_ai.service.TeamService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Teams", description = "Franchise and team-season data")
@RestController
@RequestMapping(value = "/api/teams", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Operation(summary = "Get every season for a franchise")
    @GetMapping("/{teamId}")
    public List<TeamSummary> getSeasons(@PathVariable String teamId){
       return teamService.getSeasons(teamId);
    }

    @Operation(summary = "Get a single team-season")
    @GetMapping("/{teamId}/{yearId}")
    public TeamDetail getTeam(@PathVariable String teamId, @PathVariable Integer yearId){
       return teamService.getTeam(teamId, yearId);
    }

    @Operation(summary = "List all franchises with year span and season count")
    @GetMapping
    public List<FranchiseSummary> getFranchises() {
        return teamService.getFranchises();
    }
}
