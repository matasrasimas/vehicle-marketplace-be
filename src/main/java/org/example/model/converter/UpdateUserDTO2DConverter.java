package org.example.model.converter;

import org.example.model.domain.UpdateUser;
import org.example.model.dto.UpdateUserDTO;

public class UpdateUserDTO2DConverter {
    public UpdateUser convert(UpdateUserDTO input) {
        return new UpdateUser(
                input.firstName(),
                input.lastName(),
                input.phoneNumber(),
                input.username()
        );
    }
}
