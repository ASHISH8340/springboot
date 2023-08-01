package com.globallogic.GloQuora_Post.controller;

import com.globallogic.GloQuora_Post.exception.IdNotFoundException;
import com.globallogic.GloQuora_Post.model.GloQuoraPost;

import com.globallogic.GloQuora_Post.service.PostServiceImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @Mock
    PostServiceImpl postServiceImpl;

    @InjectMocks
    PostController postController;

//    @Test
//    @DisplayName("Testing add quora post")
//    void testAddGloQuoraStatus() {
//        GloQuoraPost gloQuoraPost = new GloQuoraPost();
//        gloQuoraPost.setBody("Learn Java");
//        gloQuoraPost.setTitle("JAVA");
//        gloQuoraPost.setUserId(1);
//        gloQuoraPost.setPostId("P1");
//        when(postServiceImpl.addPost(gloQuoraPost)).thenReturn(gloQuoraPost);
//        ResponseEntity<GloQuoraPost> responseFromController = postController.addPost(gloQuoraPost);
//        assertEquals(HttpStatus.CREATED, responseFromController.getStatusCode());
//    }

    @Test
    @DisplayName("Testing update quora post")
    void testupdateGloQuoraStatus() throws IdNotFoundException {
        GloQuoraPost gloQuoraPost = new GloQuoraPost();
        gloQuoraPost.setBody("Learn Java");
        gloQuoraPost.setTitle("JAVA");
        gloQuoraPost.setUserId(1);
        gloQuoraPost.setPostId("P1");
        when(postServiceImpl.updatePost(gloQuoraPost.getUserId(), gloQuoraPost)).thenReturn(gloQuoraPost);
        ResponseEntity<GloQuoraPost> responseFromController = postController.updatePost(gloQuoraPost, gloQuoraPost.getUserId());
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }

    @Test
    @DisplayName("Testing get all  quora post")
    void getAllGloQuoraStatus() {
        List<GloQuoraPost> allPost=new ArrayList<>();
        when(postServiceImpl.getAllPost()).thenReturn(allPost);
        ResponseEntity<List<GloQuoraPost>> responseFromController = postController.getAllPost();
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }

    @Test
    @DisplayName("Testing delete  quora post")
    void deleteGloQuoraStatus() throws  IdNotFoundException{
        ResponseEntity<String> responseFromController = postController.deletePost(1);
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }

}
