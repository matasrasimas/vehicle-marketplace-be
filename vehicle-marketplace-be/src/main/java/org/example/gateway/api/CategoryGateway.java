package org.example.gateway.api;

import org.example.model.domain.Category;
import org.example.model.domain.UpsertCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryGateway {
    List<Category> retrieve();
    Optional<Category> retrieveById(String categoryId);
    void create(UpsertCategory input);
    void update(String categoryId, UpsertCategory input);
    void delete(String categoryId);
}
