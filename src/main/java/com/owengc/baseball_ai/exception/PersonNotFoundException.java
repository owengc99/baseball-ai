package com.owengc.baseball_ai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String playerId) {
        super("Person not found with playerId: " + playerId);
    }
}
