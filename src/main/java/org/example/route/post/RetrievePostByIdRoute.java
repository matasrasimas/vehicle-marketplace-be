package org.example.route.post;

import io.javalin.http.Context;
import org.example.exception.ItemNotFoundException;
import org.example.usecase.api.post.RetrievePostByIdUseCase;

import static org.example.common.RouteConstants.POST_ID;

public class RetrievePostByIdRoute {
    private final RetrievePostByIdUseCase useCase;

    public RetrievePostByIdRoute(RetrievePostByIdUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String postId = context.pathParam(POST_ID);
        useCase.retrieve(postId).ifPresentOrElse(
                context::json,
                () -> {
                    throw new ItemNotFoundException(
                            String.format("Post by id [%s] not found", postId));
                }
        );
    }
}
