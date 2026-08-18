package com.owengc.baseball_ai.dto;

import java.util.List;

public record BattingSeason(
        Integer yearId,
        Totals totals,
        List<Stint> stints
) {
    public record Totals(
            Integer games,
            Integer atBats,
            Integer runs,
            Integer hits,
            Integer doubles,
            Integer triples,
            Integer homeRuns,
            Integer rbi,
            Integer stolenBases,
            Integer caughtStealing,
            Integer walks,
            Integer strikeouts,
            Integer intentionalWalks,
            Integer hitByPitch,
            Integer sacrificeHits,
            Integer sacrificeFlies,
            Integer groundedIntoDoublePlay
    ) {}

    public record Stint(
            Integer stint,
            String teamId,
            String leagueId,
            Integer games,
            Integer atBats,
            Integer runs,
            Integer hits,
            Integer doubles,
            Integer triples,
            Integer homeRuns,
            Integer rbi,
            Integer stolenBases,
            Integer caughtStealing,
            Integer walks,
            Integer strikeouts,
            Integer intentionalWalks,
            Integer hitByPitch,
            Integer sacrificeHits,
            Integer sacrificeFlies,
            Integer groundedIntoDoublePlay
    ) {}
}