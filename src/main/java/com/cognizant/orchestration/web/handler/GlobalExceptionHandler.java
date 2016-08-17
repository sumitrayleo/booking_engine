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
package com.cognizant.orchestration.web.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cognizant.orchestration.dto.ErrorResponse;
import com.cognizant.orchestration.dto.MsiError;
import com.cognizant.orchestration.enumeration.ReturnCodeEnum;
import com.cognizant.orchestration.exception.AuthenticationTokenNotFoundException;

public class GlobalExceptionHandler {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(GlobalExceptionHandler.class);

	private MessageSource messageSource;

	@Value("${csma.request.id.header.name}")
	private String csmaRequestId;

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handle(
			AuthenticationTokenNotFoundException exception,
			HttpServletRequest httpServletRequest) {

		HttpHeaders headers = new HttpHeaders();

		LOGGER.error("AuthenticationTokenNotFoundException - "
				+ appendClientInformation(httpServletRequest));

		headers.setContentType(MediaType.APPLICATION_JSON);

		ErrorResponse response = new ErrorResponse();
		response.getErrors().add(
				new MsiError(ReturnCodeEnum.INVALID_AUTH_TOKEN.toString(),
						exception.getMessage()));

		return new ResponseEntity<ErrorResponse>(response, headers,
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handle(
			MethodArgumentNotValidException exception,
			HttpServletRequest httpServletRequest) {
		HttpHeaders headers = new HttpHeaders();

		LOGGER.error("MethodArgumentNotValidException - "
				+ appendClientInformation(httpServletRequest));

		headers.setContentType(MediaType.APPLICATION_JSON);

		StringBuffer sb = new StringBuffer();
		for (Object object : exception.getBindingResult().getAllErrors()) {
			if (object instanceof FieldError) {
				FieldError fieldError = (FieldError) object;
				sb.append(messageSource.getMessage(fieldError, null)).append(
						"; ");
			}
		}

		ErrorResponse response = new ErrorResponse();
		response.getErrors().add(
				new MsiError(ReturnCodeEnum.INVALID_PARAMETERS.toString(), sb
						.toString()));

		return new ResponseEntity<ErrorResponse>(response, headers,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handle(
			MissingServletRequestParameterException exception,
			HttpServletRequest httpServletRequest) {
		HttpHeaders headers = new HttpHeaders();

		LOGGER.error("MissingServletRequestParameterException - "
				+ appendClientInformation(httpServletRequest));

		headers.setContentType(MediaType.APPLICATION_JSON);

		ErrorResponse response = new ErrorResponse();
		response.getErrors().add(
				new MsiError(ReturnCodeEnum.INVALID_PARAMETERS.toString(),
						exception.getMessage()));

		return new ResponseEntity<ErrorResponse>(response, headers,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handle(
			IllegalArgumentException exception,
			HttpServletRequest httpServletRequest) {
		HttpHeaders headers = new HttpHeaders();

		LOGGER.error("IllegalArgumentException - "
				+ appendClientInformation(httpServletRequest));

		headers.setContentType(MediaType.APPLICATION_JSON);

		ErrorResponse response = new ErrorResponse();
		response.getErrors().add(
				new MsiError(ReturnCodeEnum.INVALID_PARAMETERS.toString(),
						exception.getMessage()));

		return new ResponseEntity<ErrorResponse>(response, headers,
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception handler method used for logging the error and returning back
	 * the response to client. All the runtime exceptions are handled by the
	 * default method and that its logged under a separate error log file if the
	 * exception type is marked with @Loggable
	 * 
	 * @param exception
	 * @param httpServletRequest
	 * @return
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleDefault(Exception exception,
			HttpServletRequest httpServletRequest) {
LOGGER.error("Exception - " + appendClientInformation(httpServletRequest));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		final ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.getErrors().add(
				new MsiError(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
						exception.getLocalizedMessage()));
		return new ResponseEntity<ErrorResponse>(errorResponse, headers,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private String appendClientInformation(HttpServletRequest httpServletRequest) {
		final String appPlatform = httpServletRequest.getHeader("appPlatform");
		final String appVersion = httpServletRequest.getHeader("appVersion");
		final StringBuffer buffer = new StringBuffer();
		buffer.append("AppPlatform: ").append(appPlatform)
				.append(", Version: ").append(appVersion)
				.append(", RequestID: ").append(MDC.get(csmaRequestId));
		return buffer.toString();
	}
}
