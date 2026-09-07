package com.owengc.baseball_ai.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SchemaProvider {

    private static final String QUERY = """
            SELECT table_name, column_name, data_type
            FROM information_schema.columns
            WHERE table_schema = 'public'
              AND table_name IN ('people','teams','batting','pitching','fielding')
            ORDER BY table_name, ordinal_position
            """;

    private final EntityManager entityManager;

    private String schema;

    public SchemaProvider(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    void loadSchema() {
        List<Object[]> rows = entityManager.createNativeQuery(QUERY).getResultList();

        Map<String, List<Object[]>> byTable = rows.stream()
                .collect(Collectors.groupingBy(
                        r -> (String) r[0],
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        this.schema = byTable.entrySet().stream()
                .map(e -> formatTable(e.getKey(), e.getValue()))
                .collect(Collectors.joining("\n"));

        System.out.println(this.schema);   // ← temporary
    }

    private String formatTable(String table, List<Object[]> columns) {
        return columns.stream()
                .map(r -> r[1] + ": " + r[2])
                .collect(Collectors.joining(", ", table + "(", ")"));
    }

    public String getSchema() {
        return schema;
    }
}