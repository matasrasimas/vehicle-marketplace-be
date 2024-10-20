package org.example.route.comment;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.common.RequestBodyValidator;
import org.example.model.dto.UpsertCommentDTO;
import org.example.usecase.api.comment.UpdateCommentUseCase;

import static org.example.common.RouteConstants.COMMENT_ID;

public class UpdateCommentRoute {
    private final UpdateCommentUseCase useCase;

    public UpdateCommentRoute(UpdateCommentUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String token = JwtParser.parse(context);
        RequestBodyValidator.validate(context, UpsertCommentDTO.class);
        UpsertCommentDTO commentDto = context.bodyAsClass(UpsertCommentDTO.class);
        String commentId = context.pathParam(COMMENT_ID);
        useCase.update(commentId, commentDto, token);
    }
}
