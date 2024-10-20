package org.example.usecase.implementation.post;

import org.example.gateway.api.PostGateway;
import org.example.model.converter.PostD2DTOConverter;
import org.example.model.domain.Post;
import org.example.model.dto.PostDTO;
import org.example.usecase.api.post.RetrievePostsUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class RetrievePostsInteractor implements RetrievePostsUseCase {
    private final PostGateway gateway;
    private final PostD2DTOConverter converter;

    public RetrievePostsInteractor(PostGateway gateway,
                                   PostD2DTOConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public List<PostDTO> retrieve() {
        List<Post> posts = gateway.retrieve();
        return posts.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
