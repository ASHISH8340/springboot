package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.Exception.userNotFound;
import com.stackroute.authenticationservice.Model.Role;
import com.stackroute.authenticationservice.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.stackroute.authenticationservice.Model.User;
import com.stackroute.authenticationservice.service.AuthenticationService;
import com.stackroute.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/v1/user")//pre-path
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;



    @PostMapping("sign-up")//api/authentication/sign-up
    public ResponseEntity<?> signUp(@RequestBody User user)
    {


        if (userService.findByUsername(user.getUsername()).isPresent())
        {
            throw new userNotFound("USER-FETCH-DETAILS");
          //  return new ResponseEntity<>(HttpStatus.CONFLICT);

        }



        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("sign-in")//api/authentication/sign-in
    public ResponseEntity<?> signIn(@RequestBody User user)
    {
        if (!userService.findByUsername(user.getUsername()).isPresent())
        {
            throw new userNotFound("USER-DETAIL");
        }

        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }

    @GetMapping("welcome")
    public String welcome(){

        return "WELCOME to the auth controller";
    }


    @GetMapping("all")
    public ResponseEntity<?> findAllUsers()
    {
        return ResponseEntity.ok(userService.findAllUsers());
    }



}
