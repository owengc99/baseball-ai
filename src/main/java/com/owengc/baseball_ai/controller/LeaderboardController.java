package com.owengc.baseball_ai.controller;

import com.owengc.baseball_ai.dto.LeaderboardEntry;
import com.owengc.baseball_ai.enums.BattingStat;
import com.owengc.baseball_ai.enums.Span;
import com.owengc.baseball_ai.service.LeaderboardService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Leaderboards", description = "Statistical leaders, career or single-season")
@RestController
@RequestMapping(value = "/api/leaderboards", produces = MediaType.APPLICATION_JSON_VALUE)
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @Operation(summary = "Batting leaders for a statistic",
            description = "Ties are preserved — a limit of 10 may return more rows if "
                    + "players tie at the boundary. Limit is capped at 100.")
    @GetMapping("/batting/{stat}")
    public List<LeaderboardEntry> getBattingLeaders(
            @PathVariable BattingStat stat,
            @RequestParam(defaultValue = "CAREER") Span span,
            @RequestParam(defaultValue = "10") int limit) {
        return leaderboardService.getBattingLeaders(stat, span, limit);
    }
}