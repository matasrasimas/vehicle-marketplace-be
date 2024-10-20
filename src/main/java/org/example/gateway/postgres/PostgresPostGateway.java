package org.example.gateway.postgres;

import org.example.gateway.api.PostGateway;
import org.example.generated.jooq.tables.records.PostsRecord;
import org.example.model.domain.Post;
import org.example.model.domain.UpsertPost;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.generated.jooq.tables.Posts.POSTS;

public class PostgresPostGateway implements PostGateway {
    private final DSLContext dsl;

    public PostgresPostGateway(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public List<Post> retrieve() {
        return dsl.selectFrom(POSTS)
                .fetch(this::buildPost);
    }

    @Override
    public List<Post> retrieveByCategoryId(String categoryId) {
        return dsl.selectFrom(POSTS)
                .where(POSTS.CATEGORY_ID.eq(UUID.fromString(categoryId)))
                .fetch(this::buildPost);
    }

    @Override
    public List<Post> retrieveByUserId(String userId) {
        return dsl.selectFrom(POSTS)
                .where(POSTS.USER_ID.eq(UUID.fromString(userId)))
                .fetch(this::buildPost);
    }

    @Override
    public Optional<Post> retrieveById(String postId) {
        return dsl.selectFrom(POSTS)
                .where(POSTS.ID.eq(UUID.fromString(postId)))
                .fetchOptional(this::buildPost);
    }

    @Override
    public void create(UpsertPost input, String userId) {
        dsl.insertInto(POSTS)
                .set(POSTS.ID, UUID.randomUUID())
                .set(POSTS.CATEGORY_ID, input.getCategoryId())
                .set(POSTS.USER_ID, UUID.fromString(userId))
                .set(POSTS.DESCRIPTION, input.getDescription().orElse(null))
                .set(POSTS.BRAND, input.getBrand())
                .set(POSTS.MODEL, input.getModel())
                .set(POSTS.MANUFACTURE_YEAR, input.getManufactureYear())
                .set(POSTS.MILEAGE, input.getMileage().orElse(null))
                .set(POSTS.PRICE, input.getPrice())
                .execute();
    }

    @Override
    public void update(UpsertPost input, String postId, String userId) {
        dsl.update(POSTS)
                .set(POSTS.CATEGORY_ID, input.getCategoryId())
                .set(POSTS.USER_ID, UUID.fromString(userId))
                .set(POSTS.DESCRIPTION, input.getDescription().orElse(null))
                .set(POSTS.BRAND, input.getBrand())
                .set(POSTS.MODEL, input.getModel())
                .set(POSTS.MANUFACTURE_YEAR, input.getManufactureYear())
                .set(POSTS.MILEAGE, input.getMileage().orElse(null))
                .set(POSTS.PRICE, input.getPrice())
                .where(POSTS.ID.eq(UUID.fromString(postId)))
                .execute();
    }

    @Override
    public void delete(String postId) {
        dsl.deleteFrom(POSTS)
                .where(POSTS.ID.eq(UUID.fromString(postId)))
                .execute();
    }

    private Post buildPost(PostsRecord record) {
        return new Post(
                UUID.fromString(record.getId().toString()),
                UUID.fromString(record.getCategoryId().toString()),
                UUID.fromString(record.getUserId().toString()),
                record.getDescription(),
                record.getBrand(),
                record.getModel(),
                record.getManufactureYear(),
                record.getMileage(),
                record.getPrice()
        );
    }
}
