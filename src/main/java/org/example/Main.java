package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.gateway.api.CategoryGateway;
import org.example.gateway.api.CommentGateway;
import org.example.gateway.api.PostGateway;
import org.example.gateway.api.UserGateway;
import org.example.gateway.postgres.PostgresCategoryGateway;
import org.example.gateway.postgres.PostgresCommentGateway;
import org.example.gateway.postgres.PostgresPostGateway;
import org.example.gateway.postgres.PostgresUserGateway;
import org.example.model.converter.*;
import org.example.route.auth.AuthenticateForAdminRoute;
import org.example.route.auth.AuthenticateForUserRoute;
import org.example.route.auth.LoginRoute;
import org.example.route.auth.ReauthenticateRoute;
import org.example.route.category.*;
import org.example.route.comment.*;
import org.example.route.post.*;
import org.example.route.user.*;
import org.example.usecase.api.auth.AuthenticateUseCase;
import org.example.usecase.api.auth.LoginUseCase;
import org.example.usecase.api.auth.ReauthenticateUseCase;
import org.example.usecase.api.category.*;
import org.example.usecase.api.comment.*;
import org.example.usecase.api.jwt.TokenGenerator;
import org.example.usecase.api.jwt.TokenUpdater;
import org.example.usecase.api.jwt.TokenValidator;
import org.example.usecase.api.post.*;
import org.example.usecase.api.user.*;
import org.example.usecase.implementation.auth.AuthenticateInteractor;
import org.example.usecase.implementation.auth.LoginInteractor;
import org.example.usecase.implementation.auth.ReauthenticateInteractor;
import org.example.usecase.implementation.category.*;
import org.example.usecase.implementation.comment.*;
import org.example.usecase.implementation.jwt.JwtGenerator;
import org.example.usecase.implementation.jwt.JwtProcessorBuilder;
import org.example.usecase.implementation.jwt.JwtUpdater;
import org.example.usecase.implementation.jwt.JwtValidator;
import org.example.usecase.implementation.post.*;
import org.example.usecase.implementation.user.*;
import org.flywaydb.core.Flyway;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.ThreadLocalTransactionProvider;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
        Dotenv dotenv = Dotenv.configure().load();

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dotenv.get("DATABASE_URL"));
        hikariConfig.setUsername(dotenv.get("DATABASE_USERNAME"));
        hikariConfig.setPassword(dotenv.get("DATABASE_PASSWORD"));
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        Configuration jooqConfig = new DefaultConfiguration()
                .set(new ThreadLocalTransactionProvider(new DataSourceConnectionProvider(hikariDataSource)))
                .set(SQLDialect.DEFAULT);
        DSLContext jooqDSLContext = DSL.using(jooqConfig);

        Flyway.configure()
                .locations("classpath:db/migration", "org/example/db/migration")
                .dataSource(hikariDataSource)
                .load()
                .migrate();

        CategoryD2DTOConverter categoryD2DTOConverter = new CategoryD2DTOConverter();
        UpsertCategoryDTO2DConverter upsertCategoryDTO2DConverter = new UpsertCategoryDTO2DConverter();
        PostD2DTOConverter postD2DTOConverter = new PostD2DTOConverter();
        UpsertPostDTO2DConverter upsertPostDTO2DConverter = new UpsertPostDTO2DConverter();
        CommentD2DTOConverter commentD2DTOConverter = new CommentD2DTOConverter();
        UpsertCommentDTO2DConverter upsertCommentDTO2DConverter = new UpsertCommentDTO2DConverter();
        UserD2DTOConverter userD2DTOConverter = new UserD2DTOConverter();
        CreateUserDTO2DConverter createUserDTO2DConverter = new CreateUserDTO2DConverter();
        UpdateUserDTO2DConverter updateUserDTO2DConverter = new UpdateUserDTO2DConverter();
        UserLoginDTO2DConverter userLoginDTO2DConverter = new UserLoginDTO2DConverter();

        CategoryGateway categoryGateway = new PostgresCategoryGateway(jooqDSLContext);
        PostGateway postGateway = new PostgresPostGateway(jooqDSLContext);
        CommentGateway commentGateway = new PostgresCommentGateway(jooqDSLContext);
        UserGateway userGateway = new PostgresUserGateway(jooqDSLContext);

        TokenGenerator tokenGenerator = new JwtGenerator(dotenv.get("SIGNING_KEY"));
        JwtProcessorBuilder jwtProcessorBuilder = new JwtProcessorBuilder(dotenv.get("SIGNING_KEY"));
        TokenValidator tokenValidator = new JwtValidator(jwtProcessorBuilder.buildProcess());
        TokenUpdater tokenUpdater = new JwtUpdater(tokenGenerator, tokenValidator);

        RetrieveCategoriesUseCase retrieveCategoriesUseCase = new RetrieveCategoriesInteractor(
                categoryGateway,
                categoryD2DTOConverter);
        RetrieveCategoryByIdUseCase retrieveCategoryByIdUseCase = new RetrieveCategoryByIdInteractor(
                categoryGateway,
                categoryD2DTOConverter
        );
        CreateCategoryUseCase upsertCategoryUseCase = new CreateCategoryInteractor(
                categoryGateway,
                upsertCategoryDTO2DConverter
        );
        UpdateCategoryUseCase updateCategoryUseCase = new UpdateCategoryInteractor(
                categoryGateway,
                upsertCategoryDTO2DConverter
        );
        DeleteCategoryUseCase deleteCategoryUseCase = new DeleteCategoryInteractor(
                categoryGateway
        );
        RetrievePostsUseCase retrievePostsUseCase = new RetrievePostsInteractor(
                postGateway,
                postD2DTOConverter
        );
        RetrievePostByIdUseCase retrievePostByIdUseCase = new RetrievePostByIdInteractor(
                postGateway,
                postD2DTOConverter
        );
        RetrievePostsByCategoryIdUseCase retrievePostsByCategoryIdUseCase = new RetrievePostsByCategoryIdInteractor(
                postGateway,
                postD2DTOConverter
        );
        RetrievePostsByUserIdUseCase retrievePostsByUserIdUseCase = new RetrievePostsByUserIdInteractor(
                postGateway,
                postD2DTOConverter
        );
        CreatePostUseCase createPostUseCase = new CreatePostInteractor(
                postGateway,
                upsertPostDTO2DConverter,
                tokenValidator
        );
        UpdatePostUseCase updatePostUseCase = new UpdatePostInteractor(
                postGateway,
                upsertPostDTO2DConverter,
                tokenValidator
        );
        DeletePostUseCase deletePostUseCase = new DeletePostInteractor(
                postGateway,
                tokenValidator
        );
        RetrieveCommentsUseCase retrieveCommentsUseCase = new RetrieveCommentsInteractor(
                commentGateway,
                commentD2DTOConverter
        );
        RetrieveCommentByIdUseCase retrieveCommentByIdUseCase = new RetrieveCommentByIdInteractor(
                commentGateway,
                commentD2DTOConverter
        );
        RetrieveCommentsByPostIdUseCase retrieveCommentsByPostIdUseCase = new RetrieveCommentsByPostIdInteractor(
                commentGateway,
                commentD2DTOConverter
        );
        CreateCommentUseCase createCommentUseCase = new CreateCommentInteractor(
                commentGateway,
                upsertCommentDTO2DConverter,
                tokenValidator
        );
        UpdateCommentUseCase updateCommentUseCase = new UpdateCommentInteractor(
                commentGateway,
                upsertCommentDTO2DConverter,
                tokenValidator
        );
        DeleteCommentUseCase deleteCommentUseCase = new DeleteCommentInteractor(
                commentGateway,
                tokenValidator
        );
        RetrieveUsersUseCase retrieveUsersUseCase = new RetrieveUsersInteractor(
                userGateway,
                userD2DTOConverter
        );
        RetrieveUserByIdUseCase retrieveUserByIdUseCase = new RetrieveUserByIdInteractor(
                userGateway,
                userD2DTOConverter,
                tokenValidator
        );
        CreateUserUseCase createUserUseCase = new CreateUserInteractor(
                userGateway,
                createUserDTO2DConverter
        );
        UpdateUserUseCase updateUserUseCase = new UpdateUserInteractor(
                userGateway,
                updateUserDTO2DConverter,
                tokenValidator
        );
        DeleteUserUseCase deleteUserUseCase = new DeleteUserInteractor(
                userGateway,
                tokenValidator
        );

        AuthenticateUseCase authenticateUseCase = new AuthenticateInteractor(tokenValidator);
        LoginUseCase loginUseCase = new LoginInteractor(
                userGateway,
                userLoginDTO2DConverter,
                tokenGenerator
        );
        ReauthenticateUseCase reauthenticateUseCase = new ReauthenticateInteractor(tokenUpdater);

        RetrieveCategoriesRoute retrieveCategoriesRoute = new RetrieveCategoriesRoute(retrieveCategoriesUseCase);
        RetrieveCategoryByIdRoute retrieveCategoryByIdRoute = new RetrieveCategoryByIdRoute(retrieveCategoryByIdUseCase);
        CreateCategoryRoute createCategoryRoute = new CreateCategoryRoute(upsertCategoryUseCase);
        UpdateCategoryRoute updateCategoryRoute = new UpdateCategoryRoute(updateCategoryUseCase);
        DeleteCategoryRoute deleteCategoryRoute = new DeleteCategoryRoute(deleteCategoryUseCase);
        RetrievePostsRoute retrievePostsRoute = new RetrievePostsRoute(retrievePostsUseCase);
        RetrievePostByIdRoute retrievePostByIdRoute = new RetrievePostByIdRoute(retrievePostByIdUseCase);
        RetrievePostsByCategoryIdRoute retrievePostsByCategoryIdRoute = new RetrievePostsByCategoryIdRoute(retrievePostsByCategoryIdUseCase);
        RetrievePostsByUserIdRoute retrievePostsByUserIdRoute = new RetrievePostsByUserIdRoute(retrievePostsByUserIdUseCase);
        CreatePostRoute createPostRoute = new CreatePostRoute(createPostUseCase);
        UpdatePostRoute updatePostRoute = new UpdatePostRoute(updatePostUseCase);
        DeletePostRoute deletePostRoute = new DeletePostRoute(deletePostUseCase);
        RetrieveCommentsRoute retrieveCommentsRoute = new RetrieveCommentsRoute(retrieveCommentsUseCase);
        RetrieveCommentByIdRoute retrieveCommentByIdRoute = new RetrieveCommentByIdRoute(retrieveCommentByIdUseCase);
        RetrieveCommentsByPostIdRoute retrieveCommentsByPostIdRoute = new RetrieveCommentsByPostIdRoute(retrieveCommentsByPostIdUseCase);
        CreateCommentRoute createCommentRoute = new CreateCommentRoute(createCommentUseCase);
        UpdateCommentRoute updateCommentRoute = new UpdateCommentRoute(updateCommentUseCase);
        DeleteCommentRoute deleteCommentRoute = new DeleteCommentRoute(deleteCommentUseCase);
        RetrieveUsersRoute retrieveUsersRoute = new RetrieveUsersRoute(retrieveUsersUseCase);
        RetrieveUserByIdRoute retrieveUserByIdRoute = new RetrieveUserByIdRoute(retrieveUserByIdUseCase);
        CreateUserRoute createUserRoute = new CreateUserRoute(createUserUseCase);
        UpdateUserRoute updateUserRoute = new UpdateUserRoute(updateUserUseCase);
        DeleteUserRoute deleteUserRoute = new DeleteUserRoute(deleteUserUseCase);
        LoginRoute loginRoute = new LoginRoute(loginUseCase);
        AuthenticateForAdminRoute authenticateForAdminRoute = new AuthenticateForAdminRoute(authenticateUseCase);
        AuthenticateForUserRoute authenticateForUserRoute = new AuthenticateForUserRoute(authenticateUseCase);
        ReauthenticateRoute reauthenticateRoute = new ReauthenticateRoute(reauthenticateUseCase);

        AppBuilder app = new AppBuilder(
                retrieveCategoriesRoute,
                retrieveCategoryByIdRoute,
                createCategoryRoute,
                updateCategoryRoute,
                deleteCategoryRoute,
                retrievePostsRoute,
                retrievePostByIdRoute,
                retrievePostsByCategoryIdRoute,
                retrievePostsByUserIdRoute,
                createPostRoute,
                updatePostRoute,
                deletePostRoute,
                retrieveCommentsRoute,
                retrieveCommentByIdRoute,
                retrieveCommentsByPostIdRoute,
                createCommentRoute,
                updateCommentRoute,
                deleteCommentRoute,
                retrieveUsersRoute,
                retrieveUserByIdRoute,
                createUserRoute,
                updateUserRoute,
                deleteUserRoute,
                loginRoute,
                authenticateForAdminRoute,
                authenticateForUserRoute,
                reauthenticateRoute);
        app.build();
    }
}