package org.example.usecase.implement.user;

import org.example.gateway.api.UserGateway;
import org.example.model.converter.CreateUserDTO2DConverter;
import org.example.model.dto.CreateUserDTO;
import org.example.usecase.api.user.CreateUserUseCase;

public class CreateUserInteractor implements CreateUserUseCase {
    private final UserGateway gateway;
    private final CreateUserDTO2DConverter converter;

    public CreateUserInteractor(UserGateway gateway,
                                CreateUserDTO2DConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public void create(CreateUserDTO input) {
        gateway.create(converter.convert(input));
    }
}
