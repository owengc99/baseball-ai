package com.owengc.baseball_ai.dto;

import java.math.BigDecimal;

public record TeamDetail(
        String teamId,
        Integer yearId,
        String name,
        Identity identity,
        Record record,
        Batting batting,
        Pitching pitching,
        Fielding fielding,
        Venue venue
) {
    public record Identity(
            String leagueId,
            String divisionId,
            String franchiseId
    ) {}

    public record Record(
            Integer games,
            Integer homeGames,
            Integer wins,
            Integer losses,
            Integer rank,
            String divisionWin,
            String wildCardWin,
            String leagueWin,
            String worldSeriesWin
    ) {}

    public record Batting(
            Integer runs,
            Integer atBats,
            Integer hits,
            Integer doubles,
            Integer triples,
            Integer homeRuns,
            Integer walks,
            Integer strikeouts,
            Integer stolenBases,
            Integer caughtStealing,
            Integer hitByPitch,
            Integer sacrificeFlies
    ) {}

    public record Pitching(
            Integer runsAllowed,
            Integer earnedRuns,
            BigDecimal era,
            Integer completeGames,
            Integer shutouts,
            Integer saves,
            Integer outsPitched,
            Integer hitsAllowed,
            Integer homeRunsAllowed,
            Integer walksAllowed,
            Integer strikeoutsThrown
    ) {}

    public record Fielding(
            Integer errors,
            Integer doublePlays,
            BigDecimal fieldingPercentage
    ) {}

    public record Venue(
            String park,
            Integer attendance,
            Integer battingParkFactor,
            Integer pitchingParkFactor
    ) {}
}