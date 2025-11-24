package org.claumann.graphqlyoutube.dataprovider.mongodb;

import org.claumann.graphqlyoutube.domain.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringPostRepository extends MongoRepository<Post, String> {
}