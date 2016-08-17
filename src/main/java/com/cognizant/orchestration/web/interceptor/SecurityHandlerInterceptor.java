package com.cognizant.orchestration.web.interceptor;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cognizant.orchestration.exception.InvalidApiKeyException;

/**
 * Interceptor class which checks the validity of the Booking-API-Key passed in the header of the request.
 * 
 */
public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private static final String BOOKING_API_KEY = "Booking-API-Key";
    private static final String DEFAULT_API_KEYS_RESOURCE_NAME = "api-keys.properties";

    private final Properties apiKeyProperties;

    public SecurityHandlerInterceptor() {
        apiKeyProperties = loadApiKeys();
    }

    private Properties loadApiKeys() {
        try {
            return PropertiesLoaderUtils.loadAllProperties(DEFAULT_API_KEYS_RESOURCE_NAME);
        } catch (final Exception e) {
            LOGGER.error("UnenrichedError loading API keys", e);
        }
        return null;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
        throws Exception {

        // check the API key
        final String apiKey = request.getHeader(BOOKING_API_KEY);
        if (apiKey == null || !apiKeyProperties.containsKey(apiKey)) {
            LOGGER.error("Invalid API key: " + apiKey);
            throw new InvalidApiKeyException("Invalid API key.");
        }

        return true;
    }

}
