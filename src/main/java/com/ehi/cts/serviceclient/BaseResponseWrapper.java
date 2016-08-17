package com.ehi.cts.serviceclient;

import org.springframework.http.HttpStatus;

public class BaseResponseWrapper<T> {

	private HttpStatus httpStatus;
	private T response;

	public BaseResponseWrapper(T resp, HttpStatus httpStatus) {

		this.response = resp;
		this.httpStatus = httpStatus;
	}

	public T getResponse() {
		return response;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
