package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.dto.QueryResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

@Service
public class NlQueryService {

    private static final int MAX_ROWS = 100;

    private final SqlGenerator sqlGenerator;
    private final SqlValidator sqlValidator;
    private final JdbcTemplate readonlyJdbcTemplate;

    public NlQueryService(SqlGenerator sqlGenerator,
                          SqlValidator sqlValidator,
                          @Qualifier("readonlyJdbcTemplate") JdbcTemplate readonlyJdbcTemplate) {
        this.sqlGenerator = sqlGenerator;
        this.sqlValidator = sqlValidator;
        this.readonlyJdbcTemplate = readonlyJdbcTemplate;
    }

    public QueryResult ask(String question) {
        String sql = sqlGenerator.generateSql(question);

        if ("UNANSWERABLE".equals(sql)) {
            return new QueryResult(sql, List.of(), List.of());
        }

        sqlValidator.validate(sql);

        // Wrapping rather than setMaxResults: the model usually emits its own LIMIT,
        // and a second limit clause is a syntax error. Safe because the validator
        // guarantees a single SELECT.
        String bounded = "SELECT * FROM (" + sql + ") AS q LIMIT " + MAX_ROWS;

        return readonlyJdbcTemplate.query(bounded, rs -> {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            List<String> columns = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(meta.getColumnLabel(i));
            }

            List<List<Object>> rows = new ArrayList<>();
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                rows.add(row);
            }

            return new QueryResult(sql, columns, rows);
        });
    }
}