package com.owengc.baseball_ai.service;

import com.opencsv.CSVReaderHeaderAware;
import com.owengc.baseball_ai.entity.Batting;
import com.owengc.baseball_ai.entity.BattingId;
import com.owengc.baseball_ai.repository.BattingRepository;
import org.apache.commons.io.input.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class BattingLoaderService {

    private static final Logger log = LoggerFactory.getLogger(BattingLoaderService.class);
    private static final String CSV_PATH = "data/lahman/Batting.csv";

    private final BattingRepository battingRepository;

    public BattingLoaderService(BattingRepository battingRepository) {
        this.battingRepository = battingRepository;
    }

    public void loadBatting() {
        log.info("Starting batting load from {}", CSV_PATH);
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
                    Batting batting = new Batting();

                    String playerId = row.get("playerID");
                    Integer yearId = parseInt(row.get("yearID"));
                    Integer stint = parseInt(row.get("stint"));
                    String teamId = row.get("teamID");
                    BattingId id = new BattingId(playerId, yearId, stint, teamId);
                    batting.setId(id);

                    batting.setLeagueId(row.get("lgID"));
                    batting.setG(parseInt(row.get("G")));
                    batting.setAb(parseInt(row.get("AB")));
                    batting.setR(parseInt(row.get("R")));
                    batting.setH(parseInt(row.get("H")));
                    batting.setDoubles(parseInt(row.get("2B")));
                    batting.setTriples(parseInt(row.get("3B")));
                    batting.setHr(parseInt(row.get("HR")));
                    batting.setRbi(parseInt(row.get("RBI")));
                    batting.setSb(parseInt(row.get("SB")));
                    batting.setCs(parseInt(row.get("CS")));
                    batting.setBb(parseInt(row.get("BB")));
                    batting.setSo(parseInt(row.get("SO")));
                    batting.setIbb(parseInt(row.get("IBB")));
                    batting.setHbp(parseInt(row.get("HBP")));
                    batting.setSh(parseInt(row.get("SH")));
                    batting.setSf(parseInt(row.get("SF")));
                    batting.setGidp(parseInt(row.get("GIDP")));

                    battingRepository.save(batting);
                    count++;

                    if (count % 1000 == 0) {
                        log.info("Loaded {} batting rows so far", count);
                    }
                } catch (Exception e) {
                    errors++;
                    log.error("Failed to load row {}: {}", count + errors, e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to open CSV file: {}", e.getMessage(), e);
        }

        log.info("Batting load complete. Loaded {} rows, {} errors", count, errors);
    }

    private Integer parseInt(String s) {
        if (s == null || s.isEmpty()) return null;
        return Integer.parseInt(s);
    }
}