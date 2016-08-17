package com.ehi.msi.enterprisecarshare.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ehi.cts.web.mapper.CustomObjectMapper;
import com.ehi.msi.enterprisecarshare.annotation.Serializer;

/**
 * Spring managed component class serving the purpose of Custom Json Serializer.
 * It primarily does the job of serializing the content to String
 * 
 */
@Component
@Serializer
public class CustomJsonSerializer implements ISerializer {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(CustomJsonSerializer.class);

	@Autowired
	private CustomObjectMapper objectMapper;

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
				result = objectMapper.writeValueAsString(object);
			} catch (Exception ex) {
				LOGGER.error(
						"Exception occurred during json object to string conversion ",
						ex);
			}
		}
		return result;
	}

}