package org.example.route.category;

import io.javalin.http.Context;
import org.example.common.RequestBodyValidator;
import org.example.model.dto.UpsertCategoryDTO;
import org.example.usecase.api.category.UpdateCategoryUseCase;

import static org.example.common.RouteConstants.CATEGORY_ID;

public class UpdateCategoryRoute {
    private final UpdateCategoryUseCase useCase;

    public UpdateCategoryRoute(UpdateCategoryUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        RequestBodyValidator.validate(context, UpsertCategoryDTO.class);
        UpsertCategoryDTO upsertCategoryDTO = context.bodyAsClass(UpsertCategoryDTO.class);
        String categoryId = context.pathParam(CATEGORY_ID);
        useCase.update(categoryId, upsertCategoryDTO);
    }
}
