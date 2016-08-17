package com.ehi.msi.enterprisecarshare.web.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import com.ehi.msi.enterprisecarshare.dto.LoginRS;
import com.ehi.msi.enterprisecarshare.dto.LoginRequest;
import com.ehi.msi.test.AbstractSpringTest;

@Ignore
// The Integration test cases will fail, once the users chosen here gets updated and the renewal flag value changes. Hence they are
// marked ignored. Particular users can be used to test a particular scenario as and when required by removing the @Ignore
// annotation.
public class AuthenticationControllerIntegrationTest extends AbstractSpringTest {

    @Autowired
    private AuthenticationController controller;

    @Autowired
    private MockHttpServletRequest httpServletRequest;

    @Before
    public void setupRequestHeader() {
        httpServletRequest.addHeader("Accept-Language", "en_US");
        httpServletRequest.addHeader("Ehi-API-Key", "1Q7VbsO93CPDMxTyzYp0ADnXG56gaRymAsp3vRiTcfw");
        httpServletRequest.addHeader("appPlatform", "android");
    }

    @Test
    public void testLoginActionWithQualStatus0() {
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("8246-14");
        loginRequest.setPassword("password");
        final LoginRS response = controller.login(loginRequest, httpServletRequest);

        assertNotNull(response.getAuthorizationToken());
    }
}
