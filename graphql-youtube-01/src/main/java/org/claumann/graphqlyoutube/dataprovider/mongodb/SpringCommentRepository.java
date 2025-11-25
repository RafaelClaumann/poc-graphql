package org.claumann.graphqlyoutube.dataprovider.mongodb;

import org.claumann.graphqlyoutube.domain.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringCommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findCommentByPostId(final String postId);

    @Query("{ postId: { $eq: ?0 } }")
    List<Comment> findCommentByPostIdQuery(final String postId);

}