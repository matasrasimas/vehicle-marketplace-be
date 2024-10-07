package org.example.route.user;

import io.javalin.http.Context;
import org.example.exception.ItemNotFoundException;
import org.example.usecase.api.user.RetrieveUserByIdUseCase;

import static org.example.common.RouteConstants.USER_ID_PATH;

public class RetrieveUserByIdRoute {
    private final RetrieveUserByIdUseCase useCase;

    public RetrieveUserByIdRoute(RetrieveUserByIdUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String userId = context.pathParam(USER_ID_PATH);
        useCase.retrieve(userId).ifPresentOrElse(
                context::json,
                () -> {
                    throw new ItemNotFoundException(
                            String.format("User by id [%s] not found", userId)
                    );
                }
        );
    }
}
