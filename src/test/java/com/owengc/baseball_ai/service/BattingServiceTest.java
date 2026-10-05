package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.entity.Batting;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BattingServiceTest {

    private final BattingService service = new BattingService(null);

    private Batting stintWithHomeRuns(Integer hr) {
        Batting b = new Batting();
        b.setHr(hr);
        return b;
    }

    @Test
    void sumsASingleStint() {
        assertEquals(20, service.sum(List.of(stintWithHomeRuns(20)), Batting::getHr));
    }

    @Test
    void sumsAcrossStints() {
        List<Batting> stints = List.of(stintWithHomeRuns(20), stintWithHomeRuns(17));
        assertEquals(37, service.sum(stints, Batting::getHr));
    }

    @Test
    void sumsKnownValuesWhenOneStintIsNull() {
        // 20 known steals plus an unrecorded half-season is 20, not null — losing a
        // real number is worse than reporting a partial one.
        List<Batting> stints = List.of(stintWithHomeRuns(20), stintWithHomeRuns(null));
        assertEquals(20, service.sum(stints, Batting::getHr));
    }

    @Test
    void returnsNullWhenEveryStintIsNull() {
        // Nothing was recorded, so there's nothing to report. Returning 0 would assert
        // a measurement nobody made — the pre-1954 sacrifice fly case.
        List<Batting> stints = List.of(stintWithHomeRuns(null), stintWithHomeRuns(null));
        assertNull(service.sum(stints, Batting::getHr));
    }
}