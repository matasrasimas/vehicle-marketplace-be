package org.example.route.post;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.common.RequestBodyValidator;
import org.example.model.dto.UpsertPostDTO;
import org.example.usecase.api.post.CreatePostUseCase;

public class CreatePostRoute {
    private final CreatePostUseCase useCase;

    public CreatePostRoute(CreatePostUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String token = JwtParser.parse(context);
        RequestBodyValidator.validate(context, UpsertPostDTO.class);
        UpsertPostDTO dto = context.bodyAsClass(UpsertPostDTO.class);
        useCase.create(dto, token);
        context.status(201);
    }
}
