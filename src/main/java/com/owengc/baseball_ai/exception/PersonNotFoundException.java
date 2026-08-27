package com.owengc.baseball_ai.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String playerId) {
        super("Person not found with playerId: " + playerId);
    }
}