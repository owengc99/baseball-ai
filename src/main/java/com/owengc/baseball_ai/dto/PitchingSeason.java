package com.owengc.baseball_ai.dto;

import java.math.BigDecimal;
import java.util.List;

public record PitchingSeason(
        Integer yearId,
        Totals totals,
        List<Stint> stints
) {
    public record Totals(
            Integer wins,
            Integer losses,
            Integer games,
            Integer gamesStarted,
            Integer completeGames,
            Integer shutouts,
            Integer saves,
            Integer outsPitched,
            Integer hitsAllowed,
            Integer earnedRuns,
            Integer homeRunsAllowed,
            Integer walksAllowed,
            Integer strikeouts,
            BigDecimal baopp,
            BigDecimal era,
            Integer intentionalWalks,
            Integer wildPitches,
            Integer hitBatters,
            Integer balks,
            Integer battersFaced,
            Integer gamesFinished,
            Integer runsAllowed,
            Integer sacrificeHits,
            Integer sacrificeFlies,
            Integer groundedIntoDoublePlay
    ) {}

    public record Stint(
            Integer stint,
            String teamId,
            String leagueId,
            Integer wins,
            Integer losses,
            Integer games,
            Integer gamesStarted,
            Integer completeGames,
            Integer shutouts,
            Integer saves,
            Integer outsPitched,
            Integer hitsAllowed,
            Integer earnedRuns,
            Integer homeRunsAllowed,
            Integer walksAllowed,
            Integer strikeouts,
            BigDecimal baopp,
            BigDecimal era,
            Integer intentionalWalks,
            Integer wildPitches,
            Integer hitBatters,
            Integer balks,
            Integer battersFaced,
            Integer gamesFinished,
            Integer runsAllowed,
            Integer sacrificeHits,
            Integer sacrificeFlies,
            Integer groundedIntoDoublePlay
    ) {}
}