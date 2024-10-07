package org.example.usecase.implement.comment;

import org.example.common.UpsertStatus;
import org.example.gateway.api.CommentGateway;
import org.example.model.converter.CommentDTO2DConverter;
import org.example.model.dto.CommentDTO;
import org.example.usecase.api.comment.UpsertCommentUseCase;

public class UpsertCommentInteractor implements UpsertCommentUseCase {
    private final CommentGateway gateway;
    private final CommentDTO2DConverter converter;

    public UpsertCommentInteractor(CommentGateway gateway,
                                   CommentDTO2DConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public UpsertStatus upsert(CommentDTO dto) {
        return gateway.upsert(converter.convert(dto));
    }
}
