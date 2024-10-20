package org.example.usecase.api.category;

import org.example.model.dto.UpsertCategoryDTO;

public interface CreateCategoryUseCase {
    void create(UpsertCategoryDTO dto);
}
