package org.example.usecase.implement.category;

import org.example.common.UpsertStatus;
import org.example.gateway.api.CategoryGateway;
import org.example.model.converter.CategoryDTO2DConverter;
import org.example.model.domain.Category;
import org.example.model.dto.CategoryDTO;
import org.example.usecase.api.category.UpsertCategoryUseCase;

public class UpsertCategoryInteractor implements UpsertCategoryUseCase {
    private final CategoryGateway gateway;
    private final CategoryDTO2DConverter converter;

    public UpsertCategoryInteractor(CategoryGateway gateway,
                                    CategoryDTO2DConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public UpsertStatus upsert(CategoryDTO dto) {
        Category input = converter.convert(dto);
        return gateway.upsert(input);
    }
}
