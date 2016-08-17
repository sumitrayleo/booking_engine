package com.cognizant.orchestration.web.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Autowired
    private Properties buildProperties;

    @RequestMapping(value = "/api/status", method = RequestMethod.GET)
    public void status(HttpServletResponse resp) throws IOException, ClassCastException {
        buildProperties.list(resp.getWriter());
    }
}