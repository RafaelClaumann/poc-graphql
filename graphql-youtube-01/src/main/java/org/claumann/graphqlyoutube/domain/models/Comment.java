package org.claumann.graphqlyoutube.domain.models;

public sealed interface Comment permits TextComment, ImageComment {
    String id();

    String content();

    String postId();
}
