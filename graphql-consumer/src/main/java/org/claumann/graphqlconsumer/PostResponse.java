package org.claumann.graphqlconsumer;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

record PostResponse(
        String id,
        String subject,
        String content,
        List<Comment> comments
) {
}

@JsonInclude(JsonInclude.Include.NON_NULL)
record Comment(
        String id,
        String content,
        String imageUrl
) {
}
