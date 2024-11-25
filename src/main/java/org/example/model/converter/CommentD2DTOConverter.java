package org.example.model.converter;

import org.example.model.domain.Comment;
import org.example.model.dto.CommentDTO;

public class CommentD2DTOConverter {
    public CommentDTO convert(Comment input) {
        return new CommentDTO(
                input.id().toString(),
                input.postId().toString(),
                input.userId().toString(),
                input.content(),
                input.rating(),
                input.createdAt()
        );
    }
}
