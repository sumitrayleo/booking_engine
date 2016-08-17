package com.cognizant.orchestration.service;

import com.cognizant.orchestration.dto.LoginRS;
import com.cognizant.orchestration.dto.LoginRequest;
import com.cognizant.orchestration.dto.LogoutRS;

public interface AuthenticationService {

    LoginRS login(final LoginRequest loginRequest);

    LogoutRS logout();

}
