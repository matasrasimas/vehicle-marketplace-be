package org.example.gateway.api;

import org.example.common.UpsertStatus;
import org.example.model.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryGateway {
    List<Category> retrieve();
    Optional<Category> retrieveById(String categoryId);
    UpsertStatus upsert(Category input);
    void delete(String categoryId);
}
