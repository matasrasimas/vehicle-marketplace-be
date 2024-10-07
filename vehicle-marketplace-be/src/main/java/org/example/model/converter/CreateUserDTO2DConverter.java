package org.example.model.converter;

import org.example.model.domain.CreateUser;
import org.example.model.domain.Role;
import org.example.model.dto.CreateUserDTO;

public class CreateUserDTO2DConverter {
    public CreateUser convert(CreateUserDTO input) {
        return new CreateUser(
                input.firstName(),
                input.lastName(),
                input.phoneNumber(),
                input.username(),
                input.password(),
                Role.valueOf(input.role())
        );
    }
}
