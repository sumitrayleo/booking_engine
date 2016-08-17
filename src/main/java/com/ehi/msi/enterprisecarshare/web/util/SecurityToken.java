package com.ehi.msi.enterprisecarshare.web.util;

import java.io.Serializable;

public class SecurityToken implements Serializable {
	private final static long serialVersionUID = -5387192812928202430L;

	private String authenticationToken;
	private EncryptedCredentials encryptedCredentials;

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public EncryptedCredentials getEncryptedCredentials() {
		return encryptedCredentials;
	}

	public void setEncryptedCredentials(
			EncryptedCredentials encryptedCredentials) {
		this.encryptedCredentials = encryptedCredentials;
	}
}
