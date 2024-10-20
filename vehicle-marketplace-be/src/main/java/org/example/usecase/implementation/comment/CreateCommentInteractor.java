package org.example.usecase.implementation.comment;

import org.example.exception.UnauthorizedException;
import org.example.gateway.api.CommentGateway;
import org.example.model.converter.UpsertCommentDTO2DConverter;
import org.example.model.dto.JwtUser;
import org.example.model.dto.UpsertCommentDTO;
import org.example.usecase.api.comment.CreateCommentUseCase;
import org.example.usecase.api.jwt.TokenValidator;

import java.util.Optional;

public class CreateCommentInteractor implements CreateCommentUseCase {
    private final CommentGateway gateway;
    private final UpsertCommentDTO2DConverter converter;
    private final TokenValidator tokenValidator;

    public CreateCommentInteractor(CommentGateway gateway,
                                   UpsertCommentDTO2DConverter converter,
                                   TokenValidator tokenValidator) {
        this.gateway = gateway;
        this.converter = converter;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public void create(UpsertCommentDTO dto, String token) {
        Optional<JwtUser> requestor = tokenValidator.getUserFromJwt(token);
        requestor.ifPresentOrElse(
                user -> gateway.create(converter.convert(dto), user.id()),
                () -> {
                    throw new UnauthorizedException("You have to be logged in order to access this resource");
                }
        );
    }
}
