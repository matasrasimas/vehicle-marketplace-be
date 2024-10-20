package org.example.usecase.implementation.jwt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.example.model.dto.JwtUser;
import org.example.usecase.api.jwt.TokenGenerator;

import java.time.Instant;
import java.util.Date;

public class JwtGenerator implements TokenGenerator {

    private final String signingKey;

    public JwtGenerator(String signingKey) {
        this.signingKey = signingKey;
    }

    @Override
    public String generate(JwtUser user) {
        try {
            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).build();

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject("subject")
                    .issuer("issuer")
                    .expirationTime(Date.from(Instant.now().plusSeconds(3600)))
                    .claim("userId", user.id())
                    .claim("role", user.role())
                    .build();

            SignedJWT signedJWT = new SignedJWT(header, claimsSet);

            JWSSigner signer = new MACSigner(signingKey);

            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
