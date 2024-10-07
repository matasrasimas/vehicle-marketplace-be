package org.example.route.post;

import io.javalin.http.Context;
import org.example.usecase.api.post.RetrievePostsByUserIdUseCase;

import static org.example.common.RouteConstants.USER_ID_PATH;

public class RetrievePostsByUserIdRoute {
    private final RetrievePostsByUserIdUseCase useCase;

    public RetrievePostsByUserIdRoute(RetrievePostsByUserIdUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String userId = context.pathParam(USER_ID_PATH);
        context.json(useCase.retrieve(userId));
    }
}
