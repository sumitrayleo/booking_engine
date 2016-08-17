package com.ehi.msi.enterprisecarshare.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ehi.msi.enterprisecarshare.annotation.Serializer;
import com.ehi.msi.enterprisecarshare.enumeration.SerializerType;
import com.thoughtworks.xstream.XStream;

/**
 * Spring managed component class serving the purpose of XStream Serializer
 * 
 */
@Component
@Serializer(value = SerializerType.XSTREAMALIAS)
public class XStreamSerializer implements ISerializer {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(CustomJsonSerializer.class);

	@Autowired
	private XStream xstream;

	/*
	 * @see
	 * com.ehi.msi.enterprisecarshare.serializer.ISerializer#convert(java.lang
	 * .Object)
	 */
	@Override
	public final String convert(final Object object) {
		String result = null;
		if (object != null) {
			try {
				result = xstream.toXML(object);
			} catch (Exception ex) {
				LOGGER.error(
						"Exception occurred during XStream object to string conversion ",
						ex);
			}
		}
		return result;
	}

}