package com.globallogic.exception;

public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public String getMessage() {
		return message;
	}

	public PostNotFoundException(String message) {
		this.message = message;
	}

}
