package com.owengc.baseball_ai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;

@Entity
@Table(name="teams")
public class Team {

    @EmbeddedId
    private TeamYearId id;


    @Column(name = "league_id", length = 5)
    private String leagueId;

    @Column(name = "franch_id", length = 3)
    private String franchId;

    @Column(name = "div_id")
    @JdbcTypeCode(SqlTypes.CHAR)
    private String divId;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "g")
    private Integer g;

    @Column(name = "g_home")
    private Integer gHome;

    @Column(name = "w")
    private Integer w;

    @Column(name = "l")
    private Integer l;

    @Column(name = "div_win")
    @JdbcTypeCode(SqlTypes.CHAR)
    private String divWin;

    @Column(name = "wc_win")
    @JdbcTypeCode(SqlTypes.CHAR)
    private String wcWin;

    @Column(name = "lg_win")
    @JdbcTypeCode(SqlTypes.CHAR)
    private String lgWin;

    @Column(name = "ws_win")
    @JdbcTypeCode(SqlTypes.CHAR)
    private String wsWin;

    @Column(name = "r")
    private Integer r;

    @Column(name = "ab")
    private Integer ab;

    @Column(name = "h")
    private Integer h;

    @Column(name = "doubles")
    private Integer doubles;

    @Column(name = "triples")
    private Integer triples;

    @Column(name = "hr")
    private Integer hr;

    @Column(name = "bb")
    private Integer bb;

    @Column(name = "so")
    private Integer so;

    @Column(name = "sb")
    private Integer sb;

    @Column(name = "cs")
    private Integer cs;

    @Column(name = "hbp")
    private Integer hbp;

    @Column(name = "sf")
    private Integer sf;

    @Column(name = "ra")
    private Integer ra;

    @Column(name = "er")
    private Integer er;

    @Column(name = "era")
    private BigDecimal era;

    @Column(name = "cg")
    private Integer cg;

    @Column(name = "sho")
    private Integer sho;

    @Column(name = "sv")
    private Integer sv;

    @Column(name = "ip_outs")
    private Integer ipOuts;

    @Column(name = "ha")
    private Integer ha;

    @Column(name = "hra")
    private Integer hra;

    @Column(name = "bba")
    private Integer bba;

    @Column(name = "soa")
    private Integer soa;

    @Column(name = "e")
    private Integer e;

    @Column(name = "dp")
    private Integer dp;

    @Column(name = "fp")
    private BigDecimal fp;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "park", length = 100)
    private String park;

    @Column(name = "attendance")
    private Integer attendance;

    @Column(name = "bpf")
    private Integer bpf;

    @Column(name = "ppf")
    private Integer ppf;

    @Column(name = "team_id_br", length = 10)
    private String teamIdBr;

    @Column(name = "team_id_lahman45", length = 10)
    private String teamIdLahman45;

    @Column(name = "team_id_retro", length = 10)
    private String teamIdRetro;

    public Team() {}

    public TeamYearId getId() {
        return id;
    }

    public void setId(TeamYearId id) {
        this.id = id;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getFranchId() {
        return franchId;
    }

    public void setFranchId(String franchId) {
        this.franchId = franchId;
    }

    public String getDivId() {
        return divId;
    }

    public void setDivId(String divId) {
        this.divId = divId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getGHome() {
        return gHome;
    }

    public void setGHome(Integer gHome) {
        this.gHome = gHome;
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

    public String getDivWin() {
        return divWin;
    }

    public void setDivWin(String divWin) {
        this.divWin = divWin;
    }

    public String getWcWin() {
        return wcWin;
    }

    public void setWcWin(String wcWin) {
        this.wcWin = wcWin;
    }

    public String getLgWin() {
        return lgWin;
    }

    public void setLgWin(String lgWin) {
        this.lgWin = lgWin;
    }

    public String getWsWin() {
        return wsWin;
    }

    public void setWsWin(String wsWin) {
        this.wsWin = wsWin;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getAb() {
        return ab;
    }

    public void setAb(Integer ab) {
        this.ab = ab;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Integer getDoubles() {
        return doubles;
    }

    public void setDoubles(Integer doubles) {
        this.doubles = doubles;
    }

    public Integer getTriples() {
        return triples;
    }

    public void setTriples(Integer triples) {
        this.triples = triples;
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

    public Integer getHbp() {
        return hbp;
    }

    public void setHbp(Integer hbp) {
        this.hbp = hbp;
    }

    public Integer getSf() {
        return sf;
    }

    public void setSf(Integer sf) {
        this.sf = sf;
    }

    public Integer getRa() {
        return ra;
    }

    public void setRa(Integer ra) {
        this.ra = ra;
    }

    public Integer getEr() {
        return er;
    }

    public void setEr(Integer er) {
        this.er = er;
    }

    public BigDecimal getEra() {
        return era;
    }

    public void setEra(BigDecimal era) {
        this.era = era;
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

    public Integer getHa() {
        return ha;
    }

    public void setHa(Integer ha) {
        this.ha = ha;
    }

    public Integer getHra() {
        return hra;
    }

    public void setHra(Integer hra) {
        this.hra = hra;
    }

    public Integer getBba() {
        return bba;
    }

    public void setBba(Integer bba) {
        this.bba = bba;
    }

    public Integer getSoa() {
        return soa;
    }

    public void setSoa(Integer soa) {
        this.soa = soa;
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

    public BigDecimal getFp() {
        return fp;
    }

    public void setFp(BigDecimal fp) {
        this.fp = fp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Integer getBpf() {
        return bpf;
    }

    public void setBpf(Integer bpf) {
        this.bpf = bpf;
    }

    public Integer getPpf() {
        return ppf;
    }

    public void setPpf(Integer ppf) {
        this.ppf = ppf;
    }

    public String getTeamIdBr() {
        return teamIdBr;
    }

    public void setTeamIdBr(String teamIdBr) {
        this.teamIdBr = teamIdBr;
    }

    public String getTeamIdLahman45() {
        return teamIdLahman45;
    }

    public void setTeamIdLahman45(String teamIdLahman45) {
        this.teamIdLahman45 = teamIdLahman45;
    }

    public String getTeamIdRetro() {
        return teamIdRetro;
    }

    public void setTeamIdRetro(String teamIdRetro) {
        this.teamIdRetro = teamIdRetro;
    }
}
