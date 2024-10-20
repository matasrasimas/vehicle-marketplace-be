package org.example.usecase.implementation.post;

import org.example.exception.UnauthorizedException;
import org.example.gateway.api.PostGateway;
import org.example.model.converter.UpsertPostDTO2DConverter;
import org.example.model.dto.JwtUser;
import org.example.model.dto.UpsertPostDTO;
import org.example.usecase.api.jwt.TokenValidator;
import org.example.usecase.api.post.CreatePostUseCase;

import java.util.Optional;

public class CreatePostInteractor implements CreatePostUseCase {
    private final PostGateway gateway;
    private final UpsertPostDTO2DConverter converter;
    private final TokenValidator tokenValidator;

    public CreatePostInteractor(PostGateway gateway,
                                UpsertPostDTO2DConverter converter,
                                TokenValidator tokenValidator) {
        this.gateway = gateway;
        this.converter = converter;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public void create(UpsertPostDTO dto, String token) {
        Optional<JwtUser> requestor = tokenValidator.getUserFromJwt(token);
        requestor.ifPresentOrElse(
                user -> gateway.create(converter.convert(dto), user.id()),
                () -> {
                    throw new UnauthorizedException("You have to be logged in order to access this resource");
                }
        );
    }
}
