package org.example.usecase.implementation.jwt;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.example.model.dto.JwtUser;
import org.example.usecase.api.jwt.TokenGenerator;
import org.example.usecase.api.jwt.TokenUpdater;
import org.example.usecase.api.jwt.TokenValidator;

import java.text.ParseException;
import java.util.Optional;

import static java.util.Objects.isNull;

public class JwtUpdater implements TokenUpdater {

    private final TokenGenerator jwtGenerator;
    private final TokenValidator jwtValidator;

    public JwtUpdater(TokenGenerator jwtGenerator,
                      TokenValidator jwtValidator) {
        this.jwtGenerator = jwtGenerator;
        this.jwtValidator = jwtValidator;
    }

    @Override
    public Optional<String> update(String token) {
        return jwtValidator.validate(token)
                ? handleTokenUpdate(token)
                : Optional.empty();
    }

    private Optional<String> handleTokenUpdate(String token) {
        try {
            return extractUserFromTokenClaims(token)
                    .map(jwtGenerator::generate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<JwtUser> extractUserFromTokenClaims(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
        String userId = claimsSet.getClaim("userId").toString();
        String role = claimsSet.getClaim("role").toString();
        return isNull(userId) || isNull(role)
                ? Optional.empty()
                : Optional.of(new JwtUser(userId, role));
    }
}

