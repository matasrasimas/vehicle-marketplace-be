package org.example.gateway.api;

import org.example.model.domain.Comment;
import org.example.model.domain.UpsertComment;

import java.util.List;
import java.util.Optional;

public interface CommentGateway {
    List<Comment> retrieve();
    Optional<Comment> retrieveById(String commentId);
    List<Comment> retrieveByPostId(String postId);
    void create(UpsertComment input, String userId);
    void update(UpsertComment input, String commentId, String userId);
    void delete(String commentId);
}
