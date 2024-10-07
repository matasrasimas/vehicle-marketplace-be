package org.example.route.user;

import io.javalin.http.Context;
import io.javalin.validation.BodyValidator;
import org.example.common.RequestBodyValidator;
import org.example.exception.IncorrectRequestBodyException;
import org.example.model.dto.UpdateUserDTO;
import org.example.usecase.api.user.UpdateUserUseCase;

public class UpdateUserRoute {
    private final UpdateUserUseCase useCase;

    public UpdateUserRoute(UpdateUserUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        RequestBodyValidator.validate(context, UpdateUserDTO.class);
        UpdateUserDTO updateUserDTO = context.bodyAsClass(UpdateUserDTO.class);
        useCase.update(updateUserDTO);
    }
}
