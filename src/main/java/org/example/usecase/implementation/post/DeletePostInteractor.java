package org.example.usecase.implementation.post;

import org.example.exception.ForbiddenActionException;
import org.example.exception.ItemNotFoundException;
import org.example.exception.UnauthorizedException;
import org.example.gateway.api.PostGateway;
import org.example.model.domain.Post;
import org.example.model.dto.JwtUser;
import org.example.usecase.api.jwt.TokenValidator;
import org.example.usecase.api.post.DeletePostUseCase;

import java.util.Optional;

public class DeletePostInteractor implements DeletePostUseCase {
    private final PostGateway gateway;
    private final TokenValidator tokenValidator;

    public DeletePostInteractor(PostGateway gateway,
                                TokenValidator tokenValidator) {
        this.gateway = gateway;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public void delete(String postId, String token) {
        Optional<Post> postToDelete = gateway.retrieveById(postId);
        if(postToDelete.isEmpty())
            throw new ItemNotFoundException(String.format("post by id [%s] is not found", postId));

        Optional<JwtUser> requestor = tokenValidator.getUserFromJwt(token);
        requestor.ifPresentOrElse(
                user -> {
                    if(!user.id().equals(postToDelete.get().getUserId().toString()) && !user.role().equals("ADMIN"))
                        throw new ForbiddenActionException("Cannot delete other user's post");
                    else gateway.delete(postId);
                },
                () -> {
                    throw new UnauthorizedException("You have to be logged in order to access this resource");
                }
        );
    }
}
