package com.globallogic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.dto.GloQuoraPost;
import com.globallogic.dto.UserDetails;
import com.globallogic.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("/usersPost/{userId}")
	public ResponseEntity<List<GloQuoraPost>> getPostByUser(@PathVariable int userId) {
		log.info("calling controller to fetch user post ");
		return new ResponseEntity<>(adminService.getPostByUserId(userId), HttpStatus.ACCEPTED);
	}

	@GetMapping("/usersPost")
	ResponseEntity<List<UserDetails>> getAllUserWithPost() {
		log.info("calling controller to fetch all user with their post ");
		return new ResponseEntity<>(adminService.allUserWithPost(), HttpStatus.OK);
	}

	@GetMapping("/usersName/{count}")
	ResponseEntity<List<String>> getAllUserName(@PathVariable int count) {
		log.info("calling controller to fetch all username with their post greater than certain count ");
		return new ResponseEntity<>(adminService.getAllNamesWithPostMoreThanK(count), HttpStatus.OK);
	}

	@GetMapping("/usersPost/company")
	ResponseEntity<List<String>> getAllCompany() {
		log.info("calling controller to fetch all company name with their post ");
		return new ResponseEntity<>(adminService.getAllCompanyName(), HttpStatus.OK);
	}

}
