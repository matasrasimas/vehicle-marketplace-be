package org.example.route.comment;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.exception.IncorrectRequestBodyException;
import org.example.model.dto.UpsertCommentDTO;
import org.example.usecase.api.comment.UpdateCommentUseCase;

import static org.example.common.RouteConstants.COMMENT_ID;

public class UpdateCommentRoute {
    private final UpdateCommentUseCase useCase;

    public UpdateCommentRoute(UpdateCommentUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String postId = context.formParam("postId");
        String content = context.formParam("content");
        String rating = context.formParam("rating");

        if(postId == null || content == null || rating == null)
            throw new IncorrectRequestBodyException("Incorrect request body");

        UpsertCommentDTO commentDto = new UpsertCommentDTO(postId, content, Integer.parseInt(rating));

        String token = JwtParser.parse(context);

        String commentId = context.pathParam(COMMENT_ID);

        useCase.update(commentId, commentDto, token);
    }
}
