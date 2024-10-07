package org.example.usecase.api.post;

import org.example.common.UpsertStatus;
import org.example.model.dto.PostDTO;

public interface UpsertPostUseCase {
    UpsertStatus upsert(PostDTO dto);
}
