package com.capgemini.theaterservice.exception;

public class TheaterNotFoundException extends RuntimeException {
	public TheaterNotFoundException(String message) {
		super(message);
	}
}
