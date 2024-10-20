package org.example.usecase.api.post;

import org.example.model.dto.PostDTO;

import java.util.Optional;

public interface RetrievePostByIdUseCase {
    Optional<PostDTO> retrieve(String postId);
}
