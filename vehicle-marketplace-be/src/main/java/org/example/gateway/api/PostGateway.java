package org.example.gateway.api;

import org.example.model.domain.Post;
import org.example.model.domain.UpsertPost;

import java.util.List;
import java.util.Optional;

public interface PostGateway {
    List<Post> retrieve();
    List<Post> retrieveByCategoryId(String categoryId);
    List<Post> retrieveByUserId(String userId);
    Optional<Post> retrieveById(String postId);
    void create(UpsertPost input, String userId);
    void update(UpsertPost input, String postId, String userId);
    void delete(String postId);
}
