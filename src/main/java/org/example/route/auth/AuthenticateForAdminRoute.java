package org.example.route.auth;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.exception.IncorrectRequestBodyException;
import org.example.exception.UnauthorizedException;
import org.example.model.domain.Role;
import org.example.usecase.api.auth.AuthenticateUseCase;

public class AuthenticateForAdminRoute {
    private final AuthenticateUseCase useCase;

    public AuthenticateForAdminRoute(AuthenticateUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String jwt = JwtParser.parse(context);
        if (!useCase.authenticate(jwt, Role.ADMIN))
            throw new UnauthorizedException("You don't have permission to access this resource");
    }
}
