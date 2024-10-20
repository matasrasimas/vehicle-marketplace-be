package org.example.route.comment;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.common.RequestBodyValidator;
import org.example.model.dto.UpsertCommentDTO;
import org.example.usecase.api.comment.CreateCommentUseCase;

public class CreateCommentRoute {
    private final CreateCommentUseCase useCase;

    public CreateCommentRoute(CreateCommentUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String token = JwtParser.parse(context);
        RequestBodyValidator.validate(context, UpsertCommentDTO.class);
        UpsertCommentDTO commentDto = context.bodyAsClass(UpsertCommentDTO.class);
        useCase.create(commentDto, token);
        context.status(201);
    }
}
