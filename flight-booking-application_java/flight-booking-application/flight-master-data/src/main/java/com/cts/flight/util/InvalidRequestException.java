package com.cts.flight.util;

public class InvalidRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5254684291329803415L;
	
	public InvalidRequestException(String message) {
		super(message);
	}
	
}
