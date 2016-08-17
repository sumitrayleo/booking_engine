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
package com.ehi.msi.enterprisecarshare.web.handler;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import com.ehi.msi.enterprisecarshare.spring.WebConfig;

/**
 * Extends {@link ExceptionHandlerExceptionResolver} to provide "global"
 * {@errorCode @ExceptionHandler} methods for use across all controllers.
 * 
 * <p>
 * Plugged in via
 * {@link WebConfig#configureHandlerExceptionResolvers(java.util.List)}.
 */
public class ExtendedExceptionHandlerExceptionResolver extends
		ExceptionHandlerExceptionResolver {

	private Object handler;

	private ExceptionHandlerMethodResolver methodResolver;

	private final static String EHI_CSMA_REQUEST_ID = "Ehi-CSMA-Request-Id";

	/**
	 * Provide a handler with @{@link ExceptionHandler} methods.
	 */
	public void setExceptionHandler(Object handler) {
		this.handler = handler;
		this.methodResolver = new ExceptionHandlerMethodResolver(
				handler.getClass());
	}

	@Override
	protected ServletInvocableHandlerMethod getExceptionHandlerMethod(
			HandlerMethod handlerMethod, Exception exception) {
		ServletInvocableHandlerMethod result = super.getExceptionHandlerMethod(
				handlerMethod, exception);
		if (result != null) {
			return result;
		}
		Method method = this.methodResolver.resolveMethod(exception);
		return (method != null) ? new ServletInvocableHandlerMethod(
				this.handler, method) : null;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		response.addHeader(EHI_CSMA_REQUEST_ID, MDC.get(EHI_CSMA_REQUEST_ID));
		return super.resolveException(request, response, handler, ex);
	}

}
