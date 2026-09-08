package com.owengc.baseball_ai.exception;

public class InvalidQueryException extends RuntimeException {

    public InvalidQueryException(String reason) {
        super("Generated query was rejected: " + reason);
    }
}