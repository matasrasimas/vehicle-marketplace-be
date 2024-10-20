package org.example.route.post;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.common.RequestBodyValidator;
import org.example.model.dto.UpsertPostDTO;
import org.example.usecase.api.post.UpdatePostUseCase;

import static org.example.common.RouteConstants.POST_ID;

public class UpdatePostRoute {
    private final UpdatePostUseCase useCase;

    public UpdatePostRoute(UpdatePostUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String token = JwtParser.parse(context);
        RequestBodyValidator.validate(context, UpsertPostDTO.class);
        UpsertPostDTO dto = context.bodyAsClass(UpsertPostDTO.class);
        String postId = context.pathParam(POST_ID);
        useCase.update(postId, dto, token);
    }
}
