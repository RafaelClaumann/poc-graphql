package org.claumann.graphqlyoutube.entrypoint.api;

import org.claumann.graphqlyoutube.domain.models.Comment;
import org.claumann.graphqlyoutube.service.CommentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @QueryMapping
    public Comment commentById(@Argument String id) {
        return commentService.getComment(id);
    }

    @MutationMapping
    public Boolean deleteComment(@Argument String id) {
        return commentService.deleteComment(id);
    }

    @QueryMapping
    public Collection<Comment> getComments() {
        return commentService.getComments();
    }

    @MutationMapping
    public Comment createComment(@Argument String content, @Argument String imageUrl, @Argument String postId) {
        return commentService.creatComment(content, imageUrl, postId);
    }

}
