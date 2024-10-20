package org.example.usecase.api.post;

import org.example.model.dto.UpsertPostDTO;

public interface CreatePostUseCase {
    void create(UpsertPostDTO dto, String token);
}
