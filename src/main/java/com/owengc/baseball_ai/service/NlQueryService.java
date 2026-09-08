package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.dto.QueryResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NlQueryService {

    private final SqlGenerator sqlGenerator;
    private final EntityManager entityManager;
    private final SqlValidator sqlValidator;

    public NlQueryService(SqlGenerator sqlGenerator,
                          EntityManager entityManager,
                          SqlValidator sqlValidator) {
        this.sqlGenerator = sqlGenerator;
        this.entityManager = entityManager;
        this.sqlValidator = sqlValidator;
    }

    @Transactional(readOnly = true)
    public QueryResult ask(String question) {
        final String sql = sqlGenerator.generateSql(question);

        if ("UNANSWERABLE".equals(sql)) {
            return new QueryResult(sql, List.of(), List.of());
        }

        sqlValidator.validate(sql);

        entityManager.createNativeQuery("SET LOCAL statement_timeout = 5000").executeUpdate();

        String bounded = "SELECT * FROM (" + sql + ") AS q LIMIT 100";
        Query query = entityManager.createNativeQuery(bounded);

        @SuppressWarnings("unchecked")
        List<Object[]> raw = query.getResultList();

        List<List<Object>> rows = raw.stream()
                .map(r -> (List<Object>) new ArrayList<>(Arrays.asList(r)))
                .toList();

        return new QueryResult(sql, List.of(), rows);
    }
}