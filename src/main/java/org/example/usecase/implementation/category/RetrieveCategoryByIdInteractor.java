package org.example.usecase.implementation.category;

import org.example.gateway.api.CategoryGateway;
import org.example.model.converter.CategoryD2DTOConverter;
import org.example.model.domain.Category;
import org.example.model.dto.CategoryDTO;
import org.example.usecase.api.category.RetrieveCategoryByIdUseCase;

import java.util.Optional;

public class RetrieveCategoryByIdInteractor implements RetrieveCategoryByIdUseCase {
    private final CategoryGateway gateway;
    private final CategoryD2DTOConverter converter;

    public RetrieveCategoryByIdInteractor(CategoryGateway gateway,
                                          CategoryD2DTOConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public Optional<CategoryDTO> retrieve(String categoryId) {
        Optional<Category> category = gateway.retrieveById(categoryId);
        return category.map(converter::convert);
    }
}
