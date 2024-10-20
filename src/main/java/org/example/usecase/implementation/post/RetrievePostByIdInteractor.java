package org.example.usecase.implementation.post;

import org.example.gateway.api.PostGateway;
import org.example.model.converter.PostD2DTOConverter;
import org.example.model.dto.PostDTO;
import org.example.usecase.api.post.RetrievePostByIdUseCase;

import java.util.Optional;

public class RetrievePostByIdInteractor implements RetrievePostByIdUseCase {
    private final PostGateway gateway;
    private final PostD2DTOConverter converter;

    public RetrievePostByIdInteractor(PostGateway gateway,
                                   PostD2DTOConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public Optional<PostDTO> retrieve(String postId) {
        return gateway.retrieveById(postId).map(converter::convert);
    }
}
