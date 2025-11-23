package org.claumann.graphqlyoutube.domain.models;

public record ImageComment(String id, String content, String imageUrl, String postId) implements Comment {
}
