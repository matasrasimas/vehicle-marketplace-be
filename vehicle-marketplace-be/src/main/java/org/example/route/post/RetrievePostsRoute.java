package org.example.route.post;

import io.javalin.http.Context;
import org.example.usecase.api.post.RetrievePostsUseCase;

public class RetrievePostsRoute {
    private final RetrievePostsUseCase useCase;

    public RetrievePostsRoute(RetrievePostsUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        context.json(useCase.retrieve());
    }
}
