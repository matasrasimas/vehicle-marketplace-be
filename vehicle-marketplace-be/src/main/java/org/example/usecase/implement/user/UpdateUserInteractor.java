package org.example.usecase.implement.user;

import org.example.gateway.api.UserGateway;
import org.example.model.converter.UpdateUserDTO2DConverter;
import org.example.model.dto.UpdateUserDTO;
import org.example.usecase.api.user.UpdateUserUseCase;

public class UpdateUserInteractor implements UpdateUserUseCase {
    private final UserGateway gateway;
    private final UpdateUserDTO2DConverter converter;

    public UpdateUserInteractor(UserGateway gateway,
                                UpdateUserDTO2DConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public void update(UpdateUserDTO input) {
        gateway.update(converter.convert(input));
    }
}
