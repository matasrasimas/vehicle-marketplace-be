package org.example.exception;

public class IncorrectRequestHeaderException extends RuntimeException {
    public IncorrectRequestHeaderException(String message) {
        super(message);
    }
}
