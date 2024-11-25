/*
 * This file is generated by jOOQ.
 */
package org.example.generated.jooq.tables;


import java.util.Collection;

import org.example.generated.jooq.DefaultSchema;
import org.example.generated.jooq.Keys;
import org.example.generated.jooq.tables.records.CommentsRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Comments extends TableImpl<CommentsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>comments</code>
     */
    public static final Comments COMMENTS = new Comments();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CommentsRecord> getRecordType() {
        return CommentsRecord.class;
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public final TableField<CommentsRecord, Object> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.OTHER.nullable(false), this, "");

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public final TableField<CommentsRecord, Object> POST_ID = createField(DSL.name("post_id"), org.jooq.impl.SQLDataType.OTHER.nullable(false), this, "");

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public final TableField<CommentsRecord, Object> USER_ID = createField(DSL.name("user_id"), org.jooq.impl.SQLDataType.OTHER.nullable(false), this, "");

    /**
     * The column <code>comments.content</code>.
     */
    public final TableField<CommentsRecord, String> CONTENT = createField(DSL.name("content"), SQLDataType.NVARCHAR(2147483647).nullable(false), this, "");

    /**
     * The column <code>comments.rating</code>.
     */
    public final TableField<CommentsRecord, Integer> RATING = createField(DSL.name("rating"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public final TableField<CommentsRecord, Object> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.OTHER.nullable(false).defaultValue(DSL.field(DSL.raw("'(getdate())'"), org.jooq.impl.SQLDataType.OTHER)), this, "");

    private Comments(Name alias, Table<CommentsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Comments(Name alias, Table<CommentsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>comments</code> table reference
     */
    public Comments(String alias) {
        this(DSL.name(alias), COMMENTS);
    }

    /**
     * Create an aliased <code>comments</code> table reference
     */
    public Comments(Name alias) {
        this(alias, COMMENTS);
    }

    /**
     * Create a <code>comments</code> table reference
     */
    public Comments() {
        this(DSL.name("comments"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<CommentsRecord> getPrimaryKey() {
        return Keys.PK_COMMENTS;
    }

    @Override
    public Comments as(String alias) {
        return new Comments(DSL.name(alias), this);
    }

    @Override
    public Comments as(Name alias) {
        return new Comments(alias, this);
    }

    @Override
    public Comments as(Table<?> alias) {
        return new Comments(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Comments rename(String name) {
        return new Comments(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Comments rename(Name name) {
        return new Comments(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Comments rename(Table<?> name) {
        return new Comments(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Comments where(Condition condition) {
        return new Comments(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Comments where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Comments where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Comments where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Comments where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Comments where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Comments where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Comments where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Comments whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Comments whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
