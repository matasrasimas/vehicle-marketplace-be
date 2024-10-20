package org.example.usecase.implementation.user;

import org.example.exception.ForbiddenActionException;
import org.example.exception.UnauthorizedException;
import org.example.gateway.api.UserGateway;
import org.example.model.converter.UserD2DTOConverter;
import org.example.model.dto.JwtUser;
import org.example.model.dto.UserDTO;
import org.example.usecase.api.jwt.TokenValidator;
import org.example.usecase.api.user.RetrieveUserByIdUseCase;

import java.util.Optional;

public class RetrieveUserByIdInteractor implements RetrieveUserByIdUseCase {
    private final UserGateway gateway;
    private final UserD2DTOConverter converter;
    private final TokenValidator tokenValidator;

    public RetrieveUserByIdInteractor(UserGateway gateway,
                                      UserD2DTOConverter converter,
                                      TokenValidator tokenValidator) {
        this.gateway = gateway;
        this.converter = converter;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public Optional<UserDTO> retrieve(String userId, String token) {
        Optional<JwtUser> requestor = tokenValidator.getUserFromJwt(token);
        if (requestor.isPresent()) {
            JwtUser user = requestor.get();
            if (!user.id().equals(userId) && !user.role().equals("ADMIN"))
                throw new ForbiddenActionException("Cannot view other user's profiles");
            else return gateway.retrieveById(userId).map(converter::convert);
        } else
            throw new UnauthorizedException("You have to be logged in order to access this resource");
    }
}
