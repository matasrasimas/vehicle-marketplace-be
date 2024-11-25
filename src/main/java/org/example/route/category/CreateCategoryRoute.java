package org.example.route.category;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import io.javalin.validation.BodyValidator;
import org.example.exception.IncorrectRequestBodyException;
import org.example.model.dto.UpsertCategoryDTO;
import org.example.usecase.api.category.CreateCategoryUseCase;

import java.io.IOException;

public class CreateCategoryRoute {
    private final CreateCategoryUseCase useCase;

    public CreateCategoryRoute(CreateCategoryUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) throws IOException {
        UploadedFile uploadedImage = context.uploadedFile("image");

        byte[] imageBytes = uploadedImage != null ? uploadedImage.content().readAllBytes() : null;
        String title = context.formParam("title");

        if(title == null)
            throw new IncorrectRequestBodyException("Incorrect request body");

        UpsertCategoryDTO upsertCategoryDTO = new UpsertCategoryDTO(title, imageBytes);
        useCase.create(upsertCategoryDTO);
        context.status(201);
    }
}
