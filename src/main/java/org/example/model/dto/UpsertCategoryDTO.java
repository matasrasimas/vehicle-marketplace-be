package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpsertCategoryDTO(@JsonProperty(required = true, value = "title") String title,
                                @JsonProperty("image") byte[] image) {
}
