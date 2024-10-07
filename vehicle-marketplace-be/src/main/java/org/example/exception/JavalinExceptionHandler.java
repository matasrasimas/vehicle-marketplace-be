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
                context.status(404).json(new Message(e.getMessage()));
            case IncorrectRequestBodyException ignored ->
                context.status(400).json(new Message(e.getMessage()));
            default ->
                context.status(500).json(new Message("Internal server error"));
        }
//        switch (e) {
//            case InvalidGameIdException invalidGameIdException ->
//                    context.status(400).json(new Message("Invalid game ID."));
//            case GameNotFoundException gameNotFoundException ->
//                    context.status(404).json(new Message("Game by given ID is not found."));
//            default -> context.status(500).json(new Message("Internal server error."));
//        }
    }

    private record Message(String message) {
    }
}
