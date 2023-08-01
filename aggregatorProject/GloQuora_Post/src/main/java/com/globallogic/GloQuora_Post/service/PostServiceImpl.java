package com.globallogic.GloQuora_Post.service;

import com.globallogic.GloQuora_Post.exception.IdNotFoundException;
import com.globallogic.GloQuora_Post.model.GloQuoraPost;
import com.globallogic.GloQuora_Post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService{
    @Autowired
    PostRepository postRepository;
    @Override
    public GloQuoraPost addPost(GloQuoraPost post) {
        log.info("adding new post");
        return postRepository.save(post);

    }

    @Override
    public List<GloQuoraPost> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public GloQuoraPost getPostById(long userId) {
        Optional<GloQuoraPost> postFromRepo = postRepository.findById(userId);
        if(postFromRepo.isPresent()) {
            return postFromRepo.get();
        }
       else {
           throw new IdNotFoundException("SERVICE.POST_GIVEN_ID_NOT_FOUND");

        }

    }


    @Override
    public GloQuoraPost updatePost(long userId, GloQuoraPost post) {
        Optional<GloQuoraPost> postFromDb = postRepository.findById(userId);
        GloQuoraPost updatedPost = null;
        if (postFromDb.isPresent()) {
            GloQuoraPost postFromRepo = postFromDb.get();
            postFromRepo.setUserId(post.getUserId());
            postFromRepo.setBody(post.getBody());
            postFromRepo.setTitle(post.getTitle());
            updatedPost = postRepository.save(postFromRepo);
        }
           else {
            log.error("unable to update post as post not exist");
            throw new IdNotFoundException("SERVICE.POST_GIVEN_ID_NOT_FOUND");
        }
        log.info(" post is updated");
        return updatedPost;
    }


    @Override
    public void deletePost(long userId) {
        if(!postRepository.existsById(userId)) {
            log.info("post not exist for delete");
            throw new IdNotFoundException("SERVICE.POST_NOT_FOUND");

        }
        log.info("post deleted successfully");
        postRepository.deleteById(userId);
    }
}
