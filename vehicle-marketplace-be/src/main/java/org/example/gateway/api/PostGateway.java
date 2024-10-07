package org.example.gateway.api;

import org.example.common.UpsertStatus;
import org.example.model.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostGateway {
    List<Post> retrieve();
    List<Post> retrieveByCategoryId(String categoryId);
    List<Post> retrieveByUserId(String userId);
    Optional<Post> retrieveById(String postId);
    UpsertStatus upsert(Post input);
    void delete(String postId);
}
