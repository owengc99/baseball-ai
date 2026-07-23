package com.owengc.baseball_ai.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Entity
@Table(name = "people")
public class Person {

    public Person() {}

    @Id
    @Column(name = "player_id", length = 10)
    private String playerId;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "birth_month")
    private Integer birthMonth;

    @Column(name = "birth_day")
    private Integer birthDay;

    @Column(name = "birth_city", length = 100)
    private String birthCity;

    @Column(name = "birth_country", length = 50)
    private String birthCountry;

    @Column(name = "birth_state", length = 50)
    private String birthState;

    @Column(name = "death_year")
    private Integer deathYear;

    @Column(name = "death_month")
    private Integer deathMonth;

    @Column(name = "death_day")
    private Integer deathDay;

    @Column(name = "death_country", length = 50)
    private String deathCountry;

    @Column(name = "death_state", length = 50)
    private String deathState;

    @Column(name = "death_city", length = 100)
    private String deathCity;

    @Column(name = "name_first", length = 50)
    private String nameFirst;

    @Column(name = "name_last", length = 50)
    private String nameLast;

    @Column(name = "name_given", length = 100)
    private String nameGiven;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "height")
    private Integer height;

    @Column(name = "bats")
    @JdbcTypeCode(SqlTypes.CHAR)
    private String bats;

    @Column(name = "throws")
    @JdbcTypeCode(SqlTypes.CHAR)
    private String throwsHand;

    @Column(name = "debut")
    private LocalDate debut;

    @Column(name = "final_game")
    private LocalDate finalGame;

    @Column(name = "bbref_id", length = 20)
    private String bbrefId;

    @Column(name = "retro_id", length = 20)
    private String retroId;


    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public Integer getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Integer birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getBirthState() {
        return birthState;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public Integer getDeathMonth() {
        return deathMonth;
    }

    public void setDeathMonth(Integer deathMonth) {
        this.deathMonth = deathMonth;
    }

    public Integer getDeathDay() {
        return deathDay;
    }

    public void setDeathDay(Integer deathDay) {
        this.deathDay = deathDay;
    }

    public String getDeathCountry() {
        return deathCountry;
    }

    public void setDeathCountry(String deathCountry) {
        this.deathCountry = deathCountry;
    }

    public String getDeathState() {
        return deathState;
    }

    public void setDeathState(String deathState) {
        this.deathState = deathState;
    }

    public String getDeathCity() {
        return deathCity;
    }

    public void setDeathCity(String deathCity) {
        this.deathCity = deathCity;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getNameGiven() {
        return nameGiven;
    }

    public void setNameGiven(String nameGiven) {
        this.nameGiven = nameGiven;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getBats() {
        return bats;
    }

    public void setBats(String bats) {
        this.bats = bats;
    }

    public String getThrowsHand() {
        return throwsHand;
    }

    public void setThrowsHand(String throwsHand) {
        this.throwsHand = throwsHand;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFinalGame() {
        return finalGame;
    }

    public void setFinalGame(LocalDate finalGame) {
        this.finalGame = finalGame;
    }

    public String getBbrefId() {
        return bbrefId;
    }

    public void setBbrefId(String bbrefId) {
        this.bbrefId = bbrefId;
    }

    public String getRetroId() {
        return retroId;
    }

    public void setRetroId(String retroId) {
        this.retroId = retroId;
    }
}
