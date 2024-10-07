package org.example.route.user;

import io.javalin.http.Context;
import org.example.common.RequestBodyValidator;
import org.example.model.dto.CreateUserDTO;
import org.example.usecase.api.user.CreateUserUseCase;

public class CreateUserRoute {
    private final CreateUserUseCase useCase;

    public CreateUserRoute(CreateUserUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        RequestBodyValidator.validate(context, CreateUserDTO.class);
        CreateUserDTO createUserDTO = context.bodyAsClass(CreateUserDTO.class);
        useCase.create(createUserDTO);
        context.status(201);
    }
}
