package com.stackroute.userservice.service;

import com.stackroute.userservice.DTO.googleAuthDTO;
import com.stackroute.userservice.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    public User saveUser(User user);
    public User getUserByEmail(String emailId);
    public User deleteUserByEmail(String emailId);
    public User updateUser(String emailId, User user);

    User updateAddressUser(String emailId, User user);

    User addByAuth(googleAuthDTO userDto);
}
