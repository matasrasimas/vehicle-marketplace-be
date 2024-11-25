package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateUserDTO(@JsonProperty(required = true, value = "firstName") String firstName,
                            @JsonProperty(required = true, value = "lastName") String lastName,
                            @JsonProperty(required = true, value = "phoneNumber") String phoneNumber,
                            @JsonProperty(required = true, value = "username") String username) {
}
