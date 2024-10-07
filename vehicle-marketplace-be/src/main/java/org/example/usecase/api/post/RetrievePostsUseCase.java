package org.example.usecase.api.post;

import org.example.model.dto.PostDTO;

import java.util.List;

public interface RetrievePostsUseCase {
    List<PostDTO> retrieve();
}
