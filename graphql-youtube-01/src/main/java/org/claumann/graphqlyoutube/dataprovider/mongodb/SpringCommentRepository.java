package org.claumann.graphqlyoutube.dataprovider.mongodb;

import org.claumann.graphqlyoutube.domain.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringCommentRepository extends MongoRepository<Comment, String> {
}