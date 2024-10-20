package org.example.route.user;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.exception.ItemNotFoundException;
import org.example.usecase.api.user.RetrieveUserByIdUseCase;

import static org.example.common.RouteConstants.USER_ID;

public class RetrieveUserByIdRoute {
    private final RetrieveUserByIdUseCase useCase;

    public RetrieveUserByIdRoute(RetrieveUserByIdUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String token = JwtParser.parse(context);
        String userId = context.pathParam(USER_ID);
        useCase.retrieve(userId, token).ifPresentOrElse(
                context::json,
                () -> {
                    throw new ItemNotFoundException(
                            String.format("User by id [%s] not found", userId)
                    );
                }
        );
    }
}
