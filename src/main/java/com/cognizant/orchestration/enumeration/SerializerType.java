package com.cognizant.orchestration.enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enumeration class used for different types of Serialization.
 * 
 */
public enum SerializerType {

	XSTREAMALIAS("XSTREAM"), DEFAULT("JSON"), XMLROOTELEMENT("XMLROOTELEMENT");

	private final static Logger LOGGER = LoggerFactory
			.getLogger(SerializerType.class);
	private String code;

	private SerializerType(final String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	/**
	 * Matches the incoming value with the enum declared
	 * 
	 * @param value
	 * @return
	 */
	public static SerializerType checkCode(final String value) {
		try {
			return SerializerType.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException ex) {
			LOGGER.error(
					"Error occurred while trying to find the Serialization type ",
					ex);
			return SerializerType.DEFAULT;
		}

	}

}
