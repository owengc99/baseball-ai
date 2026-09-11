package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.entity.Pitching;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PitchingServiceTest {

    private final PitchingService service = new PitchingService(null);

    private Pitching stint(Integer er, Integer ipOuts) {
        Pitching p = new Pitching();
        p.setEr(er);
        p.setIpOuts(ipOuts);
        return p;
    }

    @Test
    void computesEraFromSingleStint() {
        // 60 earned runs over 200 innings (600 outs) = 2.70
        assertEquals(new BigDecimal("2.70"), service.era(List.of(stint(60, 600))));
    }

    @Test
    void weightsEraByInningsAcrossStints() {
        // Randy Johnson 1998: 77 ER / 480 outs for SEA, 12 ER / 253 outs for HOU.
        // Combined is 3.28. Averaging the two stint ERAs (4.33, 1.28) gives 2.81 —
        // this test exists to catch that mistake.
        List<Pitching> stints = List.of(stint(77, 480), stint(12, 253));
        assertEquals(new BigDecimal("3.28"), service.era(stints));
    }

    @Test
    void returnsNullEraWhenNoOutsRecorded() {
        assertNull(service.era(List.of(stint(5, 0))));
    }

    @Test
    void returnsNullEraWhenEarnedRunsMissing() {
        assertNull(service.era(List.of(stint(null, 600))));
    }

    private Pitching baoppStint(Integer h, Integer bfp, Integer bb,
                                Integer hbp, Integer sh, Integer sf,
                                BigDecimal stored) {
        Pitching p = new Pitching();
        p.setH(h);
        p.setBfp(bfp);
        p.setBb(bb);
        p.setHbp(hbp);
        p.setSh(sh);
        p.setSf(sf);
        p.setBaopp(stored);
        return p;
    }

    @Test
    void passesThroughStoredBaoppForSingleStint() {
        // Lahman's stored value uses an AB count we can't reconstruct, so a single
        // stint reports theirs rather than recomputing.
        Pitching only = baoppStint(100, 459, 44, 1, 0, 5, new BigDecimal("0.244"));
        assertEquals(new BigDecimal("0.244"), service.baopp(List.of(only)));
    }

    @Test
    void computesBaoppAcrossStintsWhenComponentsPresent() {
        // AB faced = BFP - BB - HBP - SH - SF, summed across stints.
        List<Pitching> stints = List.of(
                baoppStint(100, 459, 44, 1, 0, 5, null),
                baoppStint(50, 230, 22, 1, 0, 2, null)
        );
        assertEquals(new BigDecimal("0.244"), service.baopp(stints));
    }

    @Test
    void returnsNullBaoppWhenAStintIsMissingComponents() {
        // Pre-1954 rows have no SF. Substituting zero would inflate the denominator
        // and depress the result, so the whole season reports null instead.
        List<Pitching> stints = List.of(
                baoppStint(100, 459, 44, 1, 0, 5, null),
                baoppStint(50, 230, 22, 1, null, null, null)
        );
        assertNull(service.baopp(stints));
    }


}