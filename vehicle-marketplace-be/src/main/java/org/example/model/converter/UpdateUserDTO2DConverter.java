package org.example.model.converter;

import org.example.model.domain.Role;
import org.example.model.domain.UpdateUser;
import org.example.model.dto.UpdateUserDTO;

import java.util.UUID;

public class UpdateUserDTO2DConverter {
    public UpdateUser convert(UpdateUserDTO input) {
        return new UpdateUser(
                UUID.fromString(input.id()),
                input.firstName(),
                input.lastName(),
                input.phoneNumber(),
                input.username(),
                Role.valueOf(input.role())
        );
    }
}
