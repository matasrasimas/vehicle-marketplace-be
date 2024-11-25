package org.example.usecase.implementation.auth;

import org.example.gateway.api.UserGateway;
import org.example.model.converter.UserD2DTOConverter;
import org.example.model.dto.JwtUser;
import org.example.model.dto.UserDTO;
import org.example.usecase.api.auth.ExtractUserFromJwtUseCase;
import org.example.usecase.api.jwt.TokenValidator;

import java.util.Optional;

public class ExtractUserFromJwtInteractor implements ExtractUserFromJwtUseCase {
    private final UserGateway userGateway;
    private final TokenValidator tokenValidator;
    private final UserD2DTOConverter userD2DTOConverter;

    public ExtractUserFromJwtInteractor(UserGateway userGateway,
                                        TokenValidator tokenValidator,
                                        UserD2DTOConverter userD2DTOConverter) {
        this.userGateway = userGateway;
        this.tokenValidator = tokenValidator;
        this.userD2DTOConverter = userD2DTOConverter;
    }

    @Override
    public UserDTO extract(String jwt) {
        Optional<JwtUser> userFromJwt = tokenValidator.getUserFromJwt(jwt);
        return userFromJwt
                .map(user -> userD2DTOConverter.convert(userGateway.retrieveById(user.id()).orElseThrow()))
                .orElseThrow();
    }
}
