package org.example.model.converter;

import org.example.model.domain.UpsertCategory;
import org.example.model.dto.UpsertCategoryDTO;

public class UpsertCategoryDTO2DConverter {
    public UpsertCategory convert(UpsertCategoryDTO input) {
        return new UpsertCategory(input.title());
    }
}
