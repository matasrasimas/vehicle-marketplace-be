package org.example.route.category;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.example.common.RequestBodyValidator;
import org.example.exception.IncorrectRequestBodyException;
import org.example.model.dto.UpsertCategoryDTO;
import org.example.usecase.api.category.UpdateCategoryUseCase;

import java.io.IOException;

import static org.example.common.RouteConstants.CATEGORY_ID;

public class UpdateCategoryRoute {
    private final UpdateCategoryUseCase useCase;

    public UpdateCategoryRoute(UpdateCategoryUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) throws IOException {
        UploadedFile uploadedImage = context.uploadedFile("image");

        byte[] imageBytes = uploadedImage != null ? uploadedImage.content().readAllBytes() : null;
        String title = context.formParam("title");

        if(title == null)
            throw new IncorrectRequestBodyException("Incorrect request body");

        UpsertCategoryDTO upsertCategoryDTO = new UpsertCategoryDTO(title, imageBytes);

        String categoryId = context.pathParam(CATEGORY_ID);

        useCase.update(categoryId, upsertCategoryDTO);
    }
}
