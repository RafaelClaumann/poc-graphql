package org.claumann.graphqlyoutube;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class ForumController {

    private final PostService postService;
    private final CommentService commentService;

    public ForumController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    // usar quando método do controller tem nome diferente do schema
    // @SchemaMapping(typeName = "Query", value = "postById")
    @QueryMapping
    public Post postById(@Argument String id) {
        return postService.getPost(id);
    }

    @QueryMapping
    public Collection<Post> getPosts(@Argument PostSubject subject) {
        return postService.getPosts(subject);
    }

    @MutationMapping
    public Collection<Post> createPost(@Argument String content) {
        return postService.createPost(content);
    }

    @MutationMapping
    public Collection<Post> createPostWithSubject(@Argument String subject, @Argument String content) {
        return postService.createPost(subject, content);
    }

    @MutationMapping
    public Collection<Comment> createComment(@Argument String content, @Argument String imageUrl, @Argument String postId) {
        return commentService.creatComment(content, imageUrl, postId);
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
