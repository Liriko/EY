package com.example.demo.exceptions;

public class MailDomainInvalidException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MailDomainInvalidException(String exception) {
		super(exception);
	}
}
