package org.example.usecase.api.comment;

import org.example.common.UpsertStatus;
import org.example.model.dto.CommentDTO;

public interface UpsertCommentUseCase {
    UpsertStatus upsert(CommentDTO dto);
}
