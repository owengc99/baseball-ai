package com.owengc.baseball_ai.nlquery;

import com.owengc.baseball_ai.exception.InvalidQueryException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class SqlValidator {

    private static final List<String> FORBIDDEN = List.of(
            "insert", "update", "delete", "drop", "alter", "create",
            "truncate", "grant", "revoke", "copy", "vacuum", "call", "do"
    );

    public void validate(String sql) {
        String trimmed = sql.trim();
        String lower = trimmed.toLowerCase(Locale.ROOT);

        if (trimmed.isEmpty()) {
            throw new InvalidQueryException("empty statement");
        }

        if (!lower.startsWith("select") && !lower.startsWith("with")) {
            throw new InvalidQueryException("only SELECT statements are allowed");
        }

        if (trimmed.contains(";")) {
            throw new InvalidQueryException("statement separators are not allowed");
        }

        if (lower.contains("--") || lower.contains("/*")) {
            throw new InvalidQueryException("SQL comments are not allowed");
        }

        if (lower.contains("pg_") || lower.contains("information_schema")) {
            throw new InvalidQueryException("system catalogs are not accessible");
        }

        for (String keyword : FORBIDDEN) {
            if (lower.matches(".*\\b" + keyword + "\\b.*")) {
                throw new InvalidQueryException("forbidden keyword: " + keyword);
            }
        }
    }
}