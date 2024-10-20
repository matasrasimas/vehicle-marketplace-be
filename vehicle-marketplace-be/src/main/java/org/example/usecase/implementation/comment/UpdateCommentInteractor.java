package org.example.usecase.implementation.comment;

import org.example.exception.ForbiddenActionException;
import org.example.exception.ItemNotFoundException;
import org.example.exception.UnauthorizedException;
import org.example.gateway.api.CommentGateway;
import org.example.model.converter.UpsertCommentDTO2DConverter;
import org.example.model.domain.Comment;
import org.example.model.dto.JwtUser;
import org.example.model.dto.UpsertCommentDTO;
import org.example.usecase.api.comment.UpdateCommentUseCase;
import org.example.usecase.api.jwt.TokenValidator;

import java.util.Optional;

public class UpdateCommentInteractor implements UpdateCommentUseCase {
    private final CommentGateway gateway;
    private final UpsertCommentDTO2DConverter converter;
    private final TokenValidator tokenValidator;

    public UpdateCommentInteractor(CommentGateway gateway,
                                   UpsertCommentDTO2DConverter converter,
                                   TokenValidator tokenValidator) {
        this.gateway = gateway;
        this.converter = converter;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public void update(String commentId, UpsertCommentDTO dto, String token) {
        Optional<JwtUser> requestor = tokenValidator.getUserFromJwt(token);
        requestor.ifPresentOrElse(
                user -> {
                    Optional<Comment> commentToUpdate = gateway.retrieveById(commentId);
                    if(commentToUpdate.isEmpty())
                        throw new ItemNotFoundException(String.format("comment with id [%s] not found", commentId));
                    if(!user.id().equals(commentToUpdate.get().userId().toString()))
                        throw new ForbiddenActionException("Cannot update other user's comment");
                    else gateway.update(converter.convert(dto), commentId, user.id());
                },
                () -> {
                    throw new UnauthorizedException("You have to be logged in order to access this resource");
                }
        );
    }
}
