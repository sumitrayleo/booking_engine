package com.ehi.msi.enterprisecarshare.exception;

import com.ehi.msi.enterprisecarshare.annotation.Loggable;

@Loggable
@SuppressWarnings("serial")
public class InvalidCredentialsException extends RuntimeException {

	public InvalidCredentialsException() {
		super();
	}

	public InvalidCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCredentialsException(String message) {
		super(message);
	}

	public InvalidCredentialsException(Throwable cause) {
		super(cause);
	}

}
