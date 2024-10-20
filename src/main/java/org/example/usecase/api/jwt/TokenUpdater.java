package org.example.usecase.api.jwt;

import java.util.Optional;

public interface TokenUpdater {
    Optional<String> update(String token);
}
