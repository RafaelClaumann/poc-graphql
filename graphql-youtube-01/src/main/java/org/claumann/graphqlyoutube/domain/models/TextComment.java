package org.claumann.graphqlyoutube.domain.models;

public record TextComment(String id, String content, String postId) implements Comment {
}
