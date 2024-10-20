package org.example.usecase.implementation.category;

import org.example.exception.ItemAlreadyExistsException;
import org.example.gateway.api.CategoryGateway;
import org.example.model.converter.UpsertCategoryDTO2DConverter;
import org.example.model.domain.Category;
import org.example.model.domain.UpsertCategory;
import org.example.model.dto.UpsertCategoryDTO;
import org.example.usecase.api.category.CreateCategoryUseCase;

import java.util.List;

public class CreateCategoryInteractor implements CreateCategoryUseCase {
    private final CategoryGateway gateway;
    private final UpsertCategoryDTO2DConverter converter;

    public CreateCategoryInteractor(CategoryGateway gateway,
                                    UpsertCategoryDTO2DConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public void create(UpsertCategoryDTO dto) {
        List<Category> existingCategories = gateway.retrieve();
        if(existingCategories.stream().anyMatch(c -> c.title().equalsIgnoreCase(dto.title())))
            throw new ItemAlreadyExistsException(String.format("category with title [%s] already exists", dto.title()));
        UpsertCategory input = converter.convert(dto);
        gateway.create(input);
    }
}
