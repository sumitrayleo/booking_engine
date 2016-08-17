package com.ehi.msi.enterprisecarshare.web.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Controller
public class LoggingInterceptor extends HandlerInterceptorAdapter {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(LoggingInterceptor.class);
	@Value("${csma.request.id.header.name}")
	private String csmaRequestId;
	@Value("${csma.accept.language.header.name}")
	private String csmaAcceptLanguageHeaderName;
	@Value("${csma.appPlatform.header.name}")
	private String appPlatformHeaderName;

	@Override
	public boolean preHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler) {

		if (StringUtils.isNotBlank(request
				.getHeader(csmaAcceptLanguageHeaderName))) {
			MDC.put(csmaAcceptLanguageHeaderName,
					request.getHeader(csmaAcceptLanguageHeaderName));
		}
		final String requestId = request.getHeader(csmaRequestId);
		if (StringUtils.isBlank(requestId)) {
			MDC.put(csmaRequestId, UUID.randomUUID().toString());
		} else {
			MDC.put(csmaRequestId, requestId);
		}
		if (StringUtils.isNotBlank(request.getHeader(appPlatformHeaderName))) {
			MDC.put(appPlatformHeaderName,
					request.getHeader(appPlatformHeaderName));
		}
		final long startTimeMillis = System.currentTimeMillis();
		LOGGER.info(String
				.format("Issuing MSI Service request: Service Name:[%s] Start Time: %d ms ",
						((HandlerMethod) handler).getMethod().getName(),
						startTimeMillis));

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		final long endTimeMillis = System.currentTimeMillis();
		LOGGER.info(String
				.format("Finished MSI Service request: Service Name:[%s] End Time: %d ms ",
						((HandlerMethod) handler).getMethod().getName(),
						endTimeMillis));
		MDC.clear();
	}

}
