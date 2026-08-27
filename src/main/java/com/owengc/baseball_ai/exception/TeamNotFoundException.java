package com.owengc.baseball_ai.exception;

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(String teamId) {
        super("Team not found with teamId: " + teamId);
    }

    public TeamNotFoundException(String teamId, Integer yearId) {
        super("Team not found with teamId: " + teamId + " and yearId: " + yearId);
    }
}