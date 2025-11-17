package org.claumann.graphqlyoutube;

enum PostSubject {
    TECH,
    JOB,
    FUNNY,
    OTHER
}

record Post(String id, PostSubject postSubject, String content) {
}

sealed interface Comment permits TextComment, ImageComment {
    String id();
    String content();
    String postId();
}

record TextComment(String id, String content, String postId) implements Comment {
}

record ImageComment(String id, String content, String imageUrl, String postId) implements Comment {
}
