package com.owengc.baseball_ai.service;

import com.opencsv.CSVReaderHeaderAware;
import com.owengc.baseball_ai.entity.Fielding;
import com.owengc.baseball_ai.entity.FieldingId;
import com.owengc.baseball_ai.repository.FieldingRepository;
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
public class FieldingLoaderService {

    private static final Logger log = LoggerFactory.getLogger(FieldingLoaderService.class);
    private static final String CSV_PATH = "data/lahman/Fielding.csv";

    private final FieldingRepository fieldingRepository;

    public FieldingLoaderService(FieldingRepository fieldingRepository) {
        this.fieldingRepository = fieldingRepository;
    }

    public void loadFielding() {
        log.info("Starting fielding load from {}", CSV_PATH);
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
                    Fielding fielding = new Fielding();

                    String playerId = row.get("playerID");
                    Integer yearId = parseInt(row.get("yearID"));
                    Integer stint = parseInt(row.get("stint"));
                    String teamId = row.get("teamID");
                    String position = row.get("POS");
                    FieldingId id = new FieldingId(playerId, yearId, stint, teamId, position);
                    fielding.setId(id);

                    fielding.setLeagueId(row.get("lgID"));
                    fielding.setG(parseInt(row.get("G")));
                    fielding.setGs(parseInt(row.get("GS")));
                    fielding.setInnOuts(parseInt(row.get("InnOuts")));
                    fielding.setPo(parseInt(row.get("PO")));
                    fielding.setA(parseInt(row.get("A")));
                    fielding.setE(parseInt(row.get("E")));
                    fielding.setDp(parseInt(row.get("DP")));
                    fielding.setPb(parseInt(row.get("PB")));
                    fielding.setWp(parseInt(row.get("WP")));
                    fielding.setSb(parseInt(row.get("SB")));
                    fielding.setCs(parseInt(row.get("CS")));
                    fielding.setZr(parseInt(row.get("ZR")));

                    fieldingRepository.save(fielding);
                    count++;

                    if (count % 1000 == 0) {
                        log.info("Loaded {} fielding rows so far", count);
                    }
                } catch (Exception e) {
                    errors++;
                    log.error("Failed to load row {}: {}", count + errors, e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to open CSV file: {}", e.getMessage(), e);
        }

        log.info("Fielding load complete. Loaded {} rows, {} errors", count, errors);
    }

    private Integer parseInt(String s) {
        if (s == null || s.isEmpty()) return null;
        return Integer.parseInt(s);
    }
}