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

import java.util.List;

@RestController
@RequestMapping(value = "/api/teams", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @GetMapping("/{teamId}")
    public List<TeamSummary> getSeasons(@PathVariable String teamId){
       return teamService.getSeasons(teamId);
    }

    @GetMapping("/{teamId}/{yearId}")
    public TeamDetail getTeam(@PathVariable String teamId, @PathVariable Integer yearId){
       return teamService.getTeam(teamId, yearId);
    }


    @GetMapping
    public List<FranchiseSummary> getFranchises() {
        return teamService.getFranchises();
    }
}
