package org.example.exception;

public class IncorrectRequestBodyException extends RuntimeException {
    public IncorrectRequestBodyException(String message) {
        super(message);
    }
}
