package com.owengc.baseball_ai.service;

import com.opencsv.CSVReaderHeaderAware;
import com.owengc.baseball_ai.entity.Team;
import com.owengc.baseball_ai.entity.TeamId;
import com.owengc.baseball_ai.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import org.apache.commons.io.input.BOMInputStream;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Service
public class TeamsLoaderService {

    private static final Logger log = LoggerFactory.getLogger(TeamsLoaderService.class);
    private static final String CSV_PATH = "data/lahman/Teams.csv";

    private final TeamRepository teamRepository;

    public TeamsLoaderService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void loadTeams() {
        log.info("Starting teams load from {}", CSV_PATH);
        int count = 0;
        int errors = 0;

        try (
                InputStream is = new FileInputStream(CSV_PATH);
                BOMInputStream bomStream = BOMInputStream.builder().setInputStream(is).get();
                Reader reader = new InputStreamReader(bomStream, StandardCharsets.UTF_8);
                CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(reader)
        ) {
            Map<String, String> row;
            while ((row = csvReader.readMap()) != null) {
                try {
                    Team team = new Team();

                    Integer yearId = parseInt(row.get("yearID"));
                    String teamId = row.get("teamID");
                    TeamId id = new TeamId(yearId, teamId);
                    team.setId(id);

                    team.setLeagueId(row.get("lgID"));
                    team.setFranchId(row.get("franchID"));
                    team.setDivId(row.get("divID"));
                    team.setRank(parseInt(row.get("Rank")));
                    team.setG(parseInt(row.get("G")));
                    team.setgHome(parseInt(row.get("Ghome")));
                    team.setW(parseInt(row.get("W")));
                    team.setL(parseInt(row.get("L")));
                    team.setDivWin(row.get("DivWin"));
                    team.setWcWin(row.get("WCWin"));
                    team.setLgWin(row.get("LgWin"));
                    team.setWsWin(row.get("WSWin"));
                    team.setR(parseInt(row.get("R")));
                    team.setAb(parseInt(row.get("AB")));
                    team.setH(parseInt(row.get("H")));
                    team.setDoubles(parseInt(row.get("2B")));
                    team.setTriples(parseInt(row.get("3B")));
                    team.setHr(parseInt(row.get("HR")));
                    team.setBb(parseInt(row.get("BB")));
                    team.setSo(parseInt(row.get("SO")));
                    team.setSb(parseInt(row.get("SB")));
                    team.setCs(parseInt(row.get("CS")));
                    team.setHbp(parseInt(row.get("HBP")));
                    team.setSf(parseInt(row.get("SF")));
                    team.setRa(parseInt(row.get("RA")));
                    team.setEr(parseInt(row.get("ER")));
                    team.setEra(parseDecimal(row.get("ERA")));
                    team.setCg(parseInt(row.get("CG")));
                    team.setSho(parseInt(row.get("SHO")));
                    team.setSv(parseInt(row.get("SV")));
                    team.setIpOuts(parseInt(row.get("IPouts")));
                    team.setHa(parseInt(row.get("HA")));
                    team.setHra(parseInt(row.get("HRA")));
                    team.setBba(parseInt(row.get("BBA")));
                    team.setSoa(parseInt(row.get("SOA")));
                    team.setE(parseInt(row.get("E")));
                    team.setDp(parseInt(row.get("DP")));
                    team.setFp(parseDecimal(row.get("FP")));
                    team.setName(row.get("name"));
                    team.setPark(row.get("park"));
                    team.setAttendance(parseInt(row.get("attendance")));
                    team.setBpf(parseInt(row.get("BPF")));
                    team.setPpf(parseInt(row.get("PPF")));
                    team.setTeamIdBr(row.get("teamIDBR"));
                    team.setTeamIdLahman45(row.get("teamIDlahman45"));
                    team.setTeamIdRetro(row.get("teamIDretro"));

                    teamRepository.save(team);
                    count++;

                    if (count % 1000 == 0) {
                        log.info("Loaded {} teams so far", count);
                    }
                } catch (Exception e) {
                    errors++;
                    log.error("Failed to load row {}: {}", count + errors, e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to open CSV file: {}", e.getMessage(), e);
        }

        log.info("Teams load complete. Loaded {} rows, {} errors", count, errors);
    }

    // Helper methods for parsing
    private Integer parseInt(String s) {
        if (s == null || s.isEmpty()) return null;
        return Integer.parseInt(s);
    }

    private BigDecimal parseDecimal(String s) {
        if (s == null || s.isEmpty()) return null;
        return new BigDecimal(s);
    }
}