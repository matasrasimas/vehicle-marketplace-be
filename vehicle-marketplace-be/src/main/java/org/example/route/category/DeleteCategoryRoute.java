package org.example.route.category;

import io.javalin.http.Context;
import org.example.usecase.api.category.DeleteCategoryUseCase;

import static org.example.common.RouteConstants.CATEGORY_ID_PATH;

public class DeleteCategoryRoute {
    private final DeleteCategoryUseCase useCase;

    public DeleteCategoryRoute(DeleteCategoryUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String categoryId = context.pathParam(CATEGORY_ID_PATH);
        useCase.delete(categoryId);
        context.status(204);
    }
}
