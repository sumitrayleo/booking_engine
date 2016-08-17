package com.cognizant.orchestration.exception;

@SuppressWarnings("serial")
public class AuthenticationTokenNotFoundException extends RuntimeException {
	public AuthenticationTokenNotFoundException() {
		super();
	}

	public AuthenticationTokenNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationTokenNotFoundException(String message) {
		super(message);
	}

	public AuthenticationTokenNotFoundException(Throwable cause) {
		super(cause);
	}
}
