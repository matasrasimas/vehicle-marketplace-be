package org.example.usecase.implement.user;

import org.example.gateway.api.UserGateway;
import org.example.model.converter.UserD2DTOConverter;
import org.example.model.dto.UserDTO;
import org.example.usecase.api.user.RetrieveUsersUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class RetrieveUsersInteractor implements RetrieveUsersUseCase {
    private final UserGateway gateway;
    private final UserD2DTOConverter converter;

    public RetrieveUsersInteractor(UserGateway gateway,
                                   UserD2DTOConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public List<UserDTO> retrieve() {
        return gateway.retrieve().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
