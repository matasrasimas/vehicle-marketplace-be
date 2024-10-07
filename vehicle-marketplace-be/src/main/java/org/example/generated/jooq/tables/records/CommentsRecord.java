/*
 * This file is generated by jOOQ.
 */
package org.example.generated.jooq.tables.records;


import java.util.UUID;

import org.example.generated.jooq.tables.Comments;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class CommentsRecord extends UpdatableRecordImpl<CommentsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>comments.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>comments.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>comments.post_id</code>.
     */
    public void setPostId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>comments.post_id</code>.
     */
    public UUID getPostId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>comments.user_id</code>.
     */
    public void setUserId(UUID value) {
        set(2, value);
    }

    /**
     * Getter for <code>comments.user_id</code>.
     */
    public UUID getUserId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>comments.content</code>.
     */
    public void setContent(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>comments.content</code>.
     */
    public String getContent() {
        return (String) get(3);
    }

    /**
     * Setter for <code>comments.rating</code>.
     */
    public void setRating(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>comments.rating</code>.
     */
    public Integer getRating() {
        return (Integer) get(4);
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
     * Create a detached CommentsRecord
     */
    public CommentsRecord() {
        super(Comments.COMMENTS);
    }

    /**
     * Create a detached, initialised CommentsRecord
     */
    public CommentsRecord(UUID id, UUID postId, UUID userId, String content, Integer rating) {
        super(Comments.COMMENTS);

        setId(id);
        setPostId(postId);
        setUserId(userId);
        setContent(content);
        setRating(rating);
        resetChangedOnNotNull();
    }
}