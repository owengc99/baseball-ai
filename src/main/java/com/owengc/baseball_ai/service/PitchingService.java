package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.dto.PitchingSeason;
import com.owengc.baseball_ai.entity.Pitching;
import com.owengc.baseball_ai.repository.PitchingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.owengc.baseball_ai.util.StringUtils.blankToNull;

@Service
public class PitchingService {

    private final PitchingRepository pitchingRepository;

    public PitchingService(PitchingRepository pitchingRepository) {
        this.pitchingRepository = pitchingRepository;
    }

    public List<PitchingSeason> getPitchingSeasons(String playerId) {
        List<Pitching> rows = pitchingRepository.findByIdPlayerIdOrderByIdYearIdAscIdStintAsc(playerId);

// A traded player has one row per team per season; group them so each year is a single entry
        Map<Integer, List<Pitching>> seasons = rows.stream()
                .collect(Collectors.groupingBy(
                        row -> row.getId().getYearId(),
                        TreeMap::new,
                        Collectors.toList()
                ));

        return seasons.entrySet().stream()
                .map(season -> new PitchingSeason(
                        season.getKey(),
                        toTotals(season.getValue()),
                        season.getValue().stream().map(this::toStint).toList()
                ))
                .toList();
    }

    private PitchingSeason.Totals toTotals(List<Pitching> stints) {
        return new PitchingSeason.Totals(
                sum(stints, Pitching::getW),
                sum(stints, Pitching::getL),
                sum(stints, Pitching::getG),
                sum(stints, Pitching::getGs),
                sum(stints, Pitching::getCg),
                sum(stints, Pitching::getSho),
                sum(stints, Pitching::getSv),
                sum(stints, Pitching::getIpOuts),
                sum(stints, Pitching::getH),
                sum(stints, Pitching::getEr),
                sum(stints, Pitching::getHr),
                sum(stints, Pitching::getBb),
                sum(stints, Pitching::getSo),
                baopp(stints),
                era(stints),
                sum(stints, Pitching::getIbb),
                sum(stints, Pitching::getWp),
                sum(stints, Pitching::getHbp),
                sum(stints, Pitching::getBk),
                sum(stints, Pitching::getBfp),
                sum(stints, Pitching::getGf),
                sum(stints, Pitching::getR),
                sum(stints, Pitching::getSh),
                sum(stints, Pitching::getSf),
                sum(stints, Pitching::getGidp)
        );
    }

    private Integer sum(List<Pitching> stints, Function<Pitching, Integer> field) {
        List<Integer> values = stints.stream()
                .map(field)
                .filter(Objects::nonNull)
                .toList();

        return values.isEmpty() ? null : values.stream().mapToInt(Integer::intValue).sum();
    }

    private PitchingSeason.Stint toStint(Pitching row) {
        return new PitchingSeason.Stint(
                row.getId().getStint(),
                row.getId().getTeamId(),
                blankToNull(row.getLeagueId()),
                row.getW(),
                row.getL(),
                row.getG(),
                row.getGs(),
                row.getCg(),
                row.getSho(),
                row.getSv(),
                row.getIpOuts(),
                row.getH(),
                row.getEr(),
                row.getHr(),
                row.getBb(),
                row.getSo(),
                row.getBaopp(),
                row.getEra(),
                row.getIbb(),
                row.getWp(),
                row.getHbp(),
                row.getBk(),
                row.getBfp(),
                row.getGf(),
                row.getR(),
                row.getSh(),
                row.getSf(),
                row.getGidp()
        );
    }

    private BigDecimal era(List<Pitching> stints) {
        Integer er = sum(stints, Pitching::getEr);
        Integer outs = sum(stints, Pitching::getIpOuts);
        if (er == null || outs == null || outs == 0) return null;

        return BigDecimal.valueOf(er)
                .multiply(BigDecimal.valueOf(27))
                .divide(BigDecimal.valueOf(outs), 2, RoundingMode.HALF_UP);
    }


    /**
     * Single stint: Lahman's stored value, which uses an AB count we can't reconstruct.
     * Multiple stints: computed as H / (BFP - BB - HBP - SH - SF), but only when every
     * stint has all components — substituting zero for missing SH/SF inflates the
     * denominator and depresses the result.
     */
    private BigDecimal baopp(List<Pitching> stints) {
        if (stints.size() == 1) return stints.getFirst().getBaopp();
        boolean complete = stints.stream().allMatch(p ->
                p.getBfp() != null && p.getBb() != null && p.getHbp() != null
                        && p.getSh() != null && p.getSf() != null && p.getH() != null);
        if (!complete) return null;

        int hits = stints.stream().mapToInt(Pitching::getH).sum();
        int atBats = stints.stream()
                .mapToInt(p -> p.getBfp() - p.getBb() - p.getHbp() - p.getSh() - p.getSf())
                .sum();
        if (atBats <= 0) return null;

        return BigDecimal.valueOf(hits)
                .divide(BigDecimal.valueOf(atBats), 3, RoundingMode.HALF_UP);

    }

}