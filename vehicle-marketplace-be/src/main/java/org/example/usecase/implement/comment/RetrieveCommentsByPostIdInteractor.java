package org.example.usecase.implement.comment;

import org.example.gateway.api.CommentGateway;
import org.example.model.converter.CommentD2DTOConverter;
import org.example.model.dto.CommentDTO;
import org.example.usecase.api.comment.RetrieveCommentsByPostIdUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class RetrieveCommentsByPostIdInteractor implements RetrieveCommentsByPostIdUseCase {
    private final CommentGateway gateway;
    private final CommentD2DTOConverter converter;

    public RetrieveCommentsByPostIdInteractor(CommentGateway gateway,
                                              CommentD2DTOConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public List<CommentDTO> retrieve(String postId) {
        return gateway.retrieveByPostId(postId).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
