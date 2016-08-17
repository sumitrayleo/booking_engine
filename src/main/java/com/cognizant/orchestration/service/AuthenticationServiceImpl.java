package com.cognizant.orchestration.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cognizant.orchestration.dto.LoginRS;
import com.cognizant.orchestration.dto.LoginRequest;
import com.cognizant.orchestration.dto.LogoutRS;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(AuthenticationServiceImpl.class);

	@Override
	public LoginRS login(final LoginRequest loginRequest) {

		if (loginRequest == null) {
			LOGGER.error("Null  call parameters. Malformed login request.");
			throw new IllegalArgumentException("Invalid login request.");
		}

		final String msg = String.format(
				"Processing cleartext login request, username %s",
				loginRequest.getUsername());
		LOGGER.debug(msg);

		return populateLoginResponse();
	}

	@Override
	public LogoutRS logout(final String authToken) {
		LogoutRS logoutRS = new LogoutRS();
		logoutRS.setStatus(true);

		return logoutRS;
	}

	private LoginRS populateLoginResponse() {
		LoginRS loginRS = new LoginRS();
		loginRS.setAuthorizationToken(generateAuthToken());

		return loginRS;
	}

	protected String generateAuthToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
