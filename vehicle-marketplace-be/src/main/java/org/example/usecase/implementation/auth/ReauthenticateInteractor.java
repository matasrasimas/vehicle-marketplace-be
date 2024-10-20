package org.example.usecase.implementation.auth;

import org.example.usecase.api.auth.ReauthenticateUseCase;
import org.example.usecase.api.jwt.TokenUpdater;

import java.util.Optional;

public class ReauthenticateInteractor implements ReauthenticateUseCase {
    private final TokenUpdater tokenUpdater;

    public ReauthenticateInteractor(TokenUpdater tokenUpdater) {
        this.tokenUpdater = tokenUpdater;
    }

    @Override
    public Optional<String> reauthenticate(String token) {
        return tokenUpdater.update(token);
    }
}
