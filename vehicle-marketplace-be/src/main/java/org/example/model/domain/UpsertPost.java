package org.example.model.domain;

import java.util.Optional;
import java.util.UUID;

public class UpsertPost {
    private final UUID categoryId;
    private final String description;
    private final String brand;
    private final String model;
    private final int manufactureYear;
    private final Double mileage;
    private final Double price;

    public UpsertPost(UUID categoryId,
                      String description,
                      String brand,
                      String model,
                      int manufactureYear,
                      Double mileage,
                      Double price) {
        this.categoryId = categoryId;
        this.description = description;
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.mileage = mileage;
        this.price = price;
    }

    public UUID getCategoryId() {
        return categoryId;
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
