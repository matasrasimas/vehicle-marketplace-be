/*
 * This file is generated by jOOQ.
 */
package org.example.generated.jooq;


import org.example.generated.jooq.tables.Categories;
import org.example.generated.jooq.tables.Comments;
import org.example.generated.jooq.tables.FlywaySchemaHistory;
import org.example.generated.jooq.tables.Posts;
import org.example.generated.jooq.tables.Users;
import org.example.generated.jooq.tables.records.CategoriesRecord;
import org.example.generated.jooq.tables.records.CommentsRecord;
import org.example.generated.jooq.tables.records.FlywaySchemaHistoryRecord;
import org.example.generated.jooq.tables.records.PostsRecord;
import org.example.generated.jooq.tables.records.UsersRecord;
import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in the
 * default schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CategoriesRecord> CATEGORIES_PKEY = Internal.createUniqueKey(Categories.CATEGORIES, DSL.name("categories_pkey"), new TableField[] { Categories.CATEGORIES.ID }, true);
    public static final UniqueKey<CommentsRecord> COMMENTS_PKEY = Internal.createUniqueKey(Comments.COMMENTS, DSL.name("comments_pkey"), new TableField[] { Comments.COMMENTS.ID }, true);
    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, DSL.name("flyway_schema_history_pk"), new TableField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
    public static final UniqueKey<PostsRecord> POSTS_PKEY = Internal.createUniqueKey(Posts.POSTS, DSL.name("posts_pkey"), new TableField[] { Posts.POSTS.ID }, true);
    public static final UniqueKey<UsersRecord> USERS_PKEY = Internal.createUniqueKey(Users.USERS, DSL.name("users_pkey"), new TableField[] { Users.USERS.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<CommentsRecord, PostsRecord> COMMENTS__COMMENTS_POST_ID_FKEY = Internal.createForeignKey(Comments.COMMENTS, DSL.name("comments_post_id_fkey"), new TableField[] { Comments.COMMENTS.POST_ID }, Keys.POSTS_PKEY, new TableField[] { Posts.POSTS.ID }, true);
    public static final ForeignKey<CommentsRecord, UsersRecord> COMMENTS__COMMENTS_USER_ID_FKEY = Internal.createForeignKey(Comments.COMMENTS, DSL.name("comments_user_id_fkey"), new TableField[] { Comments.COMMENTS.USER_ID }, Keys.USERS_PKEY, new TableField[] { Users.USERS.ID }, true);
    public static final ForeignKey<PostsRecord, CategoriesRecord> POSTS__POSTS_CATEGORY_ID_FKEY = Internal.createForeignKey(Posts.POSTS, DSL.name("posts_category_id_fkey"), new TableField[] { Posts.POSTS.CATEGORY_ID }, Keys.CATEGORIES_PKEY, new TableField[] { Categories.CATEGORIES.ID }, true);
    public static final ForeignKey<PostsRecord, UsersRecord> POSTS__POSTS_USER_ID_FKEY = Internal.createForeignKey(Posts.POSTS, DSL.name("posts_user_id_fkey"), new TableField[] { Posts.POSTS.USER_ID }, Keys.USERS_PKEY, new TableField[] { Users.USERS.ID }, true);
}
