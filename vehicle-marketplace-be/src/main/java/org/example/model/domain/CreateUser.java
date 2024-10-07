package org.example.model.domain;

import java.util.UUID;

public record CreateUser(String firstName,
                         String lastName,
                         String phoneNumber,
                         String username,
                         String password,
                         Role role
) {
}
