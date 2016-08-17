package com.ehi.msi.enterprisecarshare.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehi.msi.enterprisecarshare.dto.LoginRS;
import com.ehi.msi.enterprisecarshare.dto.LoginRequest;
import com.ehi.msi.enterprisecarshare.dto.LogoutRS;
import com.ehi.msi.enterprisecarshare.service.AuthenticationService;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationService authService;

	@Value("${ehi.auth.token.header.name}")
	private String authTokenHeaderName;

	@RequestMapping(value = "/api/autovera/login", method = RequestMethod.POST)
	public LoginRS login(
			@ApiParam(value = "Login Request") @RequestBody LoginRequest loginRequest,
			HttpServletRequest httpServletRequest) {

		return authService.login(loginRequest);
	}

	@RequestMapping(value = "/api/autovera/logout", method = RequestMethod.POST)
	public LogoutRS logout(final HttpServletRequest httpServletRequest) {
		final String authToken = httpServletRequest
				.getHeader(authTokenHeaderName);
		return authService.logout(authToken);
	}
}
