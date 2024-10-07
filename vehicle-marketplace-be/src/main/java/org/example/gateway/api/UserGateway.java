package org.example.gateway.api;

import org.example.model.domain.CreateUser;
import org.example.model.domain.UpdateUser;
import org.example.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserGateway {
    List<User> retrieve();
    Optional<User> retrieveById(String userId);
    void create(CreateUser input);
    void update(UpdateUser input);
    void delete(String userId);
}
