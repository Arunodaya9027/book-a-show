package com.capgemini.theaterservice.exception;

public class TheaterNotFoundException extends Exception {
	private String message;
	
	public TheaterNotFoundException(String message) {
		super(message);
	}
}
