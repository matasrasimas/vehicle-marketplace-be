package org.example.usecase.implement.post;

import org.example.gateway.api.PostGateway;
import org.example.usecase.api.post.DeletePostUseCase;

public class DeletePostInteractor implements DeletePostUseCase {
    private final PostGateway gateway;

    public DeletePostInteractor(PostGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void delete(String postId) {
        gateway.delete(postId);
    }
}
