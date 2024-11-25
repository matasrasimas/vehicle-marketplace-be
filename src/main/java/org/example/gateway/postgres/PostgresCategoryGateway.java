package org.example.gateway.postgres;

import org.example.gateway.api.CategoryGateway;
import org.example.model.domain.Category;
import org.example.model.domain.UpsertCategory;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.impl.DSL;

import static java.util.Objects.isNull;
import static org.example.generated.jooq.tables.Categories.CATEGORIES;
import static org.example.generated.jooq.tables.Comments.COMMENTS;
import static org.example.generated.jooq.tables.Posts.POSTS;
import static org.example.generated.jooq.tables.Users.USERS;

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
                        UUID.fromString(record.getId().toString()),
                        record.getTitle(),
                        record.getImage()
                ));
    }

    @Override
    public Optional<Category> retrieveById(String categoryId) {
        return dsl.selectFrom(CATEGORIES)
                .where(CATEGORIES.ID.eq(UUID.fromString(categoryId)))
                .fetchOptional(record -> new Category(
                        UUID.fromString(record.getId().toString()),
                        record.getTitle(),
                        record.getImage()
                ));
    }

    @Override
    public void create(UpsertCategory input) {
        dsl.insertInto(CATEGORIES)
                .set(CATEGORIES.ID, UUID.randomUUID())
                .set(CATEGORIES.TITLE, input.title())
                .set(CATEGORIES.IMAGE, input.image())
                .execute();
    }

    @Override
    public void update(String categoryId, UpsertCategory input) {
        Query updateQuery = isNull(input.image())
                ? dsl.update(CATEGORIES)
                .set(CATEGORIES.TITLE, input.title())
                .where(CATEGORIES.ID.eq(UUID.fromString(categoryId)))
                : dsl.update(CATEGORIES)
                .set(CATEGORIES.TITLE, input.title())
                .set(CATEGORIES.IMAGE, input.image())
                .where(CATEGORIES.ID.eq(UUID.fromString(categoryId)));

        updateQuery.execute();
    }

    @Override
    public void delete(String categoryId) {
        dsl.transaction(configuration -> {
            DSLContext transactionalDsl = DSL.using(configuration);

            List<UUID> postsIds = transactionalDsl
                    .select(POSTS.ID)
                    .from(POSTS)
                    .where(POSTS.CATEGORY_ID.eq(UUID.fromString(categoryId)))
                    .fetch(r -> UUID.fromString(r.value1().toString()));

            if(!postsIds.isEmpty())
                deletePostsComments(postsIds, transactionalDsl);

            deleteCategoryPosts(categoryId, transactionalDsl);

            transactionalDsl.deleteFrom(CATEGORIES)
                    .where(CATEGORIES.ID.eq(UUID.fromString(categoryId)))
                    .execute();
        });
    }

    private void deletePostsComments(List<UUID> postIds, DSLContext dsl) {
        dsl.delete(COMMENTS)
                .where(COMMENTS.POST_ID.in(postIds))
                .execute();
    }

    private void deleteCategoryPosts(String categoryId, DSLContext dsl) {
        dsl.delete(POSTS)
                .where(POSTS.CATEGORY_ID.eq(UUID.fromString(categoryId)))
                .execute();
    }
}
