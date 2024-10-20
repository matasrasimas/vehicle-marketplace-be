package org.example.route.post;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.usecase.api.post.DeletePostUseCase;

import static org.example.common.RouteConstants.POST_ID;

public class DeletePostRoute {
    private final DeletePostUseCase useCase;

    public DeletePostRoute(DeletePostUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String token = JwtParser.parse(context);
        String postId = context.pathParam(POST_ID);
        useCase.delete(postId, token);
        context.status(204);
    }
}
