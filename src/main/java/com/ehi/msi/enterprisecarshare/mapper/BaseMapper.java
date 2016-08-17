package com.ehi.msi.enterprisecarshare.mapper;

import org.dozer.converters.ConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ehi.cts.mapper.DozerMapper;

/**
 * A base mapper used for domain conversions. This base class exposes generic
 * methods that do error checking and call common mapping plumbing, and throw in
 * cases where conversion fails.
 * 
 * @author Steve DeLassus
 * 
 */
public class BaseMapper {

	@Autowired
	private DozerMapper mapper;

	private final static Logger LOGGER = LoggerFactory
			.getLogger(BaseMapper.class);

	public <T, U> void tryMapping(T src, U dest)
			throws IllegalArgumentException {

		if (src == null) {
			throw new IllegalArgumentException(
					"Null source object for mapping.");
		}

		if (dest == null) {
			throw new IllegalArgumentException(
					"Null destination object for mapping.");
		}

		try {
			mapper.map(src, dest);
		} catch (ConversionException e) {
			final String msg = String.format(
					"Failed to convert from %s to %s: %s", src.getClass()
							.getName(), dest.getClass().getName(), e
							.getMessage());
			LOGGER.error(msg);
			throw new IllegalArgumentException(msg, e);
		}
	}

	public <T, U> U tryMapping(T src, Class<U> destClass)
			throws IllegalArgumentException {

		if (src == null) {
			throw new IllegalArgumentException(
					"Null source object for mapping.");
		}

		if (destClass == null) {
			throw new IllegalArgumentException(
					"Null destination class for mapping.");
		}

		try {
			return mapper.map(src, destClass);
		} catch (ConversionException e) {
			final String msg = String.format(
					"Failed to convert from %s to %s: %s", src.getClass()
							.getName(), destClass.getName(), e.getMessage());
			LOGGER.error(msg);
			throw new IllegalArgumentException(msg, e);
		}
	}
}
