package com.owengc.baseball_ai.dto;

import java.time.LocalDate;

public record PersonSummary(
        String playerId,
        String nameFirst,
        String nameLast,
        Integer birthYear,
        LocalDate debut,
        LocalDate finalGame
) {}