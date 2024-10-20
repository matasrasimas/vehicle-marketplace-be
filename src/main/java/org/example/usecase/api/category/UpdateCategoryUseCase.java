package org.example.usecase.api.category;

import org.example.model.dto.UpsertCategoryDTO;

public interface UpdateCategoryUseCase {
    void update(String categoryId, UpsertCategoryDTO dto);
}
