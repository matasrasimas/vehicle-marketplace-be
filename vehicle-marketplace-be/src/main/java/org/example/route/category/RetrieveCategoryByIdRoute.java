package org.example.route.category;

import io.javalin.http.Context;
import org.example.exception.ItemNotFoundException;
import org.example.usecase.api.category.RetrieveCategoryByIdUseCase;

import static org.example.common.RouteConstants.CATEGORY_ID_PATH;

public class RetrieveCategoryByIdRoute {
    private final RetrieveCategoryByIdUseCase useCase;

    public RetrieveCategoryByIdRoute(RetrieveCategoryByIdUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String categoryId = context.pathParam(CATEGORY_ID_PATH);
        useCase.retrieve(categoryId)
                .ifPresentOrElse(
                        context::json,
                        () -> { throw new ItemNotFoundException(
                                String.format("Category by id [%s] not found", categoryId));
                        }
        );
    }
}
