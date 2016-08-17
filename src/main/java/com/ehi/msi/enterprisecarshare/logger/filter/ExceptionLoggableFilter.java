package com.ehi.msi.enterprisecarshare.logger.filter;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;
import org.springframework.core.annotation.AnnotationUtils;

import com.ehi.msi.enterprisecarshare.annotation.Loggable;

/**
 * The purpose of this logger filter is to log those exception scenarios which
 * are marked with @Loggable annotation and deniable parameter as false. When
 * deniable is set as true then those exceptions are logged in common log file
 * and console. If deniable is set as false, then the logs get redirected to a
 * separate error log file.
 */
public class ExceptionLoggableFilter extends Filter {

	private String deniable;

	/*
	 * @see
	 * org.apache.log4j.spi.Filter#decide(org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	public int decide(LoggingEvent event) {
		final Boolean deniableParamValue = Boolean.valueOf(deniable);
		final int defaultReturn = deniableParamValue ? ACCEPT : DENY;
		final int conditionalReturn = deniableParamValue ? DENY : ACCEPT;
		final ThrowableInformation throwableInformation = event
				.getThrowableInformation();
		if (throwableInformation != null) {
			final Throwable throwable = throwableInformation.getThrowable();
			final Loggable loggable = AnnotationUtils.findAnnotation(
					throwable.getClass(), Loggable.class);
			return loggable != null ? conditionalReturn : defaultReturn;
		}
		return defaultReturn;
	}

	public String getDeniable() {
		return deniable;
	}

	public void setDeniable(String deniable) {
		this.deniable = deniable;
	}

}
