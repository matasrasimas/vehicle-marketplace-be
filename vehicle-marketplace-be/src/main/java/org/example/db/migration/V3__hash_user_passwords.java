package org.example.db.migration;

import org.example.DbConfigProvider;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;

import static org.example.generated.jooq.tables.Users.USERS;

public class V3__hash_user_passwords extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();
        DSLContext dsl = DSL.using(connection, SQLDialect.POSTGRES);

        String password1 = BCrypt.hashpw("password123", BCrypt.gensalt());
        String password2 = BCrypt.hashpw("janepassword", BCrypt.gensalt());
        String password3 = BCrypt.hashpw("roberthash", BCrypt.gensalt());
        String password4 = BCrypt.hashpw("_admin", BCrypt.gensalt());

        dsl.transaction(config -> {
            DSLContext transactionalDsl = DSL.using(config);

            transactionalDsl.update(USERS)
                    .set(USERS.PASSWORD_HASH, password1)
                    .where(USERS.USERNAME.eq("johndoe"))
                    .execute();

            transactionalDsl.update(USERS)
                    .set(USERS.PASSWORD_HASH, password2)
                    .where(USERS.USERNAME.eq("janesmith"))
                    .execute();

            transactionalDsl.update(USERS)
                    .set(USERS.PASSWORD_HASH, password3)
                    .where(USERS.USERNAME.eq("robertbrown"))
                    .execute();

            transactionalDsl.update(USERS)
                    .set(USERS.PASSWORD_HASH, password4)
                    .where(USERS.USERNAME.eq("admin"))
                    .execute();
        });

    }
}
