package org.example.usecase.implementation.auth;

import org.example.model.domain.Role;
import org.example.usecase.api.auth.AuthenticateUseCase;
import org.example.usecase.api.jwt.TokenValidator;

public class AuthenticateInteractor implements AuthenticateUseCase {
    private final TokenValidator jwtValidator;

    public AuthenticateInteractor(TokenValidator jwtValidator) {
        this.jwtValidator = jwtValidator;
    }

    @Override
    public boolean authenticate(String token, Role role) {
        return jwtValidator.validate(token, role);
    }
}
