package org.example.usecase.api.category;

import org.example.model.dto.CategoryDTO;

import java.util.List;

public interface RetrieveCategoriesUseCase {
    List<CategoryDTO> retrieve();
}
