package org.example.usecase.implementation.post;

import org.example.gateway.api.PostGateway;
import org.example.model.converter.PostD2DTOConverter;
import org.example.model.dto.PostDTO;
import org.example.usecase.api.post.RetrievePostsByUserIdUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class RetrievePostsByUserIdInteractor implements RetrievePostsByUserIdUseCase {
    private final PostGateway gateway;
    private final PostD2DTOConverter converter;

    public RetrievePostsByUserIdInteractor(PostGateway gateway,
                                           PostD2DTOConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public List<PostDTO> retrieve(String userId) {
        return gateway.retrieveByUserId(userId).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
