package com.cognizant.orchestration.exception;

/**
 * Exception class for Invalid API Key
 * 
 */
public class InvalidApiKeyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

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
