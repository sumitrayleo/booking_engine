package com.ehi.msi.enterprisecarshare.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
	private List<MsiError> errors = new ArrayList<MsiError>();

	public List<MsiError> getErrors() {
		return errors;
	}
}
