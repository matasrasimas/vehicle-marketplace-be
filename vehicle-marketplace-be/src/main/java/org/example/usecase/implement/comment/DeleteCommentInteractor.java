package org.example.usecase.implement.comment;

import org.example.gateway.api.CommentGateway;
import org.example.usecase.api.comment.DeleteCommentUseCase;

public class DeleteCommentInteractor implements DeleteCommentUseCase {
    private final CommentGateway gateway;

    public DeleteCommentInteractor(CommentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void delete(String commentId) {
        gateway.delete(commentId);
    }
}
