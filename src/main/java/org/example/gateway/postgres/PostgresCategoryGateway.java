package org.example.gateway.postgres;

import org.example.gateway.api.CategoryGateway;
import org.example.model.domain.Category;
import org.example.model.domain.UpsertCategory;
import org.jooq.DSLContext;

import static org.example.generated.jooq.tables.Categories.CATEGORIES;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PostgresCategoryGateway implements CategoryGateway {
    private final DSLContext dsl;

    public PostgresCategoryGateway(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public List<Category> retrieve() {
        return dsl.selectFrom(CATEGORIES)
                .fetch(record -> new Category(
                        record.getId(),
                        record.getTitle()
                ));
    }

    @Override
    public Optional<Category> retrieveById(String categoryId) {
        return dsl.selectFrom(CATEGORIES)
                .where(CATEGORIES.ID.eq(UUID.fromString(categoryId)))
                .fetchOptional(record -> new Category(
                        record.getId(),
                        record.getTitle()
                ));
    }

    @Override
    public void create(UpsertCategory input) {
        dsl.insertInto(CATEGORIES)
                .set(CATEGORIES.ID, UUID.randomUUID())
                .set(CATEGORIES.TITLE, input.title())
                .execute();
    }

    @Override
    public void update(String categoryId, UpsertCategory input) {
        dsl.update(CATEGORIES)
                .set(CATEGORIES.TITLE, input.title())
                .where(CATEGORIES.ID.eq(UUID.fromString(categoryId)))
                .execute();
    }

    @Override
    public void delete(String categoryId) {
        dsl.deleteFrom(CATEGORIES)
                .where(CATEGORIES.ID.eq(UUID.fromString(categoryId)))
                .execute();
    }
}
