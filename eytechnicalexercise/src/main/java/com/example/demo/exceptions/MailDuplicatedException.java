package com.example.demo.exceptions;

public class MailDuplicatedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MailDuplicatedException(String exception) {
		super(exception);
	}
}
