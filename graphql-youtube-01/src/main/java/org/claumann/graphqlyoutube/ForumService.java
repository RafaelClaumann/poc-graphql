package org.claumann.graphqlyoutube;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
class PostService {
    Map<String, Post> posts = new HashMap<>();

    {
        final String uuid = "0130ddee-d900-41a4-b7a2-5b1f598543e5";
        posts.put(uuid, new Post(uuid, PostSubject.FUNNY, "Post muito divertido!"));
    }

    Collection<Post> creatPost(final String content) {
        var post = new Post(UUID.randomUUID().toString(), PostSubject.OTHER, content);
        posts.put(post.id(), post);
        return posts.values();
    }

    Collection<Post> creatPost(final String subject, final String content) {
        PostSubject postSubject = PostSubject.valueOf(subject);
        var post = new Post(UUID.randomUUID().toString(), postSubject, content);
        posts.put(post.id(), post);
        return posts.values();
    }

    Post getPost(final String id) {
        return posts.get(id);
    }

    Collection<Post> getPosts(PostSubject subject) {
        return posts.values()
                .stream()
                .filter(post -> post.postSubject() == subject)
                .toList();
    }

    boolean deletePost(final String id) {
        return posts.remove(id) != null;
    }

}

@Service
class CommentService {
    Map<String, Comment> comments = new HashMap<>();

    Collection<Comment> creatComment(final String content, final String imageUrl, final String postId) {
        String commentId = UUID.randomUUID().toString();
        if (Objects.isNull(imageUrl)) {
            comments.put(commentId, new TextComment(commentId, content, postId));
            return comments.values();
        }

        comments.put(commentId, new ImageComment(commentId, content, imageUrl, postId));
        return comments.values();
    }

    Comment getComment(final String id) {
        return comments.get(id);
    }

    boolean deleteComment(final String id) {
        return comments.remove(id) != null;
    }

    public Collection<Comment> findByPostId(final String postId) {
        return comments.values()
                .stream()
                .filter(comment -> comment.postId().equals(postId))
                .toList();
    }

}
