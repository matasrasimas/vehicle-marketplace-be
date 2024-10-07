package org.example.route.post;

import io.javalin.http.Context;
import org.example.common.RequestBodyValidator;
import org.example.common.UpsertStatus;
import org.example.model.dto.PostDTO;
import org.example.usecase.api.post.UpsertPostUseCase;

public class UpsertPostRoute {
    private final UpsertPostUseCase useCase;

    public UpsertPostRoute(UpsertPostUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        RequestBodyValidator.validate(context, PostDTO.class);
        PostDTO dto = context.bodyAsClass(PostDTO.class);
        if(useCase.upsert(dto).equals(UpsertStatus.RESOURCE_CREATED))
            context.status(201);
        else
            context.status(200);
    }
}
