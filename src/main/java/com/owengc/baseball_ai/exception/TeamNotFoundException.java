package com.owengc.baseball_ai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(String teamId) {
        super("Team not found with teamId: " + teamId);
    }

    public TeamNotFoundException(String teamId, Integer yearId) {
        super("Team not found with teamId: " + teamId + " and yearId: " + yearId);
    }
}