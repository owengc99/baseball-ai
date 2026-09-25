package com.owengc.baseball_ai.nlquery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GeminiSqlGeneratorTest {

    @Test
    void failsFastWhenApiKeyIsBlank() {
        assertThrows(IllegalStateException.class,
                () -> new GeminiSqlGenerator(null, null, "", "model", "http://localhost"));
    }

    @Test
    void failsFastWhenApiKeyIsNull() {
        assertThrows(IllegalStateException.class,
                () -> new GeminiSqlGenerator(null, null, null, "model", "http://localhost"));
    }
}