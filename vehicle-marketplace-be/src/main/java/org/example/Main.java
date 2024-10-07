package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.gateway.api.CategoryGateway;
import org.example.gateway.api.CommentGateway;
import org.example.gateway.api.PostGateway;
import org.example.gateway.api.UserGateway;
import org.example.gateway.postgres.PostgresCategoryGateway;
import org.example.gateway.postgres.PostgresCommentGateway;
import org.example.gateway.postgres.PostgresPostGateway;
import org.example.gateway.postgres.PostgresUserGateway;
import org.example.model.converter.*;
import org.example.route.category.DeleteCategoryRoute;
import org.example.route.category.RetrieveCategoriesRoute;
import org.example.route.category.RetrieveCategoryByIdRoute;
import org.example.route.category.UpsertCategoryRoute;
import org.example.route.comment.*;
import org.example.route.post.*;
import org.example.route.user.*;
import org.example.usecase.api.category.DeleteCategoryUseCase;
import org.example.usecase.api.category.RetrieveCategoriesUseCase;
import org.example.usecase.api.category.RetrieveCategoryByIdUseCase;
import org.example.usecase.api.category.UpsertCategoryUseCase;
import org.example.usecase.api.comment.*;
import org.example.usecase.api.post.*;
import org.example.usecase.api.user.*;
import org.example.usecase.implement.category.DeleteCategoryInteractor;
import org.example.usecase.implement.category.RetrieveCategoriesInteractor;
import org.example.usecase.implement.category.RetrieveCategoryByIdInteractor;
import org.example.usecase.implement.category.UpsertCategoryInteractor;
import org.example.usecase.implement.comment.*;
import org.example.usecase.implement.post.*;
import org.example.usecase.implement.user.*;
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
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5469/postgres");;
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("postgres");
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        Configuration jooqConfig = new DefaultConfiguration()
                .set(new ThreadLocalTransactionProvider(new DataSourceConnectionProvider(hikariDataSource)))
                .set(SQLDialect.POSTGRES);
        DSLContext jooqDSLContext = DSL.using(jooqConfig);

        CategoryD2DTOConverter categoryD2DTOConverter = new CategoryD2DTOConverter();
        CategoryDTO2DConverter categoryDTO2DConverter = new CategoryDTO2DConverter();
        PostD2DTOConverter postD2DTOConverter = new PostD2DTOConverter();
        PostDTO2DConverter postDTO2DConverter = new PostDTO2DConverter();
        CommentD2DTOConverter commentD2DTOConverter = new CommentD2DTOConverter();
        CommentDTO2DConverter commentDTO2DConverter = new CommentDTO2DConverter();
        UserD2DTOConverter userD2DTOConverter = new UserD2DTOConverter();
        CreateUserDTO2DConverter createUserDTO2DConverter = new CreateUserDTO2DConverter();
        UpdateUserDTO2DConverter updateUserDTO2DConverter = new UpdateUserDTO2DConverter();

        CategoryGateway categoryGateway = new PostgresCategoryGateway(jooqDSLContext);
        PostGateway postGateway = new PostgresPostGateway(jooqDSLContext);
        CommentGateway commentGateway = new PostgresCommentGateway(jooqDSLContext);
        UserGateway userGateway = new PostgresUserGateway(jooqDSLContext);

        RetrieveCategoriesUseCase retrieveCategoriesUseCase = new RetrieveCategoriesInteractor(
                categoryGateway,
                categoryD2DTOConverter);
        RetrieveCategoryByIdUseCase retrieveCategoryByIdUseCase = new RetrieveCategoryByIdInteractor(
                categoryGateway,
                categoryD2DTOConverter
        );
        UpsertCategoryUseCase upsertCategoryUseCase = new UpsertCategoryInteractor(
                categoryGateway,
                categoryDTO2DConverter
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
        UpsertPostUseCase upsertPostUseCase = new UpsertPostInteractor(
                postGateway,
                postDTO2DConverter
        );
        DeletePostUseCase deletePostUseCase = new DeletePostInteractor(
                postGateway
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
        UpsertCommentUseCase upsertCommentUseCase = new UpsertCommentInteractor(
                commentGateway,
                commentDTO2DConverter
        );
        DeleteCommentUseCase deleteCommentUseCase = new DeleteCommentInteractor(
                commentGateway
        );
        RetrieveUsersUseCase retrieveUsersUseCase = new RetrieveUsersInteractor(
                userGateway,
                userD2DTOConverter
        );
        RetrieveUserByIdUseCase retrieveUserByIdUseCase = new RetrieveUserByIdInteractor(
                userGateway,
                userD2DTOConverter
        );
        CreateUserUseCase createUserUseCase = new CreateUserInteractor(
                userGateway,
                createUserDTO2DConverter
        );
        UpdateUserUseCase updateUserUseCase = new UpdateUserInteractor(
                userGateway,
                updateUserDTO2DConverter
        );
        DeleteUserUseCase deleteUserUseCase = new DeleteUserInteractor(
                userGateway
        );

        RetrieveCategoriesRoute retrieveCategoriesRoute = new RetrieveCategoriesRoute(retrieveCategoriesUseCase);
        RetrieveCategoryByIdRoute retrieveCategoryByIdRoute = new RetrieveCategoryByIdRoute(retrieveCategoryByIdUseCase);
        UpsertCategoryRoute upsertCategoryRoute = new UpsertCategoryRoute(upsertCategoryUseCase);
        DeleteCategoryRoute deleteCategoryRoute = new DeleteCategoryRoute(deleteCategoryUseCase);
        RetrievePostsRoute retrievePostsRoute = new RetrievePostsRoute(retrievePostsUseCase);
        RetrievePostByIdRoute retrievePostByIdRoute = new RetrievePostByIdRoute(retrievePostByIdUseCase);
        RetrievePostsByCategoryIdRoute retrievePostsByCategoryIdRoute = new RetrievePostsByCategoryIdRoute(retrievePostsByCategoryIdUseCase);
        RetrievePostsByUserIdRoute retrievePostsByUserIdRoute = new RetrievePostsByUserIdRoute(retrievePostsByUserIdUseCase);
        UpsertPostRoute upsertPostRoute = new UpsertPostRoute(upsertPostUseCase);
        DeletePostRoute deletePostRoute = new DeletePostRoute(deletePostUseCase);
        RetrieveCommentsRoute retrieveCommentsRoute = new RetrieveCommentsRoute(retrieveCommentsUseCase);
        RetrieveCommentByIdRoute retrieveCommentByIdRoute = new RetrieveCommentByIdRoute(retrieveCommentByIdUseCase);
        RetrieveCommentsByPostIdRoute retrieveCommentsByPostIdRoute = new RetrieveCommentsByPostIdRoute(retrieveCommentsByPostIdUseCase);
        UpsertCommentRoute upsertCommentRoute = new UpsertCommentRoute(upsertCommentUseCase);
        DeleteCommentRoute deleteCommentRoute = new DeleteCommentRoute(deleteCommentUseCase);
        RetrieveUsersRoute retrieveUsersRoute = new RetrieveUsersRoute(retrieveUsersUseCase);
        RetrieveUserByIdRoute retrieveUserByIdRoute = new RetrieveUserByIdRoute(retrieveUserByIdUseCase);
        CreateUserRoute createUserRoute = new CreateUserRoute(createUserUseCase);
        UpdateUserRoute updateUserRoute = new UpdateUserRoute(updateUserUseCase);
        DeleteUserRoute deleteUserRoute = new DeleteUserRoute(deleteUserUseCase);

        Flyway.configure()
                .locations("classpath:db/migration")
                .dataSource(hikariDataSource)
                .load()
                .migrate();

        AppBuilder app = new AppBuilder(
                retrieveCategoriesRoute,
                retrieveCategoryByIdRoute,
                upsertCategoryRoute,
                deleteCategoryRoute,
                retrievePostsRoute,
                retrievePostByIdRoute,
                retrievePostsByCategoryIdRoute,
                retrievePostsByUserIdRoute,
                upsertPostRoute,
                deletePostRoute,
                retrieveCommentsRoute,
                retrieveCommentByIdRoute,
                retrieveCommentsByPostIdRoute,
                upsertCommentRoute,
                deleteCommentRoute,
                retrieveUsersRoute,
                retrieveUserByIdRoute,
                createUserRoute,
                updateUserRoute,
                deleteUserRoute);
        app.build();
    }
}