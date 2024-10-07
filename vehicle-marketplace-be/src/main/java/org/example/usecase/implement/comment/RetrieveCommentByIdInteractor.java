package org.example.usecase.implement.comment;

import org.example.gateway.api.CommentGateway;
import org.example.model.converter.CommentD2DTOConverter;
import org.example.model.dto.CommentDTO;
import org.example.usecase.api.comment.RetrieveCommentByIdUseCase;

import java.util.Optional;

public class RetrieveCommentByIdInteractor implements RetrieveCommentByIdUseCase {
    private final CommentGateway gateway;
    private final CommentD2DTOConverter converter;

    public RetrieveCommentByIdInteractor(CommentGateway gateway,
                                         CommentD2DTOConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public Optional<CommentDTO> retrieve(String commentId) {
        return gateway.retrieveById(commentId).map(converter::convert);
    }
}
