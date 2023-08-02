package com.stackroute.authenticationservice.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler{

    @Autowired
    Environment environment;

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> shopIDNotFound(userNotFound exception){
        ErrorInfo errorInfo=new ErrorInfo();
        errorInfo.setErrormsg(environment.getProperty(exception.getMsg()));
        errorInfo.setErrorCode("200");
        errorInfo.setDateTime(LocalDateTime.now());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
    }

}
