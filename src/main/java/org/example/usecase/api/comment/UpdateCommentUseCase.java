package org.example.usecase.api.comment;

import org.example.model.dto.UpsertCommentDTO;

public interface UpdateCommentUseCase {
    void update(String commentId, UpsertCommentDTO dto, String token);
}
