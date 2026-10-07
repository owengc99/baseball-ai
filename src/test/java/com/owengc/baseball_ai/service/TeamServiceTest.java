package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.dto.TeamDetail;
import com.owengc.baseball_ai.entity.Team;
import com.owengc.baseball_ai.entity.TeamYearId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TeamServiceTest {

    private final TeamService service = new TeamService(null);

    /**
     * The 1927 Yankees. Every field is positional in toDetail and every stat is an
     * Integer, so a transposed pair compiles cleanly and reports the wrong number
     * forever. These assertions are the only thing that would catch it.
     */
    private Team yankees1927() {
        Team t = new Team();
        t.setId(new TeamYearId("NYA", 1927));
        t.setLeagueId("AL");
        t.setFranchId("NYY");
        t.setDivId(" ");
        t.setRank(1);
        t.setG(155);
        t.setGHome(77);
        t.setW(110);
        t.setL(44);
        t.setDivWin(" ");
        t.setWcWin(" ");
        t.setLgWin("Y");
        t.setWsWin("Y");
        t.setR(975);
        t.setAb(5347);
        t.setH(1644);
        t.setDoubles(291);
        t.setTriples(103);
        t.setHr(158);
        t.setBb(635);
        t.setSo(605);
        t.setSb(90);
        t.setCs(64);
        t.setHbp(null);
        t.setSf(null);
        t.setRa(599);
        t.setEr(494);
        t.setEra(new BigDecimal("3.20"));
        t.setCg(82);
        t.setSho(11);
        t.setSv(20);
        t.setIpOuts(4167);
        t.setHa(1403);
        t.setHra(42);
        t.setBba(409);
        t.setSoa(431);
        t.setE(196);
        t.setDp(123);
        t.setFp(new BigDecimal("0.969"));
        t.setName("New York Yankees");
        t.setPark("Yankee Stadium I");
        t.setAttendance(1164015);
        t.setBpf(98);
        t.setPpf(94);
        return t;
    }

    @Test
    void mapsIdentityAndName() {
        TeamDetail detail = service.toDetail(yankees1927());

        assertEquals("NYA", detail.teamId());
        assertEquals(1927, detail.yearId());
        assertEquals("New York Yankees", detail.name());
        assertEquals("AL", detail.identity().leagueId());
        assertEquals("NYY", detail.identity().franchiseId());
    }

    @Test
    void mapsRecord() {
        TeamDetail.Record record = service.toDetail(yankees1927()).record();

        assertEquals(155, record.games());
        assertEquals(110, record.wins());
        assertEquals(44, record.losses());
        assertEquals(1, record.rank());
        assertEquals("Y", record.leagueWin());
        assertEquals("Y", record.worldSeriesWin());
    }

    @Test
    void mapsBattingWithoutTransposingColumns() {
        TeamDetail.Batting batting = service.toDetail(yankees1927()).batting();

        assertEquals(975, batting.runs());
        assertEquals(1644, batting.hits());
        assertEquals(158, batting.homeRuns());
        assertEquals(291, batting.doubles());
        assertEquals(103, batting.triples());
        assertEquals(90, batting.stolenBases());
        assertEquals(64, batting.caughtStealing());
    }

    @Test
    void mapsPitchingAllowedColumnsNotOffensiveOnes() {
        // The trap: hr is home runs hit, hra is home runs allowed. Same type,
        // adjacent in the entity, opposite meaning.
        TeamDetail.Pitching pitching = service.toDetail(yankees1927()).pitching();

        assertEquals(599, pitching.runsAllowed());
        assertEquals(1403, pitching.hitsAllowed());
        assertEquals(42, pitching.homeRunsAllowed());
        assertEquals(409, pitching.walksAllowed());
        assertEquals(431, pitching.strikeoutsThrown());
        assertEquals(new BigDecimal("3.20"), pitching.era());
    }

    @Test
    void blanksBecomeNullInCharColumns() {
        TeamDetail detail = service.toDetail(yankees1927());

        // No divisions in 1927, so Lahman stores a blank-padded CHAR(1).
        assertNull(detail.identity().divisionId());
        assertNull(detail.record().divisionWin());
        assertNull(detail.record().wildCardWin());
    }
}