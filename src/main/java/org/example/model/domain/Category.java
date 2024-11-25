package org.example.model.domain;

import java.util.UUID;

public record Category(UUID id, String title, byte[] image) {
}
