package com.owengc.baseball_ai.service;

import com.opencsv.CSVReaderHeaderAware;
import com.owengc.baseball_ai.entity.Pitching;
import com.owengc.baseball_ai.entity.PitchingId;
import com.owengc.baseball_ai.repository.PitchingRepository;
import org.apache.commons.io.input.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class PitchingLoaderService {

    private static final Logger log = LoggerFactory.getLogger(PitchingLoaderService.class);
    private static final String CSV_PATH = "data/lahman/Pitching.csv";

    private final PitchingRepository pitchingRepository;

    public PitchingLoaderService(PitchingRepository pitchingRepository) {
        this.pitchingRepository = pitchingRepository;
    }

    public void loadPitching() {
        log.info("Starting pitching load from {}", CSV_PATH);
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
                    Pitching pitching = new Pitching();

                    String playerId = row.get("playerID");
                    Integer yearId = parseInt(row.get("yearID"));
                    Integer stint = parseInt(row.get("stint"));
                    String teamId = row.get("teamID");
                    PitchingId id = new PitchingId(playerId, yearId, stint, teamId);
                    pitching.setId(id);

                    pitching.setLeagueId(row.get("lgID"));
                    pitching.setW(parseInt(row.get("W")));
                    pitching.setL(parseInt(row.get("L")));
                    pitching.setG(parseInt(row.get("G")));
                    pitching.setGs(parseInt(row.get("GS")));
                    pitching.setCg(parseInt(row.get("CG")));
                    pitching.setSho(parseInt(row.get("SHO")));
                    pitching.setSv(parseInt(row.get("SV")));
                    pitching.setIpOuts(parseInt(row.get("IPouts")));
                    pitching.setH(parseInt(row.get("H")));
                    pitching.setEr(parseInt(row.get("ER")));
                    pitching.setHr(parseInt(row.get("HR")));
                    pitching.setBb(parseInt(row.get("BB")));
                    pitching.setSo(parseInt(row.get("SO")));
                    pitching.setBaopp(parseDecimal(row.get("BAOpp")));
                    pitching.setEra(parseDecimal(row.get("ERA")));
                    pitching.setIbb(parseInt(row.get("IBB")));
                    pitching.setWp(parseInt(row.get("WP")));
                    pitching.setHbp(parseInt(row.get("HBP")));
                    pitching.setBk(parseInt(row.get("BK")));
                    pitching.setBfp(parseInt(row.get("BFP")));
                    pitching.setGf(parseInt(row.get("GF")));
                    pitching.setR(parseInt(row.get("R")));
                    pitching.setSh(parseInt(row.get("SH")));
                    pitching.setSf(parseInt(row.get("SF")));
                    pitching.setGidp(parseInt(row.get("GIDP")));

                    pitchingRepository.save(pitching);
                    count++;

                    if (count % 1000 == 0) {
                        log.info("Loaded {} pitching rows so far", count);
                    }
                } catch (Exception e) {
                    errors++;
                    log.error("Failed to load row {}: {}", count + errors, e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to open CSV file: {}", e.getMessage(), e);
        }

        log.info("Pitching load complete. Loaded {} rows, {} errors", count, errors);
    }

    private Integer parseInt(String s) {
        if (s == null || s.isEmpty()) return null;
        return Integer.parseInt(s);
    }

    private BigDecimal parseDecimal(String s) {
        if (s == null || s.isEmpty()) return null;
        return new BigDecimal(s);
    }
}