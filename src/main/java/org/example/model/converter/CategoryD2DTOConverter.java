package org.example.model.converter;

import org.example.model.domain.Category;
import org.example.model.dto.CategoryDTO;

import java.util.Base64;

public class CategoryD2DTOConverter {
    public CategoryDTO convert(Category input) {
        String base64Image = input.image() != null ?
                Base64.getEncoder().encodeToString(input.image()) : null;
        return new CategoryDTO(
                input.id().toString(),
                input.title(),
                base64Image
        );
    }
}
