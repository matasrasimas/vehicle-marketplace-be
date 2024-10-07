package org.example.route.comment;

import io.javalin.http.Context;
import org.example.common.RequestBodyValidator;
import org.example.common.UpsertStatus;
import org.example.model.dto.CommentDTO;
import org.example.usecase.api.comment.UpsertCommentUseCase;

public class UpsertCommentRoute {
    private final UpsertCommentUseCase useCase;

    public UpsertCommentRoute(UpsertCommentUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        RequestBodyValidator.validate(context, CommentDTO.class);
        CommentDTO commentDto = context.bodyAsClass(CommentDTO.class);
        if(useCase.upsert(commentDto).equals(UpsertStatus.RESOURCE_CREATED))
            context.status(201);
        else
            context.status(200);
    }
}
