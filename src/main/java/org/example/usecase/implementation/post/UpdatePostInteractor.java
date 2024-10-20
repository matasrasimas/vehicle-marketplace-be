package org.example.usecase.implementation.post;

import org.example.exception.ForbiddenActionException;
import org.example.exception.ItemNotFoundException;
import org.example.exception.UnauthorizedException;
import org.example.gateway.api.PostGateway;
import org.example.model.converter.UpsertPostDTO2DConverter;
import org.example.model.domain.Post;
import org.example.model.dto.JwtUser;
import org.example.model.dto.UpsertPostDTO;
import org.example.usecase.api.jwt.TokenValidator;
import org.example.usecase.api.post.UpdatePostUseCase;

import java.util.Optional;

public class UpdatePostInteractor implements UpdatePostUseCase {
    private final PostGateway gateway;
    private final UpsertPostDTO2DConverter converter;
    private final TokenValidator tokenValidator;

    public UpdatePostInteractor(PostGateway gateway,
                                UpsertPostDTO2DConverter converter,
                                TokenValidator tokenValidator) {
        this.gateway = gateway;
        this.converter = converter;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public void update(String postId, UpsertPostDTO dto, String token) {
        Optional<JwtUser> requestor = tokenValidator.getUserFromJwt(token);
        requestor.ifPresentOrElse(
                user -> {
                    Optional<Post> postToUpdate = gateway.retrieveById(postId);
                    if(postToUpdate.isEmpty())
                        throw new ItemNotFoundException(String.format("post with id [%s] not found", postId));
                    if(!user.id().equals(postToUpdate.get().getUserId().toString()))
                        throw new ForbiddenActionException("Cannot update other user's post");
                    else gateway.update(converter.convert(dto), postId, user.id());
                },
                () -> {
                    throw new UnauthorizedException("You have to be logged in order to access this resource");
                }
        );
    }
}
