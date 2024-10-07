/*
 * This file is generated by jOOQ.
 */
package org.example.generated.jooq.tables;


import java.util.Collection;
import java.util.UUID;

import org.example.generated.jooq.DefaultSchema;
import org.example.generated.jooq.Keys;
import org.example.generated.jooq.tables.Posts.PostsPath;
import org.example.generated.jooq.tables.records.CategoriesRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
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
public class Categories extends TableImpl<CategoriesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>categories</code>
     */
    public static final Categories CATEGORIES = new Categories();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CategoriesRecord> getRecordType() {
        return CategoriesRecord.class;
    }

    /**
     * The column <code>categories.id</code>.
     */
    public final TableField<CategoriesRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>categories.title</code>.
     */
    public final TableField<CategoriesRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.CLOB.nullable(false), this, "");

    private Categories(Name alias, Table<CategoriesRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Categories(Name alias, Table<CategoriesRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>categories</code> table reference
     */
    public Categories(String alias) {
        this(DSL.name(alias), CATEGORIES);
    }

    /**
     * Create an aliased <code>categories</code> table reference
     */
    public Categories(Name alias) {
        this(alias, CATEGORIES);
    }

    /**
     * Create a <code>categories</code> table reference
     */
    public Categories() {
        this(DSL.name("categories"), null);
    }

    public <O extends Record> Categories(Table<O> path, ForeignKey<O, CategoriesRecord> childPath, InverseForeignKey<O, CategoriesRecord> parentPath) {
        super(path, childPath, parentPath, CATEGORIES);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class CategoriesPath extends Categories implements Path<CategoriesRecord> {
        public <O extends Record> CategoriesPath(Table<O> path, ForeignKey<O, CategoriesRecord> childPath, InverseForeignKey<O, CategoriesRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private CategoriesPath(Name alias, Table<CategoriesRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public CategoriesPath as(String alias) {
            return new CategoriesPath(DSL.name(alias), this);
        }

        @Override
        public CategoriesPath as(Name alias) {
            return new CategoriesPath(alias, this);
        }

        @Override
        public CategoriesPath as(Table<?> alias) {
            return new CategoriesPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<CategoriesRecord> getPrimaryKey() {
        return Keys.CATEGORIES_PKEY;
    }

    private transient PostsPath _posts;

    /**
     * Get the implicit to-many join path to the <code>public.posts</code> table
     */
    public PostsPath posts() {
        if (_posts == null)
            _posts = new PostsPath(this, null, Keys.POSTS__POSTS_CATEGORY_ID_FKEY.getInverseKey());

        return _posts;
    }

    @Override
    public Categories as(String alias) {
        return new Categories(DSL.name(alias), this);
    }

    @Override
    public Categories as(Name alias) {
        return new Categories(alias, this);
    }

    @Override
    public Categories as(Table<?> alias) {
        return new Categories(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Categories rename(String name) {
        return new Categories(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Categories rename(Name name) {
        return new Categories(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Categories rename(Table<?> name) {
        return new Categories(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Categories where(Condition condition) {
        return new Categories(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Categories where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Categories where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Categories where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Categories where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Categories where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Categories where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Categories where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Categories whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Categories whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}