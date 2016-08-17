package com.ehi.cts.common.exception;

@SuppressWarnings("serial")
public class InvalidApiKeyException extends RuntimeException {

	public InvalidApiKeyException() {
		super();
	}

	public InvalidApiKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidApiKeyException(String message) {
		super(message);
	}

	public InvalidApiKeyException(Throwable cause) {
		super(cause);
	}

}
