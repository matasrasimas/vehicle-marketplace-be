package org.example.route.auth;

import io.javalin.http.Context;
import org.example.common.JwtParser;
import org.example.usecase.api.auth.ExtractUserFromJwtUseCase;

public class ExtractUserFromJwtRoute {
    private final ExtractUserFromJwtUseCase useCase;

    public ExtractUserFromJwtRoute(ExtractUserFromJwtUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        String jwt = JwtParser.parse(context);
        context.json(useCase.extract(jwt));
    }
}
