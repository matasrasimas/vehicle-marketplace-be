package org.example.usecase.api.auth;

import org.example.model.dto.UserLoginDTO;

import java.util.Optional;

public interface LoginUseCase {
    Optional<String> login(UserLoginDTO dto);
}
