package org.example.route.category;

import io.javalin.http.Context;
import org.example.usecase.api.category.RetrieveCategoriesUseCase;

public class RetrieveCategoriesRoute {
    private final RetrieveCategoriesUseCase useCase;

    public RetrieveCategoriesRoute(RetrieveCategoriesUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        context.json(useCase.retrieve());
    }
}
