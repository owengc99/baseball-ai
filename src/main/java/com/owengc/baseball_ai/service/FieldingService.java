package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.dto.FieldingSeason;
import com.owengc.baseball_ai.entity.Fielding;
import com.owengc.baseball_ai.repository.FieldingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.owengc.baseball_ai.util.StringUtils.blankToNull;

@Service
public class FieldingService {

    private final FieldingRepository fieldingRepository;

    public FieldingService(FieldingRepository fieldingRepository) {
        this.fieldingRepository = fieldingRepository;
    }

    public List<FieldingSeason> getFieldingSeasons(String playerId) {
        List<Fielding> rows = fieldingRepository
                .findByIdPlayerIdOrderByIdYearIdAscIdStintAscIdPositionAsc(playerId);

        // A player can appear at several positions per team, and for several teams per year
        Map<Integer, List<Fielding>> seasons = rows.stream()
                .collect(Collectors.groupingBy(
                        row -> row.getId().getYearId(),
                        TreeMap::new,
                        Collectors.toList()
                ));

        return seasons.entrySet().stream()
                .map(season -> new FieldingSeason(
                        season.getKey(),
                        season.getValue().stream().map(this::toPositionStint).toList()
                ))
                .toList();
    }

    private FieldingSeason.PositionStint toPositionStint(Fielding row) {
        return new FieldingSeason.PositionStint(
                blankToNull(row.getId().getPosition()),
                row.getId().getStint(),
                row.getId().getTeamId(),
                blankToNull(row.getLeagueId()),
                row.getG(),
                row.getGs(),
                row.getInnOuts(),
                row.getPo(),
                row.getA(),
                row.getE(),
                row.getDp(),
                fieldingPercentage(row),
                row.getPb(),
                row.getWp(),
                row.getSb(),
                row.getCs(),
                row.getZr()
        );
    }

    /** (PO + A) / (PO + A + E) — total chances handled cleanly. */
    private BigDecimal fieldingPercentage(Fielding row) {
        if (row.getPo() == null || row.getA() == null || row.getE() == null) return null;

        int successful = row.getPo() + row.getA();
        int chances = successful + row.getE();
        if (chances == 0) return null;

        return BigDecimal.valueOf(successful)
                .divide(BigDecimal.valueOf(chances), 3, RoundingMode.HALF_UP);
    }
}