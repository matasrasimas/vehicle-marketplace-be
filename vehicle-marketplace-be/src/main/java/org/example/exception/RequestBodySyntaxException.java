package org.example.exception;

public class RequestBodySyntaxException extends RuntimeException {
    public RequestBodySyntaxException(String message) {
        super(message);
    }
}
