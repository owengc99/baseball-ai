package com.owengc.baseball_ai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PitchingId implements Serializable {

    @Column(name = "player_id", length = 10)
    private final String playerId;

    @Column(name = "year_id")
    private final Integer yearId;

    @Column(name = "stint")
    private final Integer stint;

    @Column(name = "team_id", length = 3)
    private final String teamId;

    protected PitchingId() {
        this.playerId = null;
        this.yearId = null;
        this.stint = null;
        this.teamId = null;
    }

    public PitchingId(String playerId, Integer yearId, Integer stint, String teamId) {
        this.playerId = playerId;
        this.yearId = yearId;
        this.stint = stint;
        this.teamId = teamId;
    }

    public String getPlayerId() { return playerId; }
    public Integer getYearId() { return yearId; }
    public Integer getStint() { return stint; }
    public String getTeamId() { return teamId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitchingId that = (PitchingId) o;
        return Objects.equals(playerId, that.playerId)
                && Objects.equals(yearId, that.yearId)
                && Objects.equals(stint, that.stint)
                && Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, yearId, stint, teamId);
    }
}