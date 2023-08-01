package com.globallogic.GloQuora_Post.repository;

import com.globallogic.GloQuora_Post.model.GloQuoraPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<GloQuoraPost, Long> {
}
