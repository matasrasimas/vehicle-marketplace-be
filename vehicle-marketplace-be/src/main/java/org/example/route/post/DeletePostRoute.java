package org.example.route.post;

import io.javalin.http.Context;
import org.example.usecase.api.post.DeletePostUseCase;

import static org.example.common.RouteConstants.POST_ID_PATH;

public class DeletePostRoute {
    private final DeletePostUseCase useCase;

    public DeletePostRoute(DeletePostUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String postId = context.pathParam(POST_ID_PATH);
        useCase.delete(postId);
        context.status(204);
    }
}
