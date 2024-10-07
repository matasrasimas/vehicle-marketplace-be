package org.example.route.category;

import io.javalin.http.Context;
import org.example.common.RequestBodyValidator;
import org.example.common.UpsertStatus;
import org.example.model.dto.CategoryDTO;
import org.example.usecase.api.category.UpsertCategoryUseCase;

public class UpsertCategoryRoute {
    private final UpsertCategoryUseCase useCase;

    public UpsertCategoryRoute(UpsertCategoryUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        RequestBodyValidator.validate(context, CategoryDTO.class);
        CategoryDTO categoryDTO = context.bodyAsClass(CategoryDTO.class);
        if(useCase.upsert(categoryDTO).equals(UpsertStatus.RESOURCE_CREATED))
            context.status(201);
        else
            context.status(200);
    }
}
