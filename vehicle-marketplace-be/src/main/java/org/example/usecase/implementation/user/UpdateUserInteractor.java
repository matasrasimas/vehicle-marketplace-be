package org.example.usecase.implementation.user;

import org.example.exception.ForbiddenActionException;
import org.example.exception.ItemNotFoundException;
import org.example.exception.UnauthorizedException;
import org.example.exception.UserAlreadyExistsException;
import org.example.gateway.api.UserGateway;
import org.example.model.converter.UpdateUserDTO2DConverter;
import org.example.model.domain.User;
import org.example.model.dto.JwtUser;
import org.example.model.dto.UpdateUserDTO;
import org.example.usecase.api.jwt.TokenValidator;
import org.example.usecase.api.user.UpdateUserUseCase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UpdateUserInteractor implements UpdateUserUseCase {
    private final UserGateway gateway;
    private final UpdateUserDTO2DConverter converter;
    private final TokenValidator tokenValidator;

    public UpdateUserInteractor(UserGateway gateway,
                                UpdateUserDTO2DConverter converter,
                                TokenValidator tokenValidator) {
        this.gateway = gateway;
        this.converter = converter;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public void update(String userId, UpdateUserDTO dto, String token) {
        Optional<JwtUser> requestor = tokenValidator.getUserFromJwt(token);
        requestor.ifPresentOrElse(
                user -> {
                    Optional<User> userToUpdate = gateway.retrieveById(userId);
                    if(userToUpdate.isEmpty())
                        throw new ItemNotFoundException(String.format("user with id [%s] not found", userId));
                    if(!user.id().equals(userId))
                        throw new ForbiddenActionException("Cannot update other user's profile");
                    List<User> existingUsers = gateway.retrieve();
                    if (existingUsers.stream()
                            .anyMatch(u -> u.username().equalsIgnoreCase(dto.username())
                                    && !u.id().equals(UUID.fromString(userId))))
                        throw new UserAlreadyExistsException(
                                String.format("User with username [%s] already exists", dto.username()));
                    else gateway.update(userId, converter.convert(dto));
                },
                () -> {
                    throw new UnauthorizedException("You have to be logged in order to access this resource");
                }
        );
    }
}
