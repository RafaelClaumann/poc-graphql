package org.claumann.graphqlyoutube;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

class Constants {
    public static final String POST_ID = "0130ddee-d900-41a4-b7a2-5b1f598543e5";
    public static final String TEXT_COMMENT_ID = "9490a2dc-9e89-47c8-a8be-dd9d3981da5a";
    public static final String IMAGE_COMMENT_ID = "3398fa22-19e4-4e2b-8272-50279e20f735";
}

@Service
class PostService {
    Map<String, Post> posts = new HashMap<>();

    {
        posts.put(Constants.POST_ID, new Post(Constants.POST_ID, PostSubject.FUNNY, "Post muito divertido!"));
    }

    Post createPost(final String content) {
        return this.createPost(PostSubject.OTHER.name(), content);
    }

    Post createPost(final String subject, final String content) {
        PostSubject postSubject = PostSubject.valueOf(subject);
        var post = new Post(UUID.randomUUID().toString(), postSubject, content);
        posts.put(post.id(), post);
        return post;
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

    {
        final String textCommentId = Constants.TEXT_COMMENT_ID;
        comments.put(textCommentId, new TextComment(textCommentId, "TextComment", Constants.POST_ID));

        final String imageCommentId = Constants.IMAGE_COMMENT_ID;
        comments.put(imageCommentId, new ImageComment(imageCommentId, "ImageComment", "ImageURL.svg", Constants.POST_ID));
    }

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

    Collection<Comment> getComments() {
        return comments.values();
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
