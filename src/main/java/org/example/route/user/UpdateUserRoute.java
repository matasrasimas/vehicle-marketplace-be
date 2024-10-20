package org.example.route.user;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.common.RequestBodyValidator;
import org.example.model.dto.UpdateUserDTO;
import org.example.usecase.api.user.UpdateUserUseCase;

import static org.example.common.RouteConstants.USER_ID;

public class UpdateUserRoute {
    private final UpdateUserUseCase useCase;

    public UpdateUserRoute(UpdateUserUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String token = JwtParser.parse(context);
        RequestBodyValidator.validate(context, UpdateUserDTO.class);
        UpdateUserDTO updateUserDTO = context.bodyAsClass(UpdateUserDTO.class);
        String userId = context.pathParam(USER_ID);
        useCase.update(userId, updateUserDTO, token);
    }
}
