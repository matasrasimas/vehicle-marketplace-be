package org.example.model.converter;

import org.example.model.domain.UpsertPost;
import org.example.model.dto.UpsertPostDTO;

import java.util.UUID;

public class UpsertPostDTO2DConverter {
    public UpsertPost convert(UpsertPostDTO input) {
        return new UpsertPost(
                UUID.fromString(input.categoryId()),
                input.description(),
                input.brand(),
                input.model(),
                input.manufactureYear(),
                input.mileage(),
                input.price()
        );
    }
}
