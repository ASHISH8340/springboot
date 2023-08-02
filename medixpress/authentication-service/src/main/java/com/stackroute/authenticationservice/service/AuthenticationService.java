package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.Model.User;

public interface AuthenticationService {

    User signInAndReturnJWT(User signInRequest);
}
