package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostDTO(@JsonProperty(required = true, value = "id") String id,
                      @JsonProperty(required = true, value = "categoryId") String categoryId,
                      @JsonProperty(required = true, value = "userId") String userId,
                      @JsonProperty("description") String description,
                      @JsonProperty(required = true, value = "brand") String brand,
                      @JsonProperty(required = true, value = "model") String model,
                      @JsonProperty(required = true, value = "manufactureYear") int manufactureYear,
                      @JsonProperty("mileage") Double mileage,
                      @JsonProperty(required = true, value = "price") Double price,
                      @JsonProperty("image") String image) {
}
