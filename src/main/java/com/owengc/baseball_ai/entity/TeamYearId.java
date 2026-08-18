package com.owengc.baseball_ai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeamYearId implements Serializable {

    @Column(name = "year_id")
    private final Integer yearId;

    @Column(name = "team_id", length = 3)
    private final String teamId;

//    for JPA
    protected TeamYearId() {
        this.yearId = null;
        this.teamId = null;
    }

    public TeamYearId(String teamId, Integer yearId) {
        this.yearId = yearId;
        this.teamId = teamId;
    }

    public Integer getYearId() {
        return yearId;
    }


    public String getTeamId() {
        return teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamYearId teamYearId1 = (TeamYearId) o;
        return Objects.equals(yearId, teamYearId1.yearId) && Objects.equals(teamId, teamYearId1.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearId, teamId);
    }
}
