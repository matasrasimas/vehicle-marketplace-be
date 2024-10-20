package org.example.gateway.api;

import org.example.model.domain.CreateUser;
import org.example.model.domain.UpdateUser;
import org.example.model.domain.User;
import org.example.model.domain.UserLogin;
import org.example.model.dto.UserLoginDTO;

import java.util.List;
import java.util.Optional;

public interface UserGateway {
    List<User> retrieve();
    Optional<User> retrieveById(String userId);
    void create(CreateUser input);
    void update(String userId, UpdateUser input);
    void delete(String userId);
    Optional<User> login(UserLogin input);
}
