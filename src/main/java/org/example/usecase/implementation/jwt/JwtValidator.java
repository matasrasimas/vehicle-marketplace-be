package org.example.usecase.implementation.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.JWTProcessor;
import org.example.exception.ExpiredJwtException;
import org.example.model.domain.Role;
import org.example.model.dto.JwtUser;
import org.example.usecase.api.jwt.TokenValidator;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class JwtValidator implements TokenValidator {

    private final JWTProcessor<SecurityContext> jwtProcessor;

    public JwtValidator(JWTProcessor<SecurityContext> jwtProcessor) {
        this.jwtProcessor = jwtProcessor;
    }

    @Override
    public boolean validate(String token) {
        try {
            jwtProcessor.process(token, null);
            return true;
        } catch (ParseException | JOSEException | BadJOSEException e) {
            return e.getMessage().equals("Expired JWT");
        }
    }

    @Override
    public Optional<JwtUser> getUserFromJwt(String token) {
        try {
            JWTClaimsSet claimsSet = jwtProcessor.process(token, null);
            String userId = claimsSet.getClaim("userId").toString();
            String role = claimsSet.getClaim("role").toString();
            return nonNull(userId) && nonNull(role)
                    ? Optional.of(new JwtUser(userId, role))
                    : Optional.empty();
        } catch (ParseException | JOSEException | BadJOSEException e) {
            if(e.getMessage().equals("Expired JWT"))
                throw new ExpiredJwtException("The provided jwt is expired");
            return Optional.empty();
        }
    }

    @Override
    public boolean validate(String token, Role roleToValidate) {
        try {
            JWTClaimsSet claimsSet = jwtProcessor.process(token, null);

            Date expirationDate = claimsSet.getExpirationTime();
            if (expirationDate == null || expirationDate.before(new Date()))
                throw new ExpiredJwtException("The provided jwt is expired");

            String role = claimsSet.getClaim("role").toString();
            if (role.equals("ADMIN"))
                return true;
            return role.equals(roleToValidate.toString());
        } catch (ParseException | JOSEException | BadJOSEException e) {
            if(e.getMessage().equals("Expired JWT"))
                throw new ExpiredJwtException("The provided jwt is expired");
            return false;
        }
    }
}
