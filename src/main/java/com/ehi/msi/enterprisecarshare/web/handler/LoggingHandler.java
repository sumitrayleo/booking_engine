package com.ehi.msi.enterprisecarshare.web.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ehi.msi.enterprisecarshare.serializer.SerializationFactory;
import com.ehi.msi.enterprisecarshare.serializer.SerializerUtil;
import com.ehi.msi.enterprisecarshare.web.util.MsiLoggingUtil;

/**
 * Aspect Handler class used for Logging Payload during the external web service
 * calls. This class is responsible for storing the payload in cache and if
 * debug mode is on, then will log the payload. Logging of payload depends on
 * the type of serialization mechanism used, hence with the use of
 * {@link SerializationFactory} this purpose is solved.
 * 
 */
@Aspect
@Component
public class LoggingHandler {

	@Autowired
	private MsiLoggingUtil loggingUtil;

	@Autowired
	private SerializerUtil serializerUtil;

	@Value("${csma.request.id.header.name}")
	private String csmaRequestId;

	/**
	 * 
	 * @param jp
	 *            Proceeding Join Point
	 * @param request
	 *            The web service request object.
	 * @return the response returned from web service
	 * @throws Throwable
	 *             object to be handled in {@code GlobalExceptionHandler}
	 */
	@SuppressWarnings("unchecked")
	@Around("execution(* com.ehi.cts.serviceclient.*.sendRequest*(..)) && args(.., request)")
	public <T> T cacheAndLogForAuditing(ProceedingJoinPoint jp, Object request)
			throws Throwable {

		final String logRequestId = MDC.get(csmaRequestId);

		storeReqRespInCacheAndLog(request, logRequestId, true);

		T response = (T) jp.proceed();

		storeReqRespInCacheAndLog(response, logRequestId, false);

		return response;
	}

	/**
	 * Stores Request and Response object in Dedicated Caches. If debug mode is
	 * on, then the appropriate serializer is picked to serialize the payload
	 * (both request and response) to string and log it using logger.
	 * 
	 * @param message
	 * @param logRequestId
	 * @param isRequest
	 */
	private void storeReqRespInCacheAndLog(final Object message,
			final String logRequestId, boolean isRequest) {
		if (null != logRequestId) {
			loggingUtil.storeServiceMessageInCache(isRequest, logRequestId,
					message);

			if (loggingUtil.isDebugMode()) {
				// For development purposes, it is assumed that the debug level
				// would be on and hence no need for asynchronous
				// logging at this level.
				final String messageString = serializerUtil
						.checkForMarshallerAndSerializeToString(message,
								isRequest);
				loggingUtil.writeMessageToFile(message.getClass()
						.getSimpleName(), logRequestId, messageString);
			}
		}
	}

}
