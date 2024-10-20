package org.example.usecase.implementation.auth;

import org.example.gateway.api.UserGateway;
import org.example.model.converter.UserLoginDTO2DConverter;
import org.example.model.domain.User;
import org.example.model.dto.JwtUser;
import org.example.model.dto.UserLoginDTO;
import org.example.usecase.api.auth.LoginUseCase;
import org.example.usecase.api.jwt.TokenGenerator;

import java.util.Optional;

public class LoginInteractor implements LoginUseCase {
    private final UserGateway userGateway;
    private final UserLoginDTO2DConverter converter;
    private final TokenGenerator tokenGenerator;

    public LoginInteractor(UserGateway userGateway,
                           UserLoginDTO2DConverter converter,
                           TokenGenerator tokenGenerator) {
        this.userGateway = userGateway;
        this.converter = converter;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public Optional<String> login(UserLoginDTO dto) {
        Optional<User> loggedUser = userGateway.login(converter.convert(dto));
        return loggedUser.map(user -> tokenGenerator.generate(new JwtUser(user.id().toString(), user.role().toString())));
    }
}
