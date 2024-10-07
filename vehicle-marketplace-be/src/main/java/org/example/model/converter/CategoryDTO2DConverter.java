package org.example.model.converter;

import org.example.model.domain.Category;
import org.example.model.dto.CategoryDTO;

import java.util.UUID;

public class CategoryDTO2DConverter {
    public Category convert(CategoryDTO input) {
        return new Category(
                UUID.fromString(input.id()),
                input.title());
    }
}
