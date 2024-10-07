package org.example.model.converter;

import org.example.model.domain.User;
import org.example.model.dto.UserDTO;

public class UserD2DTOConverter {
    public UserDTO convert(User input) {
        return new UserDTO(
            input.id().toString(),
                input.firstName(),
                input.lastName(),
                input.phoneNumber(),
                input.username(),
                input.role().toString()
        );
    }
}
