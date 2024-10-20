package org.example.usecase.api.post;

import org.example.model.dto.UpsertPostDTO;

public interface UpdatePostUseCase {
    void update(String postId, UpsertPostDTO input, String token);
}
