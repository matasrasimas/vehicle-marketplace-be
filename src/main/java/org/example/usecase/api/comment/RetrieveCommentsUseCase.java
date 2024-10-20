package org.example.usecase.api.comment;

import org.example.model.dto.CommentDTO;

import java.util.List;

public interface RetrieveCommentsUseCase {
    List<CommentDTO> retrieve();
}
