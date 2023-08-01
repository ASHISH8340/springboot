package com.globallogic.UserdetailsApp.controller;

import com.globallogic.UserdetailsApp.Dto.UserDTO;
import com.globallogic.UserdetailsApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/v1/api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "user")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        log.info("adding user and passing created status");
        return new ResponseEntity<UserDTO>(userService.addUser(userDTO), HttpStatus.OK);
    }

    @GetMapping(value = "users")
    public ResponseEntity<List<UserDTO>> getAllUserDetails() {
        log.info("getting details of all the user");
        return new ResponseEntity<List<UserDTO>>(userService.getAllUserDetails(), HttpStatus.OK);
    }

    @GetMapping(value = "user/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        log.info("getting user and passing ok status");
        return new ResponseEntity<UserDTO>(userService.getSpecificUserDetails(userId), HttpStatus.OK);
    }

    @PutMapping("users/{userId}")
    ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long userId)  {
        log.info("updating user and passing ok status");
        return new ResponseEntity<UserDTO>(userService.updateUser(userId,userDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        log.info("deleting user");
        userService.deleteUser(userId);
        return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
    }
}
