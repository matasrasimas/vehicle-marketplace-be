package org.example.usecase.api.comment;

public interface DeleteCommentUseCase {
    void delete(String commentId, String token);
}
