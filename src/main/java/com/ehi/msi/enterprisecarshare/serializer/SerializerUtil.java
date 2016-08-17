package com.ehi.msi.enterprisecarshare.serializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.stereotype.Component;

import com.ehi.cts.serviceclient.BaseResponseWrapper;
import com.ehi.msi.enterprisecarshare.enumeration.SerializerType;

/**
 * Utility Class for looking up to the appropriate serializer type like JAXB,
 * XSTREAM, JSON etc. and using them to convert to appropriate type like String,
 * any other objects etc.
 * 
 */
@Component
public class SerializerUtil {

	@Autowired
	private SerializationFactory serializationFactory;

	/**
	 * Method to determine the serialization mechanism for the incoming object
	 * (common for request and response) based upon the annotation used, and in
	 * turn calls the method "serializeToString" to serialize the payload to
	 * string
	 * 
	 * @param message
	 * @param isRequest
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String checkForMarshallerAndSerializeToString(Object message,
			final boolean isRequest) {
		Class<?> clazz = null;
		if (message instanceof ValueWrapper) {
			final ValueWrapper messageValue = (ValueWrapper) message;
			// Below is for requests containing the url and the query string -
			// applies to AEM, Autovera services
			message = messageValue.get();
			if (message instanceof String && isRequest) {
				return MaskHashedPasswordUtil.mask((String) message);
			} else {
				if (message instanceof BaseResponseWrapper) {
					final Object response = ((BaseResponseWrapper) message)
							.getResponse();
					clazz = response != null ? response.getClass() : null;
					message = response;
				} else {
					clazz = message.getClass();
				}
			}
		} else {
			clazz = message.getClass();
		}

		return serializeToString(
				AnnotationProcessorForSerialization.processAnnotation(clazz),
				message);
	}

	/**
	 * Picks up the appropriate serializer (e.g - XStream or JSON or JAXB) using
	 * a factory class and converts it to a string
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	private String serializeToString(final SerializerType code,
			final Object message) {
		return serializationFactory.getSerializer(code).convert(message);
	}
}
