package com.ehi.msi.enterprisecarshare.dto;

import java.util.List;

public class MsiError {

	private String errorCode;
	private String errorMessage;
	private List<String> validationResult;

	public MsiError(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public MsiError(String errorCode, String errorMessage,
			List<String> validationResult) {
		this(errorCode, errorMessage);
		this.validationResult = validationResult;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public List<String> getValidationResult() {
		return validationResult;
	}

}
