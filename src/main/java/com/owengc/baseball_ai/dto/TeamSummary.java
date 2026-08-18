package com.owengc.baseball_ai.dto;

public record TeamSummary(
        String teamId,
        Integer yearId,
        String name,
        Integer wins,
        Integer losses,
        Integer rank,
        String divWin,
        String wcWin,
        String lgWin,
        String wsWin
) {}