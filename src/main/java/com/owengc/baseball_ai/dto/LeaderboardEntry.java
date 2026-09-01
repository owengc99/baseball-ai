package com.owengc.baseball_ai.dto;

public record LeaderboardEntry(
        Integer rank,
        String playerId,
        String nameFirst,
        String nameLast,
        Integer yearId, // null for career bc NA
        Long total
) {}
