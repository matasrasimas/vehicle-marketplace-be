package org.example.route.auth;

import io.javalin.http.Context;
import org.example.exception.IncorrectRequestBodyException;
import org.example.exception.UnauthorizedException;
import org.example.model.domain.Role;
import org.example.usecase.api.auth.AuthenticateUseCase;
import org.example.usecase.api.auth.ReauthenticateUseCase;

import java.util.HashMap;
import java.util.Map;

public class ReauthenticateRoute {
    private final ReauthenticateUseCase useCase;

    public ReauthenticateRoute(ReauthenticateUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String authorizationHeader = context.header("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            Map<String, String> response = new HashMap<>();
            String token = authorizationHeader.substring(7);
            useCase.reauthenticate(token).ifPresentOrElse(
                    jwt -> {
                        response.put("jwt", jwt);
                        context.json(response);
                    },
                    () -> {
                        throw new UnauthorizedException("Reauthentication failed");
                    });
        } else
            throw new IncorrectRequestBodyException("You have to be logged in order to access this resource");
    }
}
