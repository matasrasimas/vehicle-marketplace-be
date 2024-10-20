package org.example.usecase.api.user;

import org.example.model.dto.UserDTO;

import java.util.Optional;

public interface RetrieveUserByIdUseCase {
    Optional<UserDTO> retrieve(String userId, String token);
}
