package com.globallogic.GloQuora_Post.service;

import com.globallogic.GloQuora_Post.model.GloQuoraPost;

import java.util.List;

public interface PostService {
    GloQuoraPost addPost(GloQuoraPost post);
    List<GloQuoraPost> getAllPost();
    GloQuoraPost getPostById(long  userId);
    GloQuoraPost updatePost(long userId, GloQuoraPost post);

    void deletePost(long userId);
}
