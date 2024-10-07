package org.example.route.user;

import io.javalin.http.Context;
import org.example.usecase.api.user.RetrieveUsersUseCase;

public class RetrieveUsersRoute {
    private final RetrieveUsersUseCase useCase;

    public RetrieveUsersRoute(RetrieveUsersUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        context.json(useCase.retrieve());
    }
}
