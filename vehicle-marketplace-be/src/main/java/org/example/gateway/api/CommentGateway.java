package org.example.gateway.api;

import org.example.common.UpsertStatus;
import org.example.model.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentGateway {
    List<Comment> retrieve();
    Optional<Comment> retrieveById(String commentId);
    List<Comment> retrieveByPostId(String postId);
    UpsertStatus upsert(Comment input);
    void delete(String commentId);
}
