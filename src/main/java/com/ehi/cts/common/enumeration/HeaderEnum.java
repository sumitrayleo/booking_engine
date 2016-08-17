package com.ehi.cts.common.enumeration;

public enum HeaderEnum {

	CORRELATION_ID("CORRELATION_ID");

	private String value;

	private HeaderEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
