package com.ehi.msi.enterprisecarshare.serializer;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ehi.msi.enterprisecarshare.annotation.Serializer;
import com.ehi.msi.enterprisecarshare.enumeration.SerializerType;

/**
 * Serialization Factory class responsible for obtaining the appropriate
 * {@link ISerializer} instance. This is in turn used to convert the requested
 * object into String.
 */
@Component
public class SerializationFactory {

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Returns an instance of {@link ISerializer}
	 * 
	 * @param serializerType
	 *            The chosen serializer type based upon the requested object
	 *            type
	 * @return
	 */
	public ISerializer getSerializer(final SerializerType serializerType) {
		if (serializerType == null) {
			throw new IllegalArgumentException(
					"Serializer Type cannot be empty");
		}
		ISerializer serializer = null;
		final Map<String, Object> serializerBeans = applicationContext
				.getBeansWithAnnotation(Serializer.class);
		if (serializerBeans != null) {
			final Iterator<Object> iterator = serializerBeans.values()
					.iterator();
			while (iterator.hasNext()) {
				final Object serializerInstance = iterator.next();
				if (serializerType.equals(serializerInstance.getClass()
						.getAnnotation(Serializer.class).value())) {
					serializer = (ISerializer) serializerInstance;
				}
			}
		}
		return serializer;
	}
}
