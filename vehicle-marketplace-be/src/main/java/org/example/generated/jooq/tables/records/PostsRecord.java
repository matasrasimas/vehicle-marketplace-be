/*
 * This file is generated by jOOQ.
 */
package org.example.generated.jooq.tables.records;


import java.util.UUID;

import org.example.generated.jooq.tables.Posts;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PostsRecord extends UpdatableRecordImpl<PostsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>posts.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>posts.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>posts.category_id</code>.
     */
    public void setCategoryId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>posts.category_id</code>.
     */
    public UUID getCategoryId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>posts.user_id</code>.
     */
    public void setUserId(UUID value) {
        set(2, value);
    }

    /**
     * Getter for <code>posts.user_id</code>.
     */
    public UUID getUserId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>posts.description</code>.
     */
    public void setDescription(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>posts.description</code>.
     */
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>posts.brand</code>.
     */
    public void setBrand(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>posts.brand</code>.
     */
    public String getBrand() {
        return (String) get(4);
    }

    /**
     * Setter for <code>posts.model</code>.
     */
    public void setModel(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>posts.model</code>.
     */
    public String getModel() {
        return (String) get(5);
    }

    /**
     * Setter for <code>posts.manufacture_year</code>.
     */
    public void setManufactureYear(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>posts.manufacture_year</code>.
     */
    public Integer getManufactureYear() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>posts.mileage</code>.
     */
    public void setMileage(Double value) {
        set(7, value);
    }

    /**
     * Getter for <code>posts.mileage</code>.
     */
    public Double getMileage() {
        return (Double) get(7);
    }

    /**
     * Setter for <code>posts.price</code>.
     */
    public void setPrice(Double value) {
        set(8, value);
    }

    /**
     * Getter for <code>posts.price</code>.
     */
    public Double getPrice() {
        return (Double) get(8);
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
     * Create a detached PostsRecord
     */
    public PostsRecord() {
        super(Posts.POSTS);
    }

    /**
     * Create a detached, initialised PostsRecord
     */
    public PostsRecord(UUID id, UUID categoryId, UUID userId, String description, String brand, String model, Integer manufactureYear, Double mileage, Double price) {
        super(Posts.POSTS);

        setId(id);
        setCategoryId(categoryId);
        setUserId(userId);
        setDescription(description);
        setBrand(brand);
        setModel(model);
        setManufactureYear(manufactureYear);
        setMileage(mileage);
        setPrice(price);
        resetChangedOnNotNull();
    }
}
