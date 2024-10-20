package org.example.route.auth;

import io.javalin.http.Context;
import org.example.common.RequestBodyValidator;
import org.example.exception.UnauthorizedException;
import org.example.model.dto.CreateUserDTO;
import org.example.model.dto.UserLoginDTO;
import org.example.usecase.api.auth.LoginUseCase;

import java.util.HashMap;
import java.util.Map;

public class LoginRoute {
    private final LoginUseCase useCase;

    public LoginRoute(LoginUseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(Context context) {
        RequestBodyValidator.validate(context, UserLoginDTO.class);
        UserLoginDTO userLoginDTO = context.bodyAsClass(UserLoginDTO.class);
        Map<String, String> response = new HashMap<>();
        useCase.login(userLoginDTO)
                .ifPresentOrElse(
                        jwt -> {
                            response.put("jwt", jwt);
                            context.status(200).json(response);
                        },
                        () -> {
                            throw new UnauthorizedException("Incorrect username or password");
                        }
                );
    }
}
