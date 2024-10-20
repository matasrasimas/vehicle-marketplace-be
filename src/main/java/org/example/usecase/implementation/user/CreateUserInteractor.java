package org.example.usecase.implementation.user;

import org.example.exception.UserAlreadyExistsException;
import org.example.gateway.api.UserGateway;
import org.example.model.converter.CreateUserDTO2DConverter;
import org.example.model.domain.User;
import org.example.model.dto.CreateUserDTO;
import org.example.usecase.api.user.CreateUserUseCase;

import java.util.List;

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
        List<User> existingUsers = gateway.retrieve();
        if(existingUsers.stream().anyMatch(u -> u.username().equalsIgnoreCase(input.username())))
            throw new UserAlreadyExistsException(
                    String.format("User with username [%s] already exists", input.username()));
        gateway.create(converter.convert(input));
    }
}
