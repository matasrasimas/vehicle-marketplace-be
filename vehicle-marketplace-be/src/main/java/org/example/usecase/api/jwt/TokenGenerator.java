package org.example.usecase.api.jwt;

import org.example.model.dto.JwtUser;

public interface TokenGenerator {
    String generate(JwtUser user);
}
