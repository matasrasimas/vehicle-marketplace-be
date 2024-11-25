package org.example.model.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record Comment(UUID id,
                      UUID postId,
                      UUID userId,
                      String content,
                      int rating,
                      String createdAt) {
}
