package org.example.route.comment;

import io.javalin.http.Context;
import org.example.usecase.api.comment.RetrieveCommentsByPostIdUseCase;

import static org.example.common.RouteConstants.POST_ID_PATH;

public class RetrieveCommentsByPostIdRoute {
    private final RetrieveCommentsByPostIdUseCase useCase;

    public RetrieveCommentsByPostIdRoute(RetrieveCommentsByPostIdUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String postId = context.pathParam(POST_ID_PATH);
        context.json(useCase.retrieve(postId));
    }
}
