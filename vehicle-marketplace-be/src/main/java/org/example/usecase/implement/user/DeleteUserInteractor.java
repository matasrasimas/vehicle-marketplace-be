package org.example.usecase.implement.user;

import org.example.gateway.api.UserGateway;
import org.example.usecase.api.user.DeleteUserUseCase;

public class DeleteUserInteractor implements DeleteUserUseCase {
    private final UserGateway gateway;

    public DeleteUserInteractor(UserGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void delete(String userId) {
        gateway.delete(userId);
    }
}
