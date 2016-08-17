package com.ehi.msi.enterprisecarshare.exception;

@SuppressWarnings("serial")
public class InvalidAppPlatformException extends RuntimeException {
	public InvalidAppPlatformException() {
		super();
	}

	public InvalidAppPlatformException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAppPlatformException(String message) {
		super(message);
	}

	public InvalidAppPlatformException(Throwable cause) {
		super(cause);
	}
}
