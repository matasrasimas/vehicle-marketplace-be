package org.example.route.user;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.usecase.api.user.DeleteUserUseCase;

import static org.example.common.RouteConstants.USER_ID;


public class DeleteUserRoute {
    private final DeleteUserUseCase useCase;

    public DeleteUserRoute(DeleteUserUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String token = JwtParser.parse(context);
        String userId = context.pathParam(USER_ID);
        useCase.delete(userId, token);
        context.status(204);
    }
}
