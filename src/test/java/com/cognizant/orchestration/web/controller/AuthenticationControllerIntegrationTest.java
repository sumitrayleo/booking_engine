package com.cognizant.orchestration.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import com.cognizant.orchestration.dto.LoginRS;
import com.cognizant.orchestration.dto.LoginRequest;
import com.cognizant.orchestration.test.AbstractSpringTest;

public class AuthenticationControllerIntegrationTest extends AbstractSpringTest {

    @Autowired
    private AuthenticationController controller;

    @Autowired
    private MockHttpServletRequest httpServletRequest;

    @Before
    public void setupRequestHeader() {
        httpServletRequest.addHeader("Accept-Language", "en_US");
        httpServletRequest.addHeader("Booking-API-Key", "1Q7VbsO93CPDMxTyzYp0ADnXG56gaRymAsp3vRiTcfw");
        httpServletRequest.addHeader("appPlatform", "android");
    }

    @Test
    public void testLoginActionWithQualStatus0() {
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("123");
        loginRequest.setPassword("password");
        final LoginRS response = controller.login(loginRequest, httpServletRequest);

        assertNotNull(response.getAuthorizationToken());
    }
}
