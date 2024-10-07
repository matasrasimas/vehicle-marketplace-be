package org.example.route.comment;

import io.javalin.http.Context;
import org.example.usecase.api.comment.DeleteCommentUseCase;

import static org.example.common.RouteConstants.COMMENT_ID_PATH;

public class DeleteCommentRoute {
    private final DeleteCommentUseCase useCase;

    public DeleteCommentRoute(DeleteCommentUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String commentId = context.pathParam(COMMENT_ID_PATH);
        useCase.delete(commentId);
        context.status(204);
    }
}
