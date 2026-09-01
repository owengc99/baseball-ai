package com.owengc.baseball_ai.controller;

import com.owengc.baseball_ai.dto.LeaderboardEntry;
import com.owengc.baseball_ai.enums.BattingStat;
import com.owengc.baseball_ai.enums.Span;
import com.owengc.baseball_ai.service.LeaderboardService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/leaderboards", produces = MediaType.APPLICATION_JSON_VALUE)
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/batting/{stat}")
    public List<LeaderboardEntry> getBattingLeaders(
            @PathVariable BattingStat stat,
            @RequestParam(defaultValue = "CAREER") Span span,
            @RequestParam(defaultValue = "10") int limit) {
        return leaderboardService.getBattingLeaders(stat, span, limit);
    }
}