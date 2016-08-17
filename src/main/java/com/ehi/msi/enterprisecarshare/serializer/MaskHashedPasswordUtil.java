package com.ehi.msi.enterprisecarshare.serializer;

/**
 * Common Utility Class which replaces the Hashed Password with a mask value
 * 
 */
public final class MaskHashedPasswordUtil {

	private final static String SEARCH_STRING_SALTED_HASH = "saltedHash=";
	private final static String MASK_STRING = "****";

	private MaskHashedPasswordUtil() {
	}

	/**
	 * Searches for a particular string "saltedHash=" from the url, picks the
	 * corresponding hashed password and replaces with a masked value "****"
	 * 
	 * @param url
	 * @return masked password
	 */
	public static String mask(final String url) {
		String maskedUrl = url;

		if (url != null && url.contains(SEARCH_STRING_SALTED_HASH)) {
			final int searchPoint = SEARCH_STRING_SALTED_HASH.length()
					+ url.indexOf(SEARCH_STRING_SALTED_HASH);
			final String saltedHashedPwd = url.substring(searchPoint,
					url.indexOf('&', searchPoint));
			maskedUrl = url.replace(saltedHashedPwd, MASK_STRING);
		}
		return maskedUrl;
	}
}
