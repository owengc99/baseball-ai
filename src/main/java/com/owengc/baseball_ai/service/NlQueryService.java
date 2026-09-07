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

    public NlQueryService(SqlGenerator sqlGenerator, EntityManager entityManager) {
        this.sqlGenerator = sqlGenerator;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public QueryResult ask(String question) {
        String sql = sqlGenerator.generateSql(question);

        if ("UNANSWERABLE".equals(sql)) {
            return new QueryResult(sql, List.of(), List.of());
        }

        Query query = entityManager.createNativeQuery(sql);

        @SuppressWarnings("unchecked")
        List<Object[]> raw = query.getResultList();

        List<List<Object>> rows = raw.stream()
                .map(r -> (List<Object>) new ArrayList<>(Arrays.asList(r)))
                .toList();

        return new QueryResult(sql, List.of(), rows);
    }
}