package org.claumann.graphqlyoutube.service;

import org.claumann.graphqlyoutube.dataprovider.mongodb.SpringPostRepository;
import org.claumann.graphqlyoutube.domain.models.Comment;
import org.claumann.graphqlyoutube.domain.models.Post;
import org.claumann.graphqlyoutube.domain.models.PostSubject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class PostService {
    Map<String, Post> posts = new HashMap<>();

    private final SpringPostRepository postRepository;
    private final CommentService commentService;

    PostService(SpringPostRepository postRepository, CommentService commentService) {
        this.postRepository = postRepository;
        this.commentService = commentService;

        postRepository.save(new Post(Constants.PostConstants.POST_ID, PostSubject.FUNNY, "Post muito divertido!"));
    }

    public Post createPost(final String subject, final String content) {
        PostSubject definedSubject = PostSubject.OTHER;
        if (Objects.nonNull(subject)) {
            definedSubject = PostSubject.valueOf(subject);
        }

        final Post post = new Post(UUID.randomUUID().toString(), definedSubject, content);
        return postRepository.save(post);
    }

    public Post getPost(final String id) {
        return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Post with id " + id + " not found"));
    }

    public Collection<Post> getPosts(final PostSubject subject) {
        if (Objects.isNull(subject)) {
            return postRepository.findAll();
        }

        return postRepository.findAll()
                .stream()
                .filter(post -> subject == post.postSubject())
                .toList();
    }

    @Transactional
    public boolean deletePost(final String id) {
        try {
            final Collection<Comment> comments = commentService.findByPostId(id);
            comments.forEach(comment -> commentService.deleteComment(comment.id()));

            postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
