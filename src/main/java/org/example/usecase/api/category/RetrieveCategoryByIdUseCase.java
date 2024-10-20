package org.example.usecase.api.category;

import org.example.model.dto.CategoryDTO;

import java.util.Optional;

public interface RetrieveCategoryByIdUseCase {
    Optional<CategoryDTO> retrieve(String categoryId);
}
