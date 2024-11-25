package org.example.usecase.api.auth;

import org.example.model.dto.UserDTO;

public interface ExtractUserFromJwtUseCase {
    UserDTO extract(String jwt);
}
