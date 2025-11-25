package org.claumann.graphqlyoutube.domain.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "comments")
public class Comment {

    @MongoId(FieldType.STRING)
    private final String id;
    private final String content;
    private final String postId;

    protected Comment(final String id, final String content, final String postId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
    }

    public String id() {
        return this.id;
    }

    public String content() {
        return this.content;
    }

    public String postId() {
        return this.postId;
    }

}
