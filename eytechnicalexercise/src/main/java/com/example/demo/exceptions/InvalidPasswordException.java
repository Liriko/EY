package com.example.demo.exceptions;

public class InvalidPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidPasswordException(String exception) {
		super(exception);
	}
}
