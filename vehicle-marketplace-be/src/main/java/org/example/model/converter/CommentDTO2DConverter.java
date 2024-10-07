package org.example.model.converter;

import org.example.model.domain.Comment;
import org.example.model.dto.CommentDTO;

import java.util.UUID;

public class CommentDTO2DConverter {
    public Comment convert(CommentDTO input) {
        return new Comment(
                UUID.fromString(input.id()),
                UUID.fromString(input.postId()),
                UUID.fromString(input.userId()),
                input.content(),
                input.rating()
        );
    }
}
