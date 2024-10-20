package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpsertPostDTO(@JsonProperty(required = true, value = "categoryId") String categoryId,
                            @JsonProperty("description") String description,
                            @JsonProperty(required = true, value = "brand") String brand,
                            @JsonProperty(required = true, value = "model") String model,
                            @JsonProperty(required = true, value = "manufactureYear") int manufactureYear,
                            @JsonProperty("mileage") Double mileage,
                            @JsonProperty(required = true, value = "price") Double price) {
}
