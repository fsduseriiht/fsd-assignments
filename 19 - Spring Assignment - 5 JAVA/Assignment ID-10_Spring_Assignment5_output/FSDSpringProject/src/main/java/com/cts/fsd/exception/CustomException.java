package com.cts.fsd.exception;


public class CustomException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public CustomException(String message) {
		
		super(String.format("Message : '%s'", message));
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
