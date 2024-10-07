package org.example.gateway.postgres;

import org.example.common.UpsertStatus;
import org.example.gateway.api.CommentGateway;
import org.example.generated.jooq.tables.records.CommentsRecord;
import org.example.model.domain.Comment;
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
    public UpsertStatus upsert(Comment input) {
        int rowsAffected =
                dsl.insertInto(COMMENTS)
                .set(COMMENTS.ID, input.id())
                .set(COMMENTS.POST_ID, input.postId())
                .set(COMMENTS.USER_ID, input.userId())
                .set(COMMENTS.CONTENT, input.content())
                .set(COMMENTS.RATING, input.rating())
                .onDuplicateKeyUpdate()
                .set(COMMENTS.POST_ID, input.postId())
                .set(COMMENTS.USER_ID, input.userId())
                .set(COMMENTS.CONTENT, input.content())
                .set(COMMENTS.RATING, input.rating())
                .execute();

        if(rowsAffected == 1)
            return UpsertStatus.RESOURCE_CREATED;
        return UpsertStatus.RESOURCE_UPDATED;

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
