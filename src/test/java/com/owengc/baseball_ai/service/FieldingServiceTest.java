package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.entity.Fielding;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FieldingServiceTest {

    private final FieldingService service = new FieldingService(null);

    private Fielding row(Integer po, Integer a, Integer e) {
        Fielding f = new Fielding();
        f.setPo(po);
        f.setA(a);
        f.setE(e);
        return f;
    }

    @Test
    void computesFromPutoutsAssistsAndErrors() {
        // Zobrist 2006 at shortstop: (86 + 148) / (86 + 148 + 9) = .963
        assertEquals(new BigDecimal("0.963"), service.fieldingPercentage(row(86, 148, 9)));
    }

    @Test
    void isPerfectWhenNoErrors() {
        assertEquals(new BigDecimal("1.000"), service.fieldingPercentage(row(61, 103, 0)));
    }

    @Test
    void returnsNullWhenThereWereNoChances() {
        // A position appeared at but never fielded a ball — 0/0 is undefined, not 1.000.
        assertNull(service.fieldingPercentage(row(0, 0, 0)));
    }

    @Test
    void returnsNullWhenAComponentIsMissing() {
        assertNull(service.fieldingPercentage(row(86, null, 9)));
    }
}