package org.example.usecase.api.jwt;

import org.example.model.domain.Role;
import org.example.model.dto.JwtUser;

import java.util.Optional;

public interface TokenValidator {
    boolean validate(String token);
    Optional<JwtUser> getUserFromJwt(String token);
    boolean validate(String token, Role role);
}
