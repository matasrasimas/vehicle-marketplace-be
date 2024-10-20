package org.example.usecase.api.comment;

import org.example.model.dto.UpsertCommentDTO;

public interface CreateCommentUseCase {
    void create(UpsertCommentDTO dto, String token);
}
