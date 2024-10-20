package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserLoginDTO(@JsonProperty("username") String username,
                           @JsonProperty("password") String password) {
}
