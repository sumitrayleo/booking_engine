package com.cognizant.orchestration.web.controller;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;

import com.cognizant.orchestration.test.AbstractSpringTest;
import com.cognizant.orchestration.web.controller.StatusController;

/**
 * Integration Test class to test build status
 * 
 */
public class StatusControllerIntegrationTest extends AbstractSpringTest {

    @Autowired
    private StatusController controller;

    @Autowired
    private MockHttpServletResponse response;

    @Test
    public void testMSIResponse() throws ClassCastException, IOException {
        controller.status(response);
        Assert.assertNotNull(response.getContentAsString());
    }
}
