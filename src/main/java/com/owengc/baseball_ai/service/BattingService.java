package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.dto.BattingSeason;
import com.owengc.baseball_ai.entity.Batting;
import com.owengc.baseball_ai.repository.BattingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.owengc.baseball_ai.util.StringUtils.blankToNull;

@Service
public class BattingService {
    private final BattingRepository battingRepository;

    public BattingService(BattingRepository battingRepository) {
        this.battingRepository = battingRepository;
    }

    public List<BattingSeason> getBattingSeasons(String playerId) {
        List<Batting> rows = battingRepository
                .findByIdPlayerIdOrderByIdYearIdAscIdStintAsc(playerId);

        Map<Integer, List<Batting>> byYear = rows.stream()
                .collect(Collectors.groupingBy(
                        row -> row.getId().getYearId(),
                        TreeMap::new,
                        Collectors.toList()
                ));

        return byYear.entrySet().stream()
                .map(entry -> new BattingSeason(
                        entry.getKey(),
                        toTotals(entry.getValue()),
                        entry.getValue().stream().map(this::toStint).toList()
                ))
                .toList();
    }

    private BattingSeason.Totals toTotals(List<Batting> stints) {
        return new BattingSeason.Totals(
                sum(stints, Batting::getG),
                sum(stints, Batting::getAb),
                sum(stints, Batting::getR),
                sum(stints, Batting::getH),
                sum(stints, Batting::getDoubles),
                sum(stints, Batting::getTriples),
                sum(stints, Batting::getHr),
                sum(stints, Batting::getRbi),
                sum(stints, Batting::getSb),
                sum(stints, Batting::getCs),
                sum(stints, Batting::getBb),
                sum(stints, Batting::getSo),
                sum(stints, Batting::getIbb),
                sum(stints, Batting::getHbp),
                sum(stints, Batting::getSh),
                sum(stints, Batting::getSf),
                sum(stints, Batting::getGidp)
        );
    }

    private Integer sum(List<Batting> stints, Function<Batting, Integer> field) {
        List<Integer> values = stints.stream()
                .map(field)
                .filter(Objects::nonNull)
                .toList();

        return values.isEmpty() ? null : values.stream().mapToInt(Integer::intValue).sum();
    }

    private BattingSeason.Stint toStint(Batting row) {
        return new BattingSeason.Stint(
                row.getId().getStint(),
                row.getId().getTeamId(),
                blankToNull(row.getLeagueId()),
                row.getG(),
                row.getAb(),
                row.getR(),
                row.getH(),
                row.getDoubles(),
                row.getTriples(),
                row.getHr(),
                row.getRbi(),
                row.getSb(),
                row.getCs(),
                row.getBb(),
                row.getSo(),
                row.getIbb(),
                row.getHbp(),
                row.getSh(),
                row.getSf(),
                row.getGidp()
        );
    }
}
