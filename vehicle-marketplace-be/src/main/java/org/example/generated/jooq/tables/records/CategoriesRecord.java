/*
 * This file is generated by jOOQ.
 */
package org.example.generated.jooq.tables.records;


import java.util.UUID;

import org.example.generated.jooq.tables.Categories;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class CategoriesRecord extends UpdatableRecordImpl<CategoriesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>categories.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>categories.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>categories.title</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>categories.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CategoriesRecord
     */
    public CategoriesRecord() {
        super(Categories.CATEGORIES);
    }

    /**
     * Create a detached, initialised CategoriesRecord
     */
    public CategoriesRecord(UUID id, String title) {
        super(Categories.CATEGORIES);

        setId(id);
        setTitle(title);
        resetChangedOnNotNull();
    }
}
