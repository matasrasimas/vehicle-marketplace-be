package org.example.usecase.api.user;

import org.example.model.dto.CreateUserDTO;

public interface CreateUserUseCase {
    void create(CreateUserDTO input);
}
