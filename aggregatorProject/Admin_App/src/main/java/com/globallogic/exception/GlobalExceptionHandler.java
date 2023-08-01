package com.globallogic.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	Environment environment;

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleExceptionForUserNotFound(PostNotFoundException postNotFoundException) {
		ErrorInfo info = new ErrorInfo("101", environment.getProperty(postNotFoundException.getMessage()),
				LocalDateTime.now());
		return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleGlobalException(Exception exception) {
		ErrorInfo info = new ErrorInfo("103", exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(info, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
