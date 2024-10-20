package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.ThreadLocalTransactionProvider;

public class DbConfigProvider {
    public static Configuration getConfiguration() {
        Dotenv dotenv = Dotenv.configure().load();

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dotenv.get("DATABASE_URL"));
        hikariConfig.setUsername(dotenv.get("DATABASE_USERNAME"));
        hikariConfig.setPassword(dotenv.get("DATABASE_PASSWORD"));
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        return new DefaultConfiguration()
                .set(new ThreadLocalTransactionProvider(new DataSourceConnectionProvider(hikariDataSource)))
                .set(SQLDialect.POSTGRES);
    }
}
