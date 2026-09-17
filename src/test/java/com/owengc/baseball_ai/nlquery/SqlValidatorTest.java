package com.owengc.baseball_ai.nlquery;

import com.owengc.baseball_ai.exception.InvalidQueryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SqlValidatorTest {

    private final SqlValidator validator = new SqlValidator();

    @Test
    void allowsPlainSelect() {
        validator.validate("SELECT name_last FROM people LIMIT 10");
    }

    @Test
    void allowsCommonTableExpression() {
        validator.validate("WITH totals AS (SELECT player_id FROM batting) SELECT * FROM totals");
    }

    @Test
    void rejectsDelete() {
        assertThrows(InvalidQueryException.class,
                () -> validator.validate("DELETE FROM people"));
    }

    @Test
    void rejectsDropHiddenAfterSelect() {
        assertThrows(InvalidQueryException.class,
                () -> validator.validate("SELECT 1; DROP TABLE batting"));
    }

    @Test
    void rejectsCommentedPayload() {
        assertThrows(InvalidQueryException.class,
                () -> validator.validate("SELECT 1 -- DROP TABLE batting"));
    }

    @Test
    void rejectsSystemCatalogAccess() {
        assertThrows(InvalidQueryException.class,
                () -> validator.validate("SELECT * FROM pg_shadow"));
    }

    @Test
    void rejectsEmptyStatement() {
        assertThrows(InvalidQueryException.class, () -> validator.validate("   "));
    }
}