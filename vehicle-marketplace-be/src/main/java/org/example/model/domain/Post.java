package org.example.model.domain;

import java.util.Optional;
import java.util.UUID;

public class Post {
    private final UUID id;
    private final UUID categoryId;
    private final UUID userId;
    private final String description;
    private final String brand;
    private final String model;
    private final int manufactureYear;
    private final Double mileage;
    private final Double price;

    public Post(UUID id,
                UUID categoryId,
                UUID userId,
                String description,
                String brand,
                String model,
                int manufactureYear,
                Double mileage,
                Double price) {
        this.id = id;
        this.categoryId = categoryId;
        this.userId = userId;
        this.description = description;
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.mileage = mileage;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public Optional<Double> getMileage() {
        return Optional.ofNullable(mileage);
    }

    public Double getPrice() {
        return price;
    }
}
