package com.gustavolyra.twiter_copy.exceptions;

public class DatabaseConflitException extends RuntimeException {
    public DatabaseConflitException(String message) {
        super(message);
    }
}
