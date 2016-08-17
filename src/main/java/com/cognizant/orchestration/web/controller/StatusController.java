package com.cognizant.orchestration.web.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.orchestration.dto.ErrorResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@Api(value = "statusController", description = "Verify MSI application status.")
public class StatusController {

	@Autowired
	private Properties buildProperties;

	/**
	 * This method sets the standard build properties obtained from Jenkins
	 * build variables into HTTP Servlet Response
	 * 
	 * @param resp
	 *            - HttpServletResponse
	 * @throws {@link IOException}
	 * @throws {@link ClassCastException}
	 */
	@ApiOperation(value = "Service to verify MSI is up and running by sending an HTTP GET.", notes = "Method to verify MSI is up and running.")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Bad Request,Invalid parameters", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden,Invalid API key", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized,Invalid or expired authentication token", response = ErrorResponse.class) })
	@RequestMapping(value = "/api/status", method = RequestMethod.GET)
	public void status(HttpServletResponse resp) throws IOException,
			ClassCastException {
		buildProperties.list(resp.getWriter());
	}
}