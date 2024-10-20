package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpsertCommentDTO(@JsonProperty(required = true, value = "postId") String postId,
                               @JsonProperty(required = true, value = "content") String content,
                               @JsonProperty(required = true, value = "rating") int rating) {
}
