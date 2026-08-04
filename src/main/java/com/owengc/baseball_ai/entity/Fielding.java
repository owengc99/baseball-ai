package com.owengc.baseball_ai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fielding")
public class Fielding {

    @EmbeddedId
    private FieldingId id;

    @Column(name = "league_id", length = 5)
    private String leagueId;

    @Column(name = "g")
    private Integer g;

    @Column(name = "gs")
    private Integer gs;

    @Column(name = "inn_outs")
    private Integer innOuts;

    @Column(name = "po")
    private Integer po;

    @Column(name = "a")
    private Integer a;

    @Column(name = "e")
    private Integer e;

    @Column(name = "dp")
    private Integer dp;

    @Column(name = "pb")
    private Integer pb;

    @Column(name = "wp")
    private Integer wp;

    @Column(name = "sb")
    private Integer sb;

    @Column(name = "cs")
    private Integer cs;

    @Column(name = "zr")
    private Integer zr;

    public Fielding() {}

    public FieldingId getId() {
        return id;
    }

    public void setId(FieldingId id) {
        this.id = id;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getGs() {
        return gs;
    }

    public void setGs(Integer gs) {
        this.gs = gs;
    }

    public Integer getInnOuts() {
        return innOuts;
    }

    public void setInnOuts(Integer innOuts) {
        this.innOuts = innOuts;
    }

    public Integer getPo() {
        return po;
    }

    public void setPo(Integer po) {
        this.po = po;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getE() {
        return e;
    }

    public void setE(Integer e) {
        this.e = e;
    }

    public Integer getDp() {
        return dp;
    }

    public void setDp(Integer dp) {
        this.dp = dp;
    }

    public Integer getPb() {
        return pb;
    }

    public void setPb(Integer pb) {
        this.pb = pb;
    }

    public Integer getWp() {
        return wp;
    }

    public void setWp(Integer wp) {
        this.wp = wp;
    }

    public Integer getSb() {
        return sb;
    }

    public void setSb(Integer sb) {
        this.sb = sb;
    }

    public Integer getCs() {
        return cs;
    }

    public void setCs(Integer cs) {
        this.cs = cs;
    }

    public Integer getZr() {
        return zr;
    }

    public void setZr(Integer zr) {
        this.zr = zr;
    }
}