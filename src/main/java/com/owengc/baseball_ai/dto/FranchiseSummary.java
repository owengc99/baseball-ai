package com.owengc.baseball_ai.dto;

public record FranchiseSummary(
        String teamId,
        String name,
        Integer firstYear,
        Integer lastYear,
        Long seasons
) {}