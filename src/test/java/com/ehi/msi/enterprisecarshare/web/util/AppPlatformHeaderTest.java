package com.ehi.msi.enterprisecarshare.web.util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import com.ehi.msi.enterprisecarshare.exception.InvalidAppPlatformException;
import com.ehi.msi.test.AbstractSpringTest;

public class AppPlatformHeaderTest extends AbstractSpringTest {
    @Autowired
    private MockHttpServletRequest request;

    @Test(expected = InvalidAppPlatformException.class)
    public void testMissingHeader() {
        AppPlatformHeader.getHeader(request);
    }

    @Test(expected = InvalidAppPlatformException.class)
    public void testBad() {
        request.addHeader("appPlatform", "windowsphone");
        AppPlatformHeader.getHeader(request);
    }

    @Test
    public void testAndroidLowercase() {
        request.addHeader("appPlatform", "android");
        AppPlatformHeader.getHeader(request);
    }

    @Test
    public void testAndroidMixedCase() {
        request.addHeader("appPlatform", "Android");
        AppPlatformHeader.getHeader(request);
    }

    @Test
    public void testIOSLowercase() {
        request.addHeader("appPlatform", "ios");
        AppPlatformHeader.getHeader(request);
    }

    @Test
    public void testIOSMixedCase() {
        request.addHeader("appPlatform", "iOS");
        AppPlatformHeader.getHeader(request);
    }
}