package org.example.route.comment;

import io.javalin.http.Context;
import org.example.usecase.api.comment.RetrieveCommentsUseCase;

public class RetrieveCommentsRoute {
    private final RetrieveCommentsUseCase useCase;

    public RetrieveCommentsRoute(RetrieveCommentsUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        context.json(useCase.retrieve());
    }
}
