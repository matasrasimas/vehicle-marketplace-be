package org.example.usecase.api.category;

import org.example.common.UpsertStatus;
import org.example.model.dto.CategoryDTO;

public interface UpsertCategoryUseCase {
    UpsertStatus upsert(CategoryDTO dto);
}
