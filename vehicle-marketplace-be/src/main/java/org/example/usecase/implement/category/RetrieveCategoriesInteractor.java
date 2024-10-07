package org.example.usecase.implement.category;

import org.example.gateway.api.CategoryGateway;
import org.example.model.converter.CategoryD2DTOConverter;
import org.example.model.domain.Category;
import org.example.model.dto.CategoryDTO;
import org.example.usecase.api.category.RetrieveCategoriesUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class RetrieveCategoriesInteractor implements RetrieveCategoriesUseCase {
    private final CategoryGateway gateway;
    private final CategoryD2DTOConverter converter;

    public RetrieveCategoriesInteractor(CategoryGateway gateway,
                                        CategoryD2DTOConverter converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public List<CategoryDTO> retrieve() {
        List<Category> categories = gateway.retrieve();
        return categories.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
