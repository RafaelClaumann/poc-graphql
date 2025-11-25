package org.claumann.graphqlyoutube.domain.models;

public class ImageComment extends Comment {

    private final String imageUrl;

    public ImageComment(final String id, final String content, final String postId, final String imageUrl) {
        super(id, content, postId);
        this.imageUrl = imageUrl;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

}
