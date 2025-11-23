package org.claumann.graphqlyoutube.entrypoint.api;

import org.claumann.graphqlyoutube.domain.models.Comment;
import org.claumann.graphqlyoutube.domain.models.Post;
import org.claumann.graphqlyoutube.domain.models.PostSubject;
import org.claumann.graphqlyoutube.service.CommentService;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class DataLoaders {

    private final CommentService commentService;

    public DataLoaders(CommentService commentService) {
        this.commentService = commentService;
    }

    @SchemaMapping
    public Collection<Comment> comments(Post post) {
        return commentService.findByPostId(post.id());
    }

    @SchemaMapping
    public PostSubject subject(Post post) {
        return post.postSubject();
    }

}
