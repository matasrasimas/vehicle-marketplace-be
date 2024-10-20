package org.example.usecase.implementation.category;

import org.example.exception.ItemNotFoundException;
import org.example.gateway.api.CategoryGateway;
import org.example.model.domain.Category;
import org.example.usecase.api.category.DeleteCategoryUseCase;

import java.util.Optional;

public class DeleteCategoryInteractor implements DeleteCategoryUseCase {
    private final CategoryGateway gateway;

    public DeleteCategoryInteractor(CategoryGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void delete(String categoryId) {
        Optional<Category> categoryToDelete = gateway.retrieveById(categoryId);
        if(categoryToDelete.isEmpty())
            throw new ItemNotFoundException(String.format("category with id [%s] not found", categoryId));
        gateway.delete(categoryId);
    }
}
