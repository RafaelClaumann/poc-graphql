package org.claumann.graphqlyoutube.service;

import org.claumann.graphqlyoutube.dataprovider.mongodb.SpringCommentRepository;
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

    private final SpringCommentRepository commentRepository;

    {
        final String textCommentId = Constants.CommentConstants.TEXT_COMMENT_ID;
        comments.put(textCommentId, new TextComment(textCommentId, "TextComment", Constants.PostConstants.POST_ID));

        final String imageCommentId = Constants.CommentConstants.IMAGE_COMMENT_ID;
        comments.put(imageCommentId, new ImageComment(imageCommentId, "ImageComment", "ImageURL.svg", Constants.PostConstants.POST_ID));
    }

    public CommentService(SpringCommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment creatComment(final String content, final String imageUrl, final String postId) {
        if (Objects.isNull(imageUrl)) {
            return commentRepository.save(new TextComment(UUID.randomUUID().toString(), content, postId));
        }

        return commentRepository.save(new ImageComment(UUID.randomUUID().toString(), content, imageUrl, postId));
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
