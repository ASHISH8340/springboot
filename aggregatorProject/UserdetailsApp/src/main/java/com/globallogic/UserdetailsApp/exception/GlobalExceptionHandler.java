package com.globallogic.UserdetailsApp.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	Environment environment;
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleExceptionForIdNotFound(IdNotFoundException exception) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
		errorInfo.setErrorCode("101");
		errorInfo.setTime(LocalDate.now());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
