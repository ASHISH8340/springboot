package com.stackroute.shoplistService.Exception;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExcceptionHandler {

    @Autowired
    Environment environment;

    @ExceptionHandler
    public ResponseEntity<Object> shopIDNotFound(shopIdNotFound exception){
        ErrorInfo errorInfo=new ErrorInfo();
        errorInfo.setErrormsg(environment.getProperty(exception.getMsg()));
        errorInfo.setErrorCode("200");
        errorInfo.setDateTime(LocalDateTime.now());
        return new ResponseEntity<Object>(errorInfo, HttpStatus.NOT_FOUND);
    }


}
