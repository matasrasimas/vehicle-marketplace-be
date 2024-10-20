package org.example.usecase.api.auth;

import java.util.Optional;

public interface ReauthenticateUseCase {
    Optional<String> reauthenticate(String token);
}
