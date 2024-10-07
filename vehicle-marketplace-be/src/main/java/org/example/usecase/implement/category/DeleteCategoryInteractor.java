package org.example.usecase.implement.category;

import org.example.gateway.api.CategoryGateway;
import org.example.usecase.api.category.DeleteCategoryUseCase;

public class DeleteCategoryInteractor implements DeleteCategoryUseCase {
    private final CategoryGateway gateway;

    public DeleteCategoryInteractor(CategoryGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void delete(String categoryId) {
        gateway.delete(categoryId);
    }
}
