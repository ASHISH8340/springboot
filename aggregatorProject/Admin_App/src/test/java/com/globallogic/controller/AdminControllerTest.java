package com.globallogic.controller;


import com.globallogic.dto.GloQuoraPost;
import com.globallogic.dto.UserDetails;
import com.globallogic.exception.PostNotFoundException;
import com.globallogic.service.AdminService;
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
public class AdminControllerTest {
    @Mock
    AdminService adminservice;

    @InjectMocks
    AdminController adminController;

    @Test
    @DisplayName("Testing get user post by id")
    void testGetGloQuoraPostByID() throws PostNotFoundException {
        List<GloQuoraPost> post=new ArrayList<>();
        when(adminservice.getPostByUserId(1)).thenReturn(post);
        ResponseEntity< List<GloQuoraPost>> responseFromController = adminController.getPostByUser(1);
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }

    @Test
    @DisplayName("Testing get all users post")
    void testGetAllUserWithPost() throws PostNotFoundException {
        List<UserDetails> users=new ArrayList<>();
        when(adminservice.allUserWithPost()).thenReturn(users);
        ResponseEntity<List<UserDetails> > responseFromController = adminController.getAllUserWithPost();
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }

    @Test
    @DisplayName("Testing get users post by count")
    void testGetAllNamesWithPostMoreThanK() throws PostNotFoundException {
        List<String> userNames=new ArrayList<>();
        when(adminservice.getAllNamesWithPostMoreThanK(2)).thenReturn(userNames);
        ResponseEntity<List<String>> responseFromController = adminController.getAllUserName(2);
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }

    @Test
    @DisplayName("Testing get company names")
    void getCompanyNames() throws PostNotFoundException {

        List<String> company=new ArrayList<>();
        when(adminservice.getAllCompanyName()).thenReturn(company);
        ResponseEntity<List<String>> responseFromController = adminController.getAllCompany();
        assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
    }
}
