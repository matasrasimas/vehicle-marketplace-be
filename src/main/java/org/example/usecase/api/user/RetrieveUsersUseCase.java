package org.example.usecase.api.user;

import org.example.model.dto.UserDTO;

import java.util.List;

public interface RetrieveUsersUseCase {
    List<UserDTO> retrieve();
}
