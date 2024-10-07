package org.example.gateway.postgres;

import org.example.gateway.api.UserGateway;
import org.example.generated.jooq.tables.records.UsersRecord;
import org.example.model.domain.CreateUser;
import org.example.model.domain.Role;
import org.example.model.domain.UpdateUser;
import org.example.model.domain.User;
import org.jooq.DSLContext;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.generated.jooq.tables.Users.USERS;

public class PostgresUserGateway implements UserGateway {
    private final DSLContext dsl;

    public PostgresUserGateway(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public List<User> retrieve() {
        return dsl.selectFrom(USERS)
                .fetch(this::buildUser);
    }

    @Override
    public Optional<User> retrieveById(String userId) {
        return dsl.selectFrom(USERS)
                .where(USERS.ID.eq(UUID.fromString(userId)))
                .fetchOptional(this::buildUser);
    }

    @Override
    public void create(CreateUser input) {
        dsl.insertInto(USERS)
                .set(USERS.ID, UUID.randomUUID())
                .set(USERS.FIRST_NAME, input.firstName())
                .set(USERS.LAST_NAME, input.lastName())
                .set(USERS.PHONE_NUMBER, input.phoneNumber())
                .set(USERS.USERNAME, input.username())
                .set(USERS.PASSWORD_HASH, BCrypt.hashpw(input.password(), BCrypt.gensalt()))
                .set(USERS.ROLE, input.role().toString())
                .execute();
    }

    @Override
    public void update(UpdateUser input) {
        dsl.update(USERS)
                .set(USERS.FIRST_NAME, input.firstName())
                .set(USERS.LAST_NAME, input.lastName())
                .set(USERS.PHONE_NUMBER, input.phoneNumber())
                .set(USERS.USERNAME, input.username())
                .set(USERS.ROLE, input.role().toString())
                .where(USERS.ID.eq(input.id()))
                .execute();
    }

    @Override
    public void delete(String userId) {
        dsl.deleteFrom(USERS)
                .where(USERS.ID.eq(UUID.fromString(userId)))
                .execute();
    }

    private User buildUser(UsersRecord record) {
        return new User(
                record.getId(),
                record.getFirstName(),
                record.getLastName(),
                record.getPhoneNumber(),
                record.getUsername(),
                Role.valueOf(record.getRole())
        );
    }
}
