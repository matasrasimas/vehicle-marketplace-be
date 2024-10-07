package org.example.route.comment;

import io.javalin.http.Context;
import org.example.exception.ItemNotFoundException;
import org.example.usecase.api.comment.RetrieveCommentByIdUseCase;

import static org.example.common.RouteConstants.COMMENT_ID_PATH;

public class RetrieveCommentByIdRoute {
    private final RetrieveCommentByIdUseCase useCase;

    public RetrieveCommentByIdRoute(RetrieveCommentByIdUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String commentId = context.pathParam(COMMENT_ID_PATH);
        useCase.retrieve(commentId).ifPresentOrElse(
                context::json,
                () -> {
                    throw new ItemNotFoundException(
                            String.format("Comment by id [%s] not found", commentId)
                    );
                }
        );
    }
}
