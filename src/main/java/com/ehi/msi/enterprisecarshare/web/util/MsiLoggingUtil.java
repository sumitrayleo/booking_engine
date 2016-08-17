package com.ehi.msi.enterprisecarshare.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.stereotype.Component;

import com.ehi.cts.serviceclient.RequestResponseCacheManager;
import com.ehi.msi.enterprisecarshare.serializer.SerializerUtil;

/**
 * Logging Utility Class for MSI layer.
 * 
 */
@Component
public class MsiLoggingUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(MsiLoggingUtil.class);
    public static final String LAST_REQUEST_CACHE_NAME = "LastRequestCache";
    public static final String LAST_RESPONSE_CACHE_NAME = "LastResponseCache";

    @Autowired
    private RequestResponseCacheManager cacheManager;

    @Autowired
    private SerializerUtil serializerUtil;

    @Value("${csma.request.id.header.name}")
    private String csmaRequestId;

    @Value("${httpClientDebugMode}")
    private boolean debugMode;

    /**
     * Uses log4j as the logger to write the output only in debug mode.
     */
    public final void writeMessageToFile(final String className, final String requestId, final String output) {
        if (this.isDebugMode()) {
            LOGGER.debug("Object Name " + className);
            LOGGER.debug(String.format("Request Id : %s \n Payload : %s ", requestId, output));
        }
    }

    /**
     * Common method for Logging Exception. The request/response objects are fetched from cache and then serialized to string based
     * upon the serializer. The final payload string is logged, which in turn passes through logging filters.
     * 
     * @param exception
     * @param clientInformation
     */
    public void logError(final Exception exception, final String clientInformation) {
        final String logRequestId = MDC.get(csmaRequestId);
        final ValueWrapper requestMessageValue =
            cacheManager.getCache(RequestResponseCacheManager.LAST_REQUEST_CACHE_NAME).get(logRequestId);

        final ValueWrapper responseMessageValue =
            cacheManager.getCache(RequestResponseCacheManager.LAST_RESPONSE_CACHE_NAME).get(logRequestId);

        String responseMessage = null;
        String requestMessage = null;

        if (requestMessageValue != null) {
            requestMessage = serializerUtil.checkForMarshallerAndSerializeToString(requestMessageValue, true);
        }
        if (responseMessageValue != null) {
            responseMessage = serializerUtil.checkForMarshallerAndSerializeToString(responseMessageValue, false);
        }
        LOGGER.error("\nHandled by default error handler \n" + clientInformation + "\nLAST_KNOWN_HOST_REQUEST\n" + requestMessage
                        + "\nLAST_KNOWN_HOST_RESPONSE\n" + responseMessage, exception);

    }

    /**
     * @param isRequest
     *            indicates if the current object is a request or a response object
     * @param correlationId
     *            the key for the cached object. The correlationId is unique to each request.
     * @param output
     *            This is either the services request or response object in the form of a string that will be written to the file
     *            system in a local development environment or stored in cache for error logging (all environments).
     */
    public void storeServiceMessageInCache(boolean isRequest, String correlationId, Object output) {
        // Cache the host request/response to be logged if an error occurs
        if (isRequest) {
            cacheManager.getCache(RequestResponseCacheManager.LAST_RESPONSE_CACHE_NAME).evict(correlationId);
            cacheManager.getCache(RequestResponseCacheManager.LAST_REQUEST_CACHE_NAME).put(correlationId, output);
        } else {
            cacheManager.getCache(RequestResponseCacheManager.LAST_RESPONSE_CACHE_NAME).put(correlationId, output);
        }
    }

    /**
     * This method used to be a part of RequestResponseLoggingUtil present in cts-common which is no longer extended by
     * MsiLoggingUtil.
     * 
     * @return
     */
    public boolean isDebugMode() {
        return debugMode;
    }

}