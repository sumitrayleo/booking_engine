package com.ehi.msi.enterprisecarshare.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LoginRS implements Serializable {
	private String authorizationToken;

	public String getAuthorizationToken() {
		return authorizationToken;
	}

	public void setAuthorizationToken(String authorizationToken) {
		this.authorizationToken = authorizationToken;
	}

}
