package org.example.usecase.implementation.category;

import org.example.exception.ItemAlreadyExistsException;
import org.example.exception.ItemNotFoundException;
import org.example.gateway.api.CategoryGateway;
import org.example.model.converter.UpsertCategoryDTO2DConverter;
import org.example.model.domain.Category;
import org.example.model.dto.UpsertCategoryDTO;
import org.example.usecase.api.category.UpdateCategoryUseCase;

import java.util.List;
import java.util.Optional;

public class UpdateCategoryInteractor implements UpdateCategoryUseCase {
    private final CategoryGateway gateway;
    private final UpsertCategoryDTO2DConverter converter;

    public UpdateCategoryInteractor(CategoryGateway gateway,
                                    UpsertCategoryDTO2DConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public void update(String categoryId, UpsertCategoryDTO dto) {
        Optional<Category> categoryToUpdate = gateway.retrieveById(categoryId);
        if(categoryToUpdate.isEmpty())
            throw new ItemNotFoundException(String.format("category with id [%s] not found", categoryId));
        List<Category> existingCategories = gateway.retrieve();
        if(existingCategories.stream().anyMatch(c -> c.title().equalsIgnoreCase(dto.title())))
            throw new ItemAlreadyExistsException(String.format("category with title [%s] already exists", dto.title()));
        gateway.update(categoryId, converter.convert(dto));
    }
}
