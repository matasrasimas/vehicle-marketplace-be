package org.example.route.comment;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.usecase.api.comment.DeleteCommentUseCase;

import static org.example.common.RouteConstants.COMMENT_ID;

public class DeleteCommentRoute {
    private final DeleteCommentUseCase useCase;

    public DeleteCommentRoute(DeleteCommentUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String token = JwtParser.parse(context);
        String commentId = context.pathParam(COMMENT_ID);
        useCase.delete(commentId, token);
        context.status(204);
    }
}
