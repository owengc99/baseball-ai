package com.owengc.baseball_ai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "pitching")
public class Pitching {

    @EmbeddedId
    private PitchingId id;

    @Column(name = "league_id", length = 5)
    private String leagueId;

    @Column(name = "w")
    private Integer w;

    @Column(name = "l")
    private Integer l;

    @Column(name = "g")
    private Integer g;

    @Column(name = "gs")
    private Integer gs;

    @Column(name = "cg")
    private Integer cg;

    @Column(name = "sho")
    private Integer sho;

    @Column(name = "sv")
    private Integer sv;

    @Column(name = "ip_outs")
    private Integer ipOuts;

    @Column(name = "h")
    private Integer h;

    @Column(name = "er")
    private Integer er;

    @Column(name = "hr")
    private Integer hr;

    @Column(name = "bb")
    private Integer bb;

    @Column(name = "so")
    private Integer so;

    @Column(name = "baopp")
    private BigDecimal baopp;

    @Column(name = "era")
    private BigDecimal era;

    @Column(name = "ibb")
    private Integer ibb;

    @Column(name = "wp")
    private Integer wp;

    @Column(name = "hbp")
    private Integer hbp;

    @Column(name = "bk")
    private Integer bk;

    @Column(name = "bfp")
    private Integer bfp;

    @Column(name = "gf")
    private Integer gf;

    @Column(name = "r")
    private Integer r;

    @Column(name = "sh")
    private Integer sh;

    @Column(name = "sf")
    private Integer sf;

    @Column(name = "gidp")
    private Integer gidp;

    public Pitching() {}

    public PitchingId getId() {
        return id;
    }

    public void setId(PitchingId id) {
        this.id = id;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
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

    public Integer getCg() {
        return cg;
    }

    public void setCg(Integer cg) {
        this.cg = cg;
    }

    public Integer getSho() {
        return sho;
    }

    public void setSho(Integer sho) {
        this.sho = sho;
    }

    public Integer getSv() {
        return sv;
    }

    public void setSv(Integer sv) {
        this.sv = sv;
    }

    public Integer getIpOuts() {
        return ipOuts;
    }

    public void setIpOuts(Integer ipOuts) {
        this.ipOuts = ipOuts;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Integer getEr() {
        return er;
    }

    public void setEr(Integer er) {
        this.er = er;
    }

    public Integer getHr() {
        return hr;
    }

    public void setHr(Integer hr) {
        this.hr = hr;
    }

    public Integer getBb() {
        return bb;
    }

    public void setBb(Integer bb) {
        this.bb = bb;
    }

    public Integer getSo() {
        return so;
    }

    public void setSo(Integer so) {
        this.so = so;
    }

    public BigDecimal getBaopp() {
        return baopp;
    }

    public void setBaopp(BigDecimal baopp) {
        this.baopp = baopp;
    }

    public BigDecimal getEra() {
        return era;
    }

    public void setEra(BigDecimal era) {
        this.era = era;
    }

    public Integer getIbb() {
        return ibb;
    }

    public void setIbb(Integer ibb) {
        this.ibb = ibb;
    }

    public Integer getWp() {
        return wp;
    }

    public void setWp(Integer wp) {
        this.wp = wp;
    }

    public Integer getHbp() {
        return hbp;
    }

    public void setHbp(Integer hbp) {
        this.hbp = hbp;
    }

    public Integer getBk() {
        return bk;
    }

    public void setBk(Integer bk) {
        this.bk = bk;
    }

    public Integer getBfp() {
        return bfp;
    }

    public void setBfp(Integer bfp) {
        this.bfp = bfp;
    }

    public Integer getGf() {
        return gf;
    }

    public void setGf(Integer gf) {
        this.gf = gf;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getSh() {
        return sh;
    }

    public void setSh(Integer sh) {
        this.sh = sh;
    }

    public Integer getSf() {
        return sf;
    }

    public void setSf(Integer sf) {
        this.sf = sf;
    }

    public Integer getGidp() {
        return gidp;
    }

    public void setGidp(Integer gidp) {
        this.gidp = gidp;
    }
}