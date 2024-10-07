package org.example.route.post;

import io.javalin.http.Context;
import org.example.usecase.api.post.RetrievePostsByCategoryIdUseCase;

import static org.example.common.RouteConstants.CATEGORY_ID_PATH;

public class RetrievePostsByCategoryIdRoute {
    private final RetrievePostsByCategoryIdUseCase useCase;

    public RetrievePostsByCategoryIdRoute(RetrievePostsByCategoryIdUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String categoryId = context.pathParam(CATEGORY_ID_PATH);
        context.json(useCase.retrieve(categoryId));
    }
}
