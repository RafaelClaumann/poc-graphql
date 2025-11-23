package org.claumann.graphqlyoutube.service;

import org.claumann.graphqlyoutube.domain.models.Comment;
import org.claumann.graphqlyoutube.domain.models.Post;
import org.claumann.graphqlyoutube.domain.models.PostSubject;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class PostService {
    Map<String, Post> posts = new HashMap<>();

    private final CommentService commentService;

    {
        posts.put(Constants.PostConstants.POST_ID, new Post(Constants.PostConstants.POST_ID, PostSubject.FUNNY, "Post muito divertido!"));
    }

    PostService(CommentService commentService) {
        this.commentService = commentService;
    }

    public Post createPost(final String subject, final String content) {
        PostSubject definedSubject = PostSubject.OTHER;
        if (Objects.nonNull(subject)) {
            definedSubject = PostSubject.valueOf(subject);
        }

        final Post post = new Post(UUID.randomUUID().toString(), definedSubject, content);
        posts.put(post.id(), post);
        return post;
    }

    public Post getPost(final String id) {
        return posts.get(id);
    }

    public Collection<Post> getPosts(PostSubject subject) {
        if (Objects.isNull(subject)) {
            return posts.values();
        }

        return posts.values()
                .stream()
                .filter(post -> post.postSubject() == subject)
                .toList();
    }

    public boolean deletePost(final String id) {
        final Collection<Comment> byPostId = commentService.findByPostId(id);
        byPostId.forEach(comment -> commentService.deleteComment(comment.id()));
        return posts.remove(id) != null;
    }

}
