/*******************************************************************************
 * FOR INTERNAL USE ONLY. NOT A CONTRIBUTION.
 *         
 * This software source code contains valuable, confidential, trade secret
 * information owned by Enterprise Rent-A-Car Company and is protected by
 * copyright laws and international copyright treaties, as well as other
 * intellectual property laws and treaties.
 * 
 * ACCESS TO AND USE OF THIS SOURCE CODE IS RESTRICTED TO AUTHORIZED PERSONS
 * WHO HAVE ENTERED INTO CONFIDENTIALITY AGREEMENTS WITH ENTERPRISE RENT-A-CAR
 * COMPANY.
 * 
 * This source code may not be licensed, disclosed or used except as authorized
 * in writing by a duly authorized officer of Enterprise Rent-A-Car Company.
 ******************************************************************************/
package com.ehi.msi.enterprisecarshare.web.handler;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * @author e3590k
 * 
 */
public class ResponseErrorHandlerImpl implements ResponseErrorHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.client.ResponseErrorHandler#hasError(org.
	 * springframework.http.client.ClientHttpResponse)
	 */
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.client.ResponseErrorHandler#handleError(org.
	 * springframework.http.client.ClientHttpResponse)
	 */
	public void handleError(ClientHttpResponse response) throws IOException {

	}

}
