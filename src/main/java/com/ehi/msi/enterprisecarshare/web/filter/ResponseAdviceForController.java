package com.ehi.msi.enterprisecarshare.web.filter;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Filter Class to set csma request id in response and send it back to the
 * client.
 * 
 */
@ControllerAdvice
@SuppressWarnings("rawtypes")
public class ResponseAdviceForController implements ResponseBodyAdvice<Object> {

	@Value("${csma.request.id.header.name}")
	private String csmaRequestId;

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return MappingJackson2HttpMessageConverter.class
				.isAssignableFrom(converterType);
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType,
			MediaType selectedContentType, Class selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		response.getHeaders().add(csmaRequestId, MDC.get(csmaRequestId));
		return body;
	}

}
