package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

public class JOOQCodeGenerator {
    public static void main(String[] args) throws Exception {

        Dotenv dotenv = Dotenv.configure().load();

        Database database = new Database() // Change from PostgresDatabase to SQLServerDatabase
                .withIncludes(".*")
                .withExcludes("")
                .withInputSchema("dbo") // Default schema in SQL Server is usually "dbo"
                .withOutputSchemaToDefault(true);

        Target target = new Target()
                .withPackageName("org.example.generated.jooq")
                .withDirectory("src/main/java");

        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver") // Update the driver
                        .withUrl(dotenv.get("DATABASE_URL")) // Ensure your URL is SQL Server compatible
                        .withUser(dotenv.get("DATABASE_USERNAME"))
                        .withPassword(dotenv.get("DATABASE_PASSWORD")))
                .withGenerator(new Generator()
                        .withName("org.jooq.codegen.JavaGenerator")
                        .withDatabase(database)
                        .withTarget(target));

        GenerationTool.generate(configuration);
    }
}

