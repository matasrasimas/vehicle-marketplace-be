package org.example.model.converter;

import org.example.model.domain.Post;
import org.example.model.dto.PostDTO;

public class PostD2DTOConverter {
    public PostDTO convert(Post input) {
        return new PostDTO(
                input.getId().toString(),
                input.getCategoryId().toString(),
                input.getUserId().toString(),
                input.getDescription().orElse(null),
                input.getBrand(),
                input.getModel(),
                input.getManufactureYear(),
                input.getMileage().orElse(null),
                input.getPrice()
        );
    }
}
