package com.ehi.msi.enterprisecarshare.service;

import com.ehi.msi.enterprisecarshare.dto.LoginRS;
import com.ehi.msi.enterprisecarshare.dto.LoginRequest;
import com.ehi.msi.enterprisecarshare.dto.LogoutRS;

public interface AuthenticationService {

	LoginRS login(final LoginRequest loginRequest);

	LogoutRS logout(final String authToken);

}
