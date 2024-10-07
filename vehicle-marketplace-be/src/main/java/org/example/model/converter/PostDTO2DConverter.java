package org.example.model.converter;

import org.example.model.domain.Post;
import org.example.model.dto.PostDTO;

import java.util.UUID;

public class PostDTO2DConverter {
    public Post convert(PostDTO input) {
        return new Post(
                UUID.fromString(input.id()),
                UUID.fromString(input.categoryId()),
                UUID.fromString(input.userId()),
                input.description(),
                input.brand(),
                input.model(),
                input.manufactureYear(),
                input.mileage(),
                input.price()
        );
    }
}
