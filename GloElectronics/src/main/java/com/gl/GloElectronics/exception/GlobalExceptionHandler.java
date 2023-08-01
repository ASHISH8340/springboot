package com.gl.GloElectronics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {



	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorInfo> handleExceptionFoNotFound(NotFoundException notFoundException) {
		ErrorInfo info = new ErrorInfo("101", notFoundException.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> handleExceptionForConstraintViolation(
			ConstraintViolationException constraintViolationException) {
		ErrorInfo info = new ErrorInfo("101", constraintViolationException.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
	}
}
