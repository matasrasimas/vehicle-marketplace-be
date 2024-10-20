package org.example.model.converter;

import org.example.model.domain.UpsertComment;
import org.example.model.dto.UpsertCommentDTO;

import java.util.UUID;

public class UpsertCommentDTO2DConverter {
    public UpsertComment convert(UpsertCommentDTO input) {
        return new UpsertComment(
                UUID.fromString(input.postId()),
                input.content(),
                input.rating()
        );
    }
}
