package org.claumann.graphqlyoutube.domain.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "post")
public record Post(
        @MongoId(FieldType.STRING)
        String id,
        PostSubject postSubject,
        String content
) {
}
