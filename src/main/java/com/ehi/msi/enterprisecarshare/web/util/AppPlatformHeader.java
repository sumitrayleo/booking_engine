package com.ehi.msi.enterprisecarshare.web.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehi.msi.enterprisecarshare.exception.InvalidAppPlatformException;

/**
 * Helper class that parses and validates the appPlatform header.
 * 
 * @author Steve DeLassus
 * 
 */
public class AppPlatformHeader {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(AppPlatformHeader.class);

	private String platform;

	static public AppPlatformHeader getHeader(final HttpServletRequest request)
			throws InvalidAppPlatformException {
		if (request == null) {
			return null;
		}

		final String appPlatform = request.getHeader("appPlatform");

		if (StringUtils.isBlank(appPlatform)) {
			final String msg = "Missing app platform header.";
			LOGGER.error(msg);
			throw new InvalidAppPlatformException(msg);
		}

		if (appPlatform.equalsIgnoreCase("android")
				|| appPlatform.equalsIgnoreCase("ios")) {
			AppPlatformHeader header = new AppPlatformHeader();
			header.setPlatform(appPlatform);
			return header;
		}

		final String msg = String.format("Invalid appPlatform value: %s",
				appPlatform);
		LOGGER.error(msg);
		throw new InvalidAppPlatformException(msg);
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
