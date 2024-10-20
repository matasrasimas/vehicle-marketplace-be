package org.example.usecase.api.auth;

import org.example.model.domain.Role;

public interface AuthenticateUseCase {
    boolean authenticate(String token, Role role);
}
