package com.owengc.baseball_ai.dto;

import java.util.List;

public record QueryResult(
        String sql,
        List<String> columns,
        List<List<Object>> rows
) {}