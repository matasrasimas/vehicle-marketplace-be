package org.example.model.domain;

import java.util.UUID;

public record UpsertComment(UUID postId,
                            String content,
                            int rating) {
}
