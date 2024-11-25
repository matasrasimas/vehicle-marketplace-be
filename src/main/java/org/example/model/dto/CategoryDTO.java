package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoryDTO(@JsonProperty(required = true, value = "id") String id,
                          @JsonProperty(required = true, value = "title") String title,
                          @JsonProperty("image") String image) {
}
