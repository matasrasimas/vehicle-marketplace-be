package org.example.model.domain;

import java.util.UUID;

public record UpdateUser(UUID id,
                         String firstName,
                         String lastName,
                         String phoneNumber,
                         String username,
                         Role role
) {
}
