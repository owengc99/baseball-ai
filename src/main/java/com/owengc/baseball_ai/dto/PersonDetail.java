package com.owengc.baseball_ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record PersonDetail(
        String playerId,
        String nameFirst,
        String nameLast,
        String nameGiven,
        Birth birth,
        Death death,
        Physical physical,
        Career career
) {
    public record Birth(
            Integer year,
            Integer month,
            Integer day,
            String city,
            String state,
            String country
    ) {}

    public record Death(
            Integer year,
            Integer month,
            Integer day,
            String city,
            String state,
            String country
    ) {}

    public record Physical(
            Integer weight,
            Integer height,
            String bats,
            @JsonProperty("throws") String throwsHand
    ) {}

    public record Career(
            LocalDate debut,
            LocalDate finalGame
    ) {}
}