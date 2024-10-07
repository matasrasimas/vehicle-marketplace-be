package org.example.usecase.implement.comment;

import org.example.gateway.api.CommentGateway;
import org.example.model.converter.CommentD2DTOConverter;
import org.example.model.dto.CommentDTO;
import org.example.usecase.api.comment.RetrieveCommentsUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class RetrieveCommentsInteractor implements RetrieveCommentsUseCase {
    private final CommentGateway gateway;
    private final CommentD2DTOConverter converter;

    public RetrieveCommentsInteractor(CommentGateway gateway,
                                      CommentD2DTOConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public List<CommentDTO> retrieve() {
        return gateway.retrieve().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
