package org.example.db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;

public class V1__create_table extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();
        DSLContext dsl = DSL.using(connection, SQLDialect.DEFAULT);

        dsl.execute("""
                CREATE TABLE categories (
                    id UNIQUEIDENTIFIER PRIMARY KEY,
                    title NVARCHAR(MAX) NOT NULL,
                    image VARBINARY(MAX) NULL
                );
                
                CREATE TABLE users (
                    id UNIQUEIDENTIFIER PRIMARY KEY,
                    first_name NVARCHAR(MAX) NOT NULL,
                    last_name NVARCHAR(MAX) NOT NULL,
                    phone_number NVARCHAR(MAX) NOT NULL,
                    username NVARCHAR(MAX) NOT NULL,
                    password_hash NVARCHAR(MAX) NOT NULL,
                    role NVARCHAR(MAX) NOT NULL
                );
                
                CREATE TABLE posts (
                    id UNIQUEIDENTIFIER PRIMARY KEY,
                    category_id UNIQUEIDENTIFIER NOT NULL,
                    user_id UNIQUEIDENTIFIER NOT NULL,
                    description NVARCHAR(MAX),
                    brand NVARCHAR(MAX) NOT NULL,
                    model NVARCHAR(MAX) NOT NULL,
                    manufacture_year INTEGER NOT NULL,
                    mileage FLOAT, -- Use FLOAT instead of DOUBLE PRECISION
                    price FLOAT NOT NULL, -- Same as above
                    image VARBINARY(MAX) NULL,
                    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE NO ACTION ON UPDATE NO ACTION
                );
                
                CREATE TABLE comments (
                    id UNIQUEIDENTIFIER PRIMARY KEY,
                    post_id UNIQUEIDENTIFIER NOT NULL,
                    user_id UNIQUEIDENTIFIER NOT NULL,
                    content NVARCHAR(MAX) NOT NULL,
                    rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
                    created_at DATETIME NOT NULL DEFAULT GETDATE(),
                    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE NO ACTION ON UPDATE NO ACTION
                );
                """);
    }
}
