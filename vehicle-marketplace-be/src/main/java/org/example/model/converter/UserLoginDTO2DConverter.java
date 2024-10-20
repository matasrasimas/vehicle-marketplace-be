package org.example.model.converter;

import org.example.model.domain.UserLogin;
import org.example.model.dto.UserLoginDTO;

public class UserLoginDTO2DConverter {
    public UserLogin convert(UserLoginDTO input) {
        return new UserLogin(input.username(), input.password());
    }
}
