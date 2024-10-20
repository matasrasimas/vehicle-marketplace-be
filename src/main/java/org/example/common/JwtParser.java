package org.example.common;

import io.javalin.http.Context;
import org.example.exception.IncorrectRequestHeaderException;

public class JwtParser {
    public static String parse(Context context) {
        String authorizationHeader = context.header("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else
            throw new IncorrectRequestHeaderException("You have to be logged in order to access this resource");
    }
}
