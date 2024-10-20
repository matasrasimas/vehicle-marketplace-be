package org.example.route.category;

import io.javalin.http.Context;
import org.example.common.RequestBodyValidator;
import org.example.model.dto.UpsertCategoryDTO;
import org.example.usecase.api.category.CreateCategoryUseCase;

public class CreateCategoryRoute {
    private final CreateCategoryUseCase useCase;

    public CreateCategoryRoute(CreateCategoryUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        RequestBodyValidator.validate(context, UpsertCategoryDTO.class);
        UpsertCategoryDTO upsertCategoryDTO = context.bodyAsClass(UpsertCategoryDTO.class);
        useCase.create(upsertCategoryDTO);
        context.status(201);
    }
}
