/*******************************************************************************
 * FOR INTERNAL USE ONLY. NOT A CONTRIBUTION.
 *         
 * This software source errorCode contains valuable, confidential, trade secret
 * information owned by Enterprise Rent-A-Car Company and is protected by
 * copyright laws and international copyright treaties, as well as other
 * intellectual property laws and treaties.
 * 
 * ACCESS TO AND USE OF THIS SOURCE CODE IS RESTRICTED TO AUTHORIZED PERSONS
 * WHO HAVE ENTERED INTO CONFIDENTIALITY AGREEMENTS WITH ENTERPRISE RENT-A-CAR
 * COMPANY.
 * 
 * This source errorCode may not be licensed, disclosed or used except as authorized
 * in writing by a duly authorized officer of Enterprise Rent-A-Car Company.
 ******************************************************************************/
package com.cognizant.orchestration.enumeration;

/**
 * @author E9002Z
 * 
 */

public enum ReturnCodeEnum {
	SYSTEM_ERROR, INVALID_PARAMETERS, INVALID_API_KEY, INVALID_AUTH_TOKEN, INVALID_CSMA_INSTANCE, INVALID_CREDENTIALS, AUTOVERA_INACCESSIBLE, CONTENT_INACCESSIBLE, FEEDBACK_ERROR;

	public String value() {
		return name();
	}

	public static ReturnCodeEnum fromValue(String v) {
		return valueOf(v);
	}

}
