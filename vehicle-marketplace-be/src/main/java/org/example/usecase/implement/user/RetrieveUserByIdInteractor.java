package org.example.usecase.implement.user;

import org.example.gateway.api.UserGateway;
import org.example.model.converter.UserD2DTOConverter;
import org.example.model.dto.UserDTO;
import org.example.usecase.api.user.RetrieveUserByIdUseCase;

import java.util.Optional;

public class RetrieveUserByIdInteractor implements RetrieveUserByIdUseCase {
    private final UserGateway gateway;
    private final UserD2DTOConverter converter;

    public RetrieveUserByIdInteractor(UserGateway gateway,
                                      UserD2DTOConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public Optional<UserDTO> retrieve(String userId) {
        return gateway.retrieveById(userId).map(converter::convert);
    }
}
