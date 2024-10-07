package org.example;

import io.javalin.Javalin;
import org.example.exception.JavalinExceptionHandler;
import org.example.route.category.DeleteCategoryRoute;
import org.example.route.category.RetrieveCategoriesRoute;
import org.example.route.category.RetrieveCategoryByIdRoute;
import org.example.route.category.UpsertCategoryRoute;
import org.example.route.comment.*;
import org.example.route.post.*;
import org.example.route.user.*;

import static io.javalin.apibuilder.ApiBuilder.*;
import static org.example.common.RouteConstants.*;

public class AppBuilder {
    private final RetrieveCategoriesRoute retrieveCategoriesRoute;
    private final RetrieveCategoryByIdRoute retrieveCategoryByIdRoute;
    private final UpsertCategoryRoute upsertCategoryRoute;
    private final DeleteCategoryRoute deleteCategoryRoute;
    private final RetrievePostsRoute retrievePostsRoute;
    private final RetrievePostByIdRoute retrievePostByIdRoute;
    private final RetrievePostsByCategoryIdRoute retrievePostsByCategoryIdRoute;
    private final RetrievePostsByUserIdRoute retrievePostsByUserIdRoute;
    private final UpsertPostRoute upsertPostRoute;
    private final DeletePostRoute deletePostRoute;
    private final RetrieveCommentsRoute retrieveCommentsRoute;
    private final RetrieveCommentByIdRoute retrieveCommentByIdRoute;
    private final RetrieveCommentsByPostIdRoute retrieveCommentsByPostIdRoute;
    private final UpsertCommentRoute upsertCommentRoute;
    private final DeleteCommentRoute deleteCommentRoute;
    private final RetrieveUsersRoute retrieveUsersRoute;
    private final RetrieveUserByIdRoute retrieveUserByIdRoute;
    private final CreateUserRoute createUserRoute;
    private final UpdateUserRoute updateUserRoute;
    private final DeleteUserRoute deleteUserRoute;

    public AppBuilder(RetrieveCategoriesRoute retrieveCategoriesRoute,
                      RetrieveCategoryByIdRoute retrieveCategoryByIdRoute,
                      UpsertCategoryRoute upsertCategoryRoute,
                      DeleteCategoryRoute deleteCategoryRoute,
                      RetrievePostsRoute retrievePostsRoute,
                      RetrievePostByIdRoute retrievePostByIdRoute,
                      RetrievePostsByCategoryIdRoute retrievePostsByCategoryIdRoute,
                      RetrievePostsByUserIdRoute retrievePostsByUserIdRoute,
                      UpsertPostRoute upsertPostRoute,
                      DeletePostRoute deletePostRoute,
                      RetrieveCommentsRoute retrieveCommentsRoute,
                      RetrieveCommentByIdRoute retrieveCommentByIdRoute,
                      RetrieveCommentsByPostIdRoute retrieveCommentsByPostIdRoute,
                      UpsertCommentRoute upsertCommentRoute,
                      DeleteCommentRoute deleteCommentRoute,
                      RetrieveUsersRoute retrieveUsersRoute,
                      RetrieveUserByIdRoute retrieveUserByIdRoute,
                      CreateUserRoute createUserRoute,
                      UpdateUserRoute updateUserRoute,
                      DeleteUserRoute deleteUserRoute) {
        this.retrieveCategoriesRoute = retrieveCategoriesRoute;
        this.retrieveCategoryByIdRoute = retrieveCategoryByIdRoute;
        this.upsertCategoryRoute = upsertCategoryRoute;
        this.deleteCategoryRoute = deleteCategoryRoute;
        this.retrievePostsRoute = retrievePostsRoute;
        this.retrievePostByIdRoute = retrievePostByIdRoute;
        this.retrievePostsByCategoryIdRoute = retrievePostsByCategoryIdRoute;
        this.retrievePostsByUserIdRoute = retrievePostsByUserIdRoute;
        this.upsertPostRoute = upsertPostRoute;
        this.deletePostRoute = deletePostRoute;
        this.retrieveCommentsRoute = retrieveCommentsRoute;
        this.retrieveCommentByIdRoute = retrieveCommentByIdRoute;
        this.retrieveCommentsByPostIdRoute = retrieveCommentsByPostIdRoute;
        this.upsertCommentRoute = upsertCommentRoute;
        this.deleteCommentRoute = deleteCommentRoute;
        this.retrieveUsersRoute = retrieveUsersRoute;
        this.retrieveUserByIdRoute = retrieveUserByIdRoute;
        this.createUserRoute = createUserRoute;
        this.updateUserRoute = updateUserRoute;
        this.deleteUserRoute = deleteUserRoute;
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
    }

    private void createCategoriesRoutes() {
        get(EMPTY, retrieveCategoriesRoute::execute);
        path(CATEGORY_ID_PATH_PARAM, this::createCategoryIdRoutes);
        post(EMPTY, upsertCategoryRoute::execute);
    }

    private void createCategoryIdRoutes() {
        get(EMPTY, retrieveCategoryByIdRoute::execute);
        get(POSTS_PATH, retrievePostsByCategoryIdRoute::execute);
        delete(EMPTY, deleteCategoryRoute::execute);
    }

    private void createPostsRoutes() {
        get(EMPTY, retrievePostsRoute::execute);
        path(POST_ID_PATH_PARAM, this::createPostIdRoutes);
        post(EMPTY, upsertPostRoute::execute);
    }

    private void createPostIdRoutes() {
        get(EMPTY, retrievePostByIdRoute::execute);
        get(COMMENTS_PATH, retrieveCommentsByPostIdRoute::execute);
        delete(EMPTY, deletePostRoute::execute);
    }

    private void createCommentsRoutes() {
        get(EMPTY, retrieveCommentsRoute::execute);
        get(COMMENT_ID_PATH_PARAM, retrieveCommentByIdRoute::execute);
        post(EMPTY, upsertCommentRoute::execute);
        delete(COMMENT_ID_PATH_PARAM, deleteCommentRoute::execute);
    }

    private void createUsersRoutes() {
        get(EMPTY, retrieveUsersRoute::execute);
        path(USER_ID_PATH_PARAM, this::createUserIdRoutes);
        post(EMPTY, createUserRoute::execute);
        put(EMPTY, updateUserRoute::execute);
    }

    private void createUserIdRoutes() {
        get(EMPTY, retrieveUserByIdRoute::execute);
        get(POSTS_PATH, retrievePostsByUserIdRoute::execute);
        delete(EMPTY, deleteUserRoute::execute);
    }
}
