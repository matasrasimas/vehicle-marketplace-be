package org.example.usecase.implementation.post;

import org.example.gateway.api.PostGateway;
import org.example.model.converter.PostD2DTOConverter;
import org.example.model.dto.PostDTO;
import org.example.usecase.api.post.RetrievePostsByCategoryIdUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class RetrievePostsByCategoryIdInteractor implements RetrievePostsByCategoryIdUseCase {
    private final PostGateway gateway;
    private final PostD2DTOConverter converter;

    public RetrievePostsByCategoryIdInteractor(PostGateway gateway,
                                      PostD2DTOConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public List<PostDTO> retrieve(String categoryId) {
        return gateway.retrieveByCategoryId(categoryId).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
