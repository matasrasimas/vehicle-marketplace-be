package org.example.model.converter;

import org.example.model.domain.Post;
import org.example.model.dto.PostDTO;

import java.util.Base64;

public class PostD2DTOConverter {
    public PostDTO convert(Post input) {
        String base64Image = input.getImage() != null ?
                Base64.getEncoder().encodeToString(input.getImage()) : null;
        return new PostDTO(
                input.getId().toString(),
                input.getCategoryId().toString(),
                input.getUserId().toString(),
                input.getDescription().orElse(null),
                input.getBrand(),
                input.getModel(),
                input.getManufactureYear(),
                input.getMileage().orElse(null),
                input.getPrice(),
                base64Image
        );
    }
}
