package org.example.gateway.postgres;

import org.example.common.UpsertStatus;
import org.example.gateway.api.CategoryGateway;
import org.example.model.domain.Category;
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
    public UpsertStatus upsert(Category input) {
        int rowsAffected =
                dsl.insertInto(CATEGORIES)
                .set(CATEGORIES.ID, input.id())
                .set(CATEGORIES.TITLE, input.title())
                .onDuplicateKeyUpdate()
                .set(CATEGORIES.TITLE, input.title())
                .execute();

        if(rowsAffected == 1)
            return UpsertStatus.RESOURCE_CREATED;
        return UpsertStatus.RESOURCE_UPDATED;
    }

    @Override
    public void delete(String categoryId) {
        dsl.deleteFrom(CATEGORIES)
                .where(CATEGORIES.ID.eq(UUID.fromString(categoryId)))
                .execute();
    }
}
