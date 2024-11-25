package org.example.route.post;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.example.common.JwtParser;
import org.example.common.RequestBodyValidator;
import org.example.exception.IncorrectRequestBodyException;
import org.example.model.dto.UpsertPostDTO;
import org.example.usecase.api.post.UpdatePostUseCase;

import java.io.IOException;

import static org.example.common.RouteConstants.POST_ID;

public class UpdatePostRoute {
    private final UpdatePostUseCase useCase;

    public UpdatePostRoute(UpdatePostUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) throws IOException {
        UploadedFile uploadedImage = context.uploadedFile("image");

        byte[] imageBytes = uploadedImage != null ? uploadedImage.content().readAllBytes() : null;
        String categoryId = context.formParam("categoryId");
        String description = context.formParam("description");
        String brand = context.formParam("brand");
        String model = context.formParam("model");
        String manufactureYear = context.formParam("manufactureYear");
        String mileage = context.formParam("mileage");
        String price = context.formParam("price");

        if(categoryId == null
                || brand == null
                || model == null
                || manufactureYear == null
                || price == null)
            throw new IncorrectRequestBodyException("Incorrect request body");

        UpsertPostDTO dto = new UpsertPostDTO(
                categoryId,
                description,
                brand,
                model,
                Integer.parseInt(manufactureYear),
                mileage != null ? Double.parseDouble(mileage) : null,
                Double.parseDouble(price),
                imageBytes
        );

        String token = JwtParser.parse(context);

        String postId = context.pathParam(POST_ID);

        useCase.update(postId, dto, token);
    }
}
