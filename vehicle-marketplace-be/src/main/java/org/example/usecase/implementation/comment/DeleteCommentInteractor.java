package org.example.usecase.implementation.comment;

import org.example.exception.ForbiddenActionException;
import org.example.exception.ItemNotFoundException;
import org.example.exception.UnauthorizedException;
import org.example.gateway.api.CommentGateway;
import org.example.model.domain.Comment;
import org.example.model.dto.JwtUser;
import org.example.usecase.api.comment.DeleteCommentUseCase;
import org.example.usecase.api.jwt.TokenValidator;

import java.util.Optional;

public class DeleteCommentInteractor implements DeleteCommentUseCase {
    private final CommentGateway gateway;
    private final TokenValidator tokenValidator;

    public DeleteCommentInteractor(CommentGateway gateway,
                                   TokenValidator tokenValidator) {
        this.tokenValidator = tokenValidator;
        this.gateway = gateway;
    }

    @Override
    public void delete(String commentId, String token) {
        Optional<Comment> commentToDelete = gateway.retrieveById(commentId);
        if(commentToDelete.isEmpty())
            throw new ItemNotFoundException(String.format("comment by id [%s] is not found", commentId));

        Optional<JwtUser> requestor = tokenValidator.getUserFromJwt(token);
        requestor.ifPresentOrElse(
                user -> {
                    if(!user.id().equals(commentToDelete.get().userId().toString()) && !user.role().equals("ADMIN"))
                        throw new ForbiddenActionException("Cannot delete other user's comment");
                    else gateway.delete(commentId);
                },
                () -> {
                    throw new UnauthorizedException("You have to be logged in order to access this resource");
                }
        );
    }
}
