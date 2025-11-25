package org.claumann.graphqlyoutube.service;

import org.claumann.graphqlyoutube.dataprovider.mongodb.SpringCommentRepository;
import org.claumann.graphqlyoutube.domain.models.Comment;
import org.claumann.graphqlyoutube.domain.models.ImageComment;
import org.claumann.graphqlyoutube.domain.models.TextComment;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class CommentService {

    private final SpringCommentRepository commentRepository;

    public CommentService(SpringCommentRepository commentRepository) {
        this.commentRepository = commentRepository;

        commentRepository.save(new TextComment(Constants.CommentConstants.TEXT_COMMENT_ID, "TextComment", Constants.PostConstants.POST_ID));
        commentRepository.save(new ImageComment(Constants.CommentConstants.IMAGE_COMMENT_ID, "ImageComment", Constants.PostConstants.POST_ID, "ImageURL.svg"));
    }

    public Comment creatComment(final String content, final String imageUrl, final String postId) {
        if (Objects.isNull(imageUrl)) {
            return commentRepository.save(new TextComment(UUID.randomUUID().toString(), content, postId));
        }

        return commentRepository.save(new ImageComment(UUID.randomUUID().toString(), content, imageUrl, postId));
    }

    public Comment getComment(final String id) {
        return commentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Comment with id " + id + " not found"));
    }

    public Collection<Comment> getComments() {
        return commentRepository.findAll();
    }

    public boolean deleteComment(final String id) {
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Collection<Comment> findByPostId(final String postId) {
        return commentRepository.findCommentByPostId(postId);
    }

}
