package com.stackroute.authenticationservice.Exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class userNotFound extends RuntimeException{

    private String msg;

}
