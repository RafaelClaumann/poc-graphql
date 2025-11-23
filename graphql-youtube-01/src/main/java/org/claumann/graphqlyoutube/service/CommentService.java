package org.claumann.graphqlyoutube.service;

import org.claumann.graphqlyoutube.domain.models.Comment;
import org.claumann.graphqlyoutube.domain.models.ImageComment;
import org.claumann.graphqlyoutube.domain.models.TextComment;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class CommentService {
    Map<String, Comment> comments = new HashMap<>();

    {
        final String textCommentId = Constants.CommentConstants.TEXT_COMMENT_ID;
        comments.put(textCommentId, new TextComment(textCommentId, "TextComment", Constants.PostConstants.POST_ID));

        final String imageCommentId = Constants.CommentConstants.IMAGE_COMMENT_ID;
        comments.put(imageCommentId, new ImageComment(imageCommentId, "ImageComment", "ImageURL.svg", Constants.PostConstants.POST_ID));
    }

    public Collection<Comment> creatComment(final String content, final String imageUrl, final String postId) {
        String commentId = UUID.randomUUID().toString();
        if (Objects.isNull(imageUrl)) {
            comments.put(commentId, new TextComment(commentId, content, postId));
            return comments.values();
        }

        comments.put(commentId, new ImageComment(commentId, content, imageUrl, postId));
        return comments.values();
    }

    public Comment getComment(final String id) {
        return comments.get(id);
    }

    public Collection<Comment> getComments() {
        return comments.values();
    }

    public boolean deleteComment(final String id) {
        return comments.remove(id) != null;
    }

    public Collection<Comment> findByPostId(final String postId) {
        return comments.values()
                .stream()
                .filter(comment -> comment.postId().equals(postId))
                .toList();
    }

}
