package com.owengc.baseball_ai.dto;

import java.math.BigDecimal;
import java.util.List;

public record FieldingSeason(
        Integer yearId,
        List<PositionStint> positions
) {
    public record PositionStint(
            String position,
            Integer stint,
            String teamId,
            String leagueId,
            Integer games,
            Integer gamesStarted,
            Integer outsPlayed,
            Integer putouts,
            Integer assists,
            Integer errors,
            Integer doublePlays,
            BigDecimal fieldingPercentage,
            Integer passedBalls,
            Integer wildPitches,
            Integer stolenBasesAllowed,
            Integer caughtStealing,
            Integer zoneRating
    ) {}
}