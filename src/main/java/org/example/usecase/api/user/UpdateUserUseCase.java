package org.example.usecase.api.user;

import org.example.model.dto.UpdateUserDTO;

public interface UpdateUserUseCase {
    void update(String userId, UpdateUserDTO dto, String token);
}
