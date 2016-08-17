package com.ehi.msi.enterprisecarshare.web.util;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class EncryptedCredentials implements Serializable {

	private final static long serialVersionUID = -8854378798772514532L;

	private String username;
	private String hashedPassword;

	public static EncryptedCredentials parse(final String credentialString)
			throws IllegalArgumentException {
		if (StringUtils.isBlank(credentialString)) {
			throw new IllegalArgumentException(
					"Empty encrypted credential string.");
		}

		String[] tokens = credentialString.split(":", 2);
		if (tokens.length != 2) {
			throw new IllegalArgumentException(
					"Incorrect number of components in encrypted credential string.");
		}

		EncryptedCredentials encryptedCreds = new EncryptedCredentials();
		encryptedCreds.setHashedPassword(tokens[0]);
		encryptedCreds.setUsername(tokens[1]);
		return encryptedCreds;
	}

	public EncryptedCredentials() {
	}

	public EncryptedCredentials(final String username,
			final String hashedPassword) {
		this.username = username;
		this.hashedPassword = hashedPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	@Override
	public String toString() {
		return String.format("%s:%s", hashedPassword, username);
	}
}
