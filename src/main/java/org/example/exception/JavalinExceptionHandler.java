package org.example.exception;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.google.gson.JsonParseException;
import io.javalin.http.Context;
import io.javalin.http.ExceptionHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavalinExceptionHandler implements ExceptionHandler<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavalinExceptionHandler.class);

    @Override
    public void handle(@NotNull Exception e, @NotNull Context context) {
        LOGGER.error("Route exception {}", context.fullUrl(), e);
        switch (e) {
            case ItemNotFoundException ignored ->
                context.status(404).json(new Message("item_not_found", e.getMessage()));
            case IncorrectRequestBodyException ignored ->
                context.status(400).json(new Message("incorrect_request_body", e.getMessage()));
            case IncorrectRequestHeaderException ignored ->
                    context.status(400).json(new Message("incorrect_request_header", e.getMessage()));
            case UserAlreadyExistsException ignored ->
                context.status(409).json(new Message("user_already_exists", e.getMessage()));
            case ItemAlreadyExistsException ignored ->
                    context.status(409).json(new Message("item_already_exists", e.getMessage()));
            case UnauthorizedException ignored ->
                context.status(401).json(new Message("unauthorized", e.getMessage()));
            case ForbiddenActionException ignored ->
                    context.status(403).json(new Message("forbidden_action", e.getMessage()));
            case ExpiredJwtException ignored ->
                    context.status(401).json(new Message("expired_jwt", e.getMessage()));
            default ->
                context.status(500).json(new Message( "internal_server_error", "Internal server error"));
        }
//        switch (e) {
//            case InvalidGameIdException invalidGameIdException ->
//                    context.status(400).json(new Message("Invalid game ID."));
//            case GameNotFoundException gameNotFoundException ->
//                    context.status(404).json(new Message("Game by given ID is not found."));
//            default -> context.status(500).json(new Message("Internal server error."));
//        }
    }

    private record Message(String error, String message) {
    }
}
