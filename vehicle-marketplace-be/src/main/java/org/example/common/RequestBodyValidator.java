package org.example.common;

import io.javalin.http.Context;
import io.javalin.validation.BodyValidator;
import org.example.exception.IncorrectRequestBodyException;

public class RequestBodyValidator {
    public static <T> void validate(Context context, Class<T> clazz) {
        BodyValidator<T> bodyValidator = context.bodyValidator(clazz);
        if(!bodyValidator.errors().isEmpty())
            throw new IncorrectRequestBodyException("Incorrect request body");
    }
}
