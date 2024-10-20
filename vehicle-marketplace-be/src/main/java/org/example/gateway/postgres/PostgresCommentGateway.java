package org.example.gateway.postgres;

import org.example.gateway.api.CommentGateway;
import org.example.generated.jooq.tables.records.CommentsRecord;
import org.example.model.domain.Comment;
import org.example.model.domain.UpsertComment;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.generated.jooq.tables.Comments.COMMENTS;

public class PostgresCommentGateway implements CommentGateway {
    private final DSLContext dsl;

    public PostgresCommentGateway(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public List<Comment> retrieve() {
        return dsl.selectFrom(COMMENTS)
                .fetch(this::buildComment);
    }

    @Override
    public Optional<Comment> retrieveById(String commentId) {
        return dsl.selectFrom(COMMENTS)
                .where(COMMENTS.ID.eq(UUID.fromString(commentId)))
                .fetchOptional(this::buildComment);
    }

    @Override
    public List<Comment> retrieveByPostId(String postId) {
        return dsl.selectFrom(COMMENTS)
                .where(COMMENTS.POST_ID.eq(UUID.fromString(postId)))
                .fetch(this::buildComment);
    }

    @Override
    public void create(UpsertComment input, String userId) {
        dsl.insertInto(COMMENTS)
                .set(COMMENTS.ID, UUID.randomUUID())
                .set(COMMENTS.POST_ID, input.postId())
                .set(COMMENTS.USER_ID, UUID.fromString(userId))
                .set(COMMENTS.CONTENT, input.content())
                .set(COMMENTS.RATING, input.rating())
                .execute();
    }

    @Override
    public void update(UpsertComment input, String commentId, String userId) {
        dsl.update(COMMENTS)
                .set(COMMENTS.POST_ID, input.postId())
                .set(COMMENTS.USER_ID, UUID.fromString(userId))
                .set(COMMENTS.CONTENT, input.content())
                .set(COMMENTS.RATING, input.rating())
                .where(COMMENTS.ID.eq(UUID.fromString(commentId)))
                .execute();
    }

    @Override
    public void delete(String commentId) {
        dsl.deleteFrom(COMMENTS)
                .where(COMMENTS.ID.eq(UUID.fromString(commentId)))
                .execute();
    }

    private Comment buildComment(CommentsRecord record) {
        return new Comment(
                record.getId(),
                record.getPostId(),
                record.getUserId(),
                record.getContent(),
                record.getRating()
        );
    }
}
