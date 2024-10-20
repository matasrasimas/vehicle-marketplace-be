package org.example.model.domain;

import java.util.UUID;

public record User(UUID id,
                   String firstName,
                   String lastName,
                   String phoneNumber,
                   String username,
                   Role role
                   ) {
}
