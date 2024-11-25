package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record CommentDTO(@JsonProperty(required = true, value = "id") String id,
                         @JsonProperty(required = true, value = "postId") String postId,
                         @JsonProperty(required = true, value = "userId") String userId,
                         @JsonProperty(required = true, value = "content") String content,
                         @JsonProperty(required = true, value = "rating") int rating,
                         @JsonProperty(required = true, value = "createdAt") String createdAt) {
}
