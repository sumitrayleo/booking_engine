package com.ehi.msi.enterprisecarshare.serializer;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.core.annotation.AnnotationUtils;

import com.ehi.msi.enterprisecarshare.enumeration.SerializerType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Common Annotation Processor class for returning specific Serializer type.
 * 
 */
public final class AnnotationProcessorForSerialization {

	private final static String SERIALIZER_TYPE_DEFAULT = "default";

	private AnnotationProcessorForSerialization() {
	}

	/**
	 * Searches for a fixed set of annotation in the provided type
	 * 
	 * @param clazz
	 * @return type of Serializer
	 */
	public static SerializerType processAnnotation(final Class<?> clazz) {
		if (clazz != null) {
			// Below is for Autovera Related Responses
			final XStreamAlias xstreamAlias = AnnotationUtils.findAnnotation(
					clazz, XStreamAlias.class);
			if (xstreamAlias != null) {
				return SerializerType.checkCode(xstreamAlias.annotationType()
						.getSimpleName());
			}
			final XmlRootElement xmlRootElement = AnnotationUtils
					.findAnnotation(clazz, XmlRootElement.class);
			if (xmlRootElement != null) {
				return SerializerType.checkCode(xmlRootElement.annotationType()
						.getSimpleName());
			}
			// Below is for AEM Related responses and any other scenario.
			return SerializerType.checkCode(SERIALIZER_TYPE_DEFAULT);
		}
		return null;
	}
}
