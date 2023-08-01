package com.globallogic.GloQuora_Post.controller;

import com.globallogic.GloQuora_Post.model.GloQuoraPost;
import com.globallogic.GloQuora_Post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/api")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping(value = "addPost")
    public ResponseEntity<GloQuoraPost> addPost(@RequestBody GloQuoraPost post) {
        log.info("adding post and passing created status");
        return new ResponseEntity<GloQuoraPost>(postService.addPost(post), HttpStatus.OK);
    }

    @GetMapping(value = "posts")
    public ResponseEntity<List<GloQuoraPost>> getAllPost() {
        log.info("getting all the post");
        return new ResponseEntity<List<GloQuoraPost>>(postService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping(value = "post/{userId}")
    public ResponseEntity<GloQuoraPost> getPost(@PathVariable long userId) {
        log.info("getting  post by Id");
        return new ResponseEntity<GloQuoraPost>(postService.getPostById(userId), HttpStatus.OK);
    }

    @PutMapping(value = "posts/{userId}")
    public ResponseEntity<GloQuoraPost> updatePost(@RequestBody GloQuoraPost post, @PathVariable long userId) {
        log.info("updating post and passing ok status");
        return new ResponseEntity<GloQuoraPost>(postService.updatePost(userId, post), HttpStatus.OK);
    }

    @DeleteMapping(value = "post/{userId}")
    public ResponseEntity<String> deletePost(@PathVariable long userId) {
        log.info("deleting post");
        postService.deletePost(userId);
        return new ResponseEntity<String>("Post deleted successfully", HttpStatus.OK);
    }
}
