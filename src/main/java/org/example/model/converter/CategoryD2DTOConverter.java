package org.example.model.converter;

import org.example.model.domain.Category;
import org.example.model.dto.CategoryDTO;

public class CategoryD2DTOConverter {
    public CategoryDTO convert(Category input) {
        return new CategoryDTO(
                input.id().toString(),
                input.title());
    }
}
