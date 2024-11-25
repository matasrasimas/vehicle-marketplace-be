package org.example;

import io.javalin.Javalin;
import io.javalin.http.HandlerType;
import org.example.exception.JavalinExceptionHandler;
import org.example.route.auth.*;
import org.example.route.category.*;
import org.example.route.comment.*;
import org.example.route.post.*;
import org.example.route.user.*;

import static io.javalin.apibuilder.ApiBuilder.*;
import static org.example.common.RouteConstants.*;

public class AppBuilder {
    private final RetrieveCategoriesRoute retrieveCategoriesRoute;
    private final RetrieveCategoryByIdRoute retrieveCategoryByIdRoute;
    private final CreateCategoryRoute createCategoryRoute;
    private final UpdateCategoryRoute updateCategoryRoute;
    private final DeleteCategoryRoute deleteCategoryRoute;
    private final RetrievePostsRoute retrievePostsRoute;
    private final RetrievePostByIdRoute retrievePostByIdRoute;
    private final RetrievePostsByCategoryIdRoute retrievePostsByCategoryIdRoute;
    private final RetrievePostsByUserIdRoute retrievePostsByUserIdRoute;
    private final CreatePostRoute createPostRoute;
    private final UpdatePostRoute updatePostRoute;
    private final DeletePostRoute deletePostRoute;
    private final RetrieveCommentsRoute retrieveCommentsRoute;
    private final RetrieveCommentByIdRoute retrieveCommentByIdRoute;
    private final RetrieveCommentsByPostIdRoute retrieveCommentsByPostIdRoute;
    private final CreateCommentRoute createCommentRoute;
    private final UpdateCommentRoute updateCommentRoute;
    private final DeleteCommentRoute deleteCommentRoute;
    private final RetrieveUsersRoute retrieveUsersRoute;
    private final RetrieveUserByIdRoute retrieveUserByIdRoute;
    private final CreateUserRoute createUserRoute;
    private final UpdateUserRoute updateUserRoute;
    private final DeleteUserRoute deleteUserRoute;
    private final LoginRoute loginRoute;
    private final AuthenticateForAdminRoute authenticateForAdminRoute;
    private final AuthenticateForUserRoute authenticateForUserRoute;
    private final ReauthenticateRoute reauthenticateRoute;
    private final ExtractUserFromJwtRoute extractUserFromJwtRoute;

    public AppBuilder(RetrieveCategoriesRoute retrieveCategoriesRoute,
                      RetrieveCategoryByIdRoute retrieveCategoryByIdRoute,
                      CreateCategoryRoute createCategoryRoute,
                      UpdateCategoryRoute updateCategoryRoute,
                      DeleteCategoryRoute deleteCategoryRoute,
                      RetrievePostsRoute retrievePostsRoute,
                      RetrievePostByIdRoute retrievePostByIdRoute,
                      RetrievePostsByCategoryIdRoute retrievePostsByCategoryIdRoute,
                      RetrievePostsByUserIdRoute retrievePostsByUserIdRoute,
                      CreatePostRoute createPostRoute,
                      UpdatePostRoute updatePostRoute,
                      DeletePostRoute deletePostRoute,
                      RetrieveCommentsRoute retrieveCommentsRoute,
                      RetrieveCommentByIdRoute retrieveCommentByIdRoute,
                      RetrieveCommentsByPostIdRoute retrieveCommentsByPostIdRoute,
                      CreateCommentRoute createCommentRoute,
                      UpdateCommentRoute updateCommentRoute,
                      DeleteCommentRoute deleteCommentRoute,
                      RetrieveUsersRoute retrieveUsersRoute,
                      RetrieveUserByIdRoute retrieveUserByIdRoute,
                      CreateUserRoute createUserRoute,
                      UpdateUserRoute updateUserRoute,
                      DeleteUserRoute deleteUserRoute,
                      LoginRoute loginRoute,
                      AuthenticateForAdminRoute authenticateForAdminRoute,
                      AuthenticateForUserRoute authenticateForUserRoute,
                      ReauthenticateRoute reauthenticateRoute,
                      ExtractUserFromJwtRoute extractUserFromJwtRoute) {
        this.retrieveCategoriesRoute = retrieveCategoriesRoute;
        this.retrieveCategoryByIdRoute = retrieveCategoryByIdRoute;
        this.createCategoryRoute = createCategoryRoute;
        this.updateCategoryRoute = updateCategoryRoute;
        this.deleteCategoryRoute = deleteCategoryRoute;
        this.retrievePostsRoute = retrievePostsRoute;
        this.retrievePostByIdRoute = retrievePostByIdRoute;
        this.retrievePostsByCategoryIdRoute = retrievePostsByCategoryIdRoute;
        this.retrievePostsByUserIdRoute = retrievePostsByUserIdRoute;
        this.createPostRoute = createPostRoute;
        this.updatePostRoute = updatePostRoute;
        this.deletePostRoute = deletePostRoute;
        this.retrieveCommentsRoute = retrieveCommentsRoute;
        this.retrieveCommentByIdRoute = retrieveCommentByIdRoute;
        this.retrieveCommentsByPostIdRoute = retrieveCommentsByPostIdRoute;
        this.createCommentRoute = createCommentRoute;
        this.updateCommentRoute = updateCommentRoute;
        this.deleteCommentRoute = deleteCommentRoute;
        this.retrieveUsersRoute = retrieveUsersRoute;
        this.retrieveUserByIdRoute = retrieveUserByIdRoute;
        this.createUserRoute = createUserRoute;
        this.updateUserRoute = updateUserRoute;
        this.deleteUserRoute = deleteUserRoute;
        this.loginRoute = loginRoute;
        this.authenticateForAdminRoute = authenticateForAdminRoute;
        this.authenticateForUserRoute = authenticateForUserRoute;
        this.reauthenticateRoute = reauthenticateRoute;
        this.extractUserFromJwtRoute = extractUserFromJwtRoute;
    }

    public Javalin build() {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> cors.addRule(it -> {
                it.allowCredentials = true;
                it.reflectClientOrigin = true;
            }));
            config.http.defaultContentType = "application/json";
            config.router.apiBuilder(() -> path(API_PATH, this::setApiRoutes));
        });

        app.exception(Exception.class, new JavalinExceptionHandler());
        app.start(8080);
        return app;
    }

    private void setApiRoutes() {
        path(CATEGORIES_PATH, this::createCategoriesRoutes);
        path(POSTS_PATH, this::createPostsRoutes);
        path(COMMENTS_PATH, this::createCommentsRoutes);
        path(USERS_PATH, this::createUsersRoutes);
        post(LOGIN_PATH, loginRoute::execute);
        post(REAUTH_PATH, reauthenticateRoute::execute);
        path(AUTH_PATH, this::createAuthRoutes);
    }

    private void createAuthRoutes() {
        get(EMPTY, extractUserFromJwtRoute::execute);
    }

    private void createCategoriesRoutes() {
        before(EMPTY, ctx -> {
            if (ctx.method().equals(HandlerType.POST))
                authenticateForAdminRoute.execute(ctx);
        });
        get(EMPTY, retrieveCategoriesRoute::execute);
        path(CATEGORY_ID_PATH_PARAM, this::createCategoryIdRoutes);
        post(EMPTY, createCategoryRoute::execute);
    }

    private void createCategoryIdRoutes() {
        before(EMPTY, ctx -> {
            if (ctx.method().equals(HandlerType.PUT) || ctx.method().equals(HandlerType.DELETE))
                authenticateForAdminRoute.execute(ctx);
        });
        get(EMPTY, retrieveCategoryByIdRoute::execute);
        get(POSTS_PATH, retrievePostsByCategoryIdRoute::execute);
        put(EMPTY, updateCategoryRoute::execute);
        delete(EMPTY, deleteCategoryRoute::execute);
    }

    private void createPostsRoutes() {
        before(EMPTY, ctx -> {
            if (ctx.method().equals(HandlerType.POST))
                authenticateForUserRoute.execute(ctx);
        });
        get(EMPTY, retrievePostsRoute::execute);
        path(POST_ID_PATH_PARAM, this::createPostIdRoutes);
        post(EMPTY, createPostRoute::execute);
    }

    private void createPostIdRoutes() {
        before(EMPTY, ctx -> {
            if (ctx.method().equals(HandlerType.PUT) || ctx.method().equals(HandlerType.DELETE))
                authenticateForUserRoute.execute(ctx);
        });
        get(EMPTY, retrievePostByIdRoute::execute);
        get(COMMENTS_PATH, retrieveCommentsByPostIdRoute::execute);
        put(EMPTY, updatePostRoute::execute);
        delete(EMPTY, deletePostRoute::execute);
    }

    private void createCommentsRoutes() {
        before(EMPTY, ctx -> {
            if (ctx.method().equals(HandlerType.POST))
                authenticateForUserRoute.execute(ctx);
        });
        get(EMPTY, retrieveCommentsRoute::execute);
        post(EMPTY, createCommentRoute::execute);
        path(COMMENT_ID_PATH_PARAM, this::createCommentIdRoutes);
    }

    private void createCommentIdRoutes() {
        before(EMPTY, ctx -> {
            if (ctx.method().equals(HandlerType.PUT) || ctx.method().equals(HandlerType.DELETE))
                authenticateForUserRoute.execute(ctx);
        });
        get(EMPTY, retrieveCommentByIdRoute::execute);
        put(EMPTY, updateCommentRoute::execute);
        delete(EMPTY, deleteCommentRoute::execute);
    }

    private void createUsersRoutes() {
        get(EMPTY, retrieveUsersRoute::execute);
        path(USER_ID_PATH_PARAM, this::createUserIdRoutes);
        post(EMPTY, createUserRoute::execute);
    }

    private void createUserIdRoutes() {
        before(EMPTY, ctx -> {
            if (ctx.method().equals(HandlerType.GET) || ctx.method().equals(HandlerType.PUT) || ctx.method().equals(HandlerType.DELETE))
                authenticateForUserRoute.execute(ctx);
        });
        get(EMPTY, retrieveUserByIdRoute::execute);
        get(POSTS_PATH, retrievePostsByUserIdRoute::execute);
        put(EMPTY, updateUserRoute::execute);
        delete(EMPTY, deleteUserRoute::execute);
    }
}
