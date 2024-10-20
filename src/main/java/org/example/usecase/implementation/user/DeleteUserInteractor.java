package org.example.usecase.implementation.user;

import org.example.exception.ForbiddenActionException;
import org.example.exception.ItemNotFoundException;
import org.example.exception.UnauthorizedException;
import org.example.gateway.api.UserGateway;
import org.example.model.domain.User;
import org.example.model.dto.JwtUser;
import org.example.usecase.api.jwt.TokenValidator;
import org.example.usecase.api.user.DeleteUserUseCase;

import java.util.Optional;

public class DeleteUserInteractor implements DeleteUserUseCase {
    private final UserGateway gateway;
    private final TokenValidator tokenValidator;

    public DeleteUserInteractor(UserGateway gateway,
                                TokenValidator tokenValidator) {
        this.gateway = gateway;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public void delete(String userId, String token) {
        Optional<User> userToDelete = gateway.retrieveById(userId);
        if(userToDelete.isEmpty())
            throw new ItemNotFoundException(String.format("post by id [%s] is not found", userId));

        Optional<JwtUser> requestor = tokenValidator.getUserFromJwt(token);
        requestor.ifPresentOrElse(
                user -> {
                    if(!user.id().equals(userId) && !user.role().equals("ADMIN"))
                        throw new ForbiddenActionException("Cannot delete other user's accounts");
                    else gateway.delete(userId);
                },
                () -> {
                    throw new UnauthorizedException("You have to be logged in order to access this resource");
                }
        );
    }
}
