package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.Model.Role;
import com.stackroute.authenticationservice.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User>findByUsername(String username);

    void changeRole(Role newRole, String username);

    List<User> findAllUsers();


}
