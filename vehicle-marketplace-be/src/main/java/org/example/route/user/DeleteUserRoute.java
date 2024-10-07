package org.example.route.user;

import io.javalin.http.Context;
import org.example.usecase.api.user.DeleteUserUseCase;

import static org.example.common.RouteConstants.USER_ID_PATH;


public class DeleteUserRoute {
    private final DeleteUserUseCase useCase;

    public DeleteUserRoute(DeleteUserUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String userId = context.pathParam(USER_ID_PATH);
        useCase.delete(userId);
        context.status(204);
    }
}
