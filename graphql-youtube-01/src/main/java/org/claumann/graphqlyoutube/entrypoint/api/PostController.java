package org.claumann.graphqlyoutube.entrypoint.api;

import org.claumann.graphqlyoutube.domain.models.Post;
import org.claumann.graphqlyoutube.domain.models.PostSubject;
import org.claumann.graphqlyoutube.service.PostService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @QueryMapping
    public Post postById(@Argument String id) {
        return postService.getPost(id);
    }

    @QueryMapping
    public Collection<Post> getPosts(@Argument PostSubject subject) {
        return postService.getPosts(subject);
    }

    @MutationMapping
    public Boolean deletePost(@Argument String id) {
        return postService.deletePost(id);
    }

    @MutationMapping
    public Post createPost(@Argument String subject, @Argument String content) {
        return postService.createPost(subject, content);
    }

}
