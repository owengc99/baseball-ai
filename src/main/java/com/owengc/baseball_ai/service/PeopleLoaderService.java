package com.owengc.baseball_ai.service;

import com.opencsv.CSVReaderHeaderAware;
import com.owengc.baseball_ai.entity.Person;
import com.owengc.baseball_ai.repository.PersonRepository;
import org.apache.commons.io.input.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Map;

@Service
public class PeopleLoaderService {

    private static final Logger log = LoggerFactory.getLogger(PeopleLoaderService.class);
    private static final String CSV_PATH = "data/lahman/People.csv";

    private final PersonRepository personRepository;

    public PeopleLoaderService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void loadPeople() {
        log.info("Starting people load from {}", CSV_PATH);
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
                    Person person = new Person();
                    person.setPlayerId(row.get("playerID"));
                    person.setBirthYear(parseInt(row.get("birthYear")));
                    person.setBirthMonth(parseInt(row.get("birthMonth")));
                    person.setBirthDay(parseInt(row.get("birthDay")));
                    person.setBirthCity(row.get("birthCity"));
                    person.setBirthCountry(row.get("birthCountry"));
                    person.setBirthState(row.get("birthState"));
                    person.setDeathYear(parseInt(row.get("deathYear")));
                    person.setDeathMonth(parseInt(row.get("deathMonth")));
                    person.setDeathDay(parseInt(row.get("deathDay")));
                    person.setDeathCountry(row.get("deathCountry"));
                    person.setDeathState(row.get("deathState"));
                    person.setDeathCity(row.get("deathCity"));
                    person.setNameFirst(row.get("nameFirst"));
                    person.setNameLast(row.get("nameLast"));
                    person.setNameGiven(row.get("nameGiven"));
                    person.setWeight(parseInt(row.get("weight")));
                    person.setHeight(parseInt(row.get("height")));
                    person.setBats(row.get("bats"));
                    person.setThrowsHand(row.get("throws"));
                    person.setDebut(parseDate(row.get("debut")));
                    person.setFinalGame(parseDate(row.get("finalGame")));
                    person.setBbrefId(row.get("bbrefID"));
                    person.setRetroId(row.get("retroID"));

                    personRepository.save(person);
                    count++;

                    if (count % 1000 == 0) {
                        log.info("Loaded {} people so far", count);
                    }
                } catch (Exception e) {
                    errors++;
                    log.error("Failed to load row {}: {}", count + errors, e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to open CSV file: {}", e.getMessage(), e);
        }

        log.info("People load complete. Loaded {} rows, {} errors", count, errors);
    }

    private Integer parseInt(String s) {
        if (s == null || s.isEmpty()) return null;
        return Integer.parseInt(s);
    }

    private LocalDate parseDate(String s) {
        if (s == null || s.isEmpty()) return null;
        return LocalDate.parse(s);
    }
}