package org.example.usecase.api.comment;

import org.example.model.dto.CommentDTO;

import java.util.Optional;

public interface RetrieveCommentByIdUseCase {
    Optional<CommentDTO> retrieve(String commentId);
}
