package com.ehi.msi.enterprisecarshare.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehi.msi.enterprisecarshare.dto.EmptyResponse;
import com.ehi.msi.enterprisecarshare.util.SwaggerConstant;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@Api(value = "apiInquiry", description = "Test Api key")
public class ApiInquiryController {
	@ApiOperation(value = "Service to test Api Key.")
	@RequestMapping(value = "/api/testApiKey", method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(name = SwaggerConstant.ACCEPT_LANGUAGE, required = true, dataType = "string", paramType = "header", defaultValue = SwaggerConstant.DEFAULT_LOCALE),
			@ApiImplicitParam(name = SwaggerConstant.EHI_API_KEY, required = true, dataType = "string", paramType = "header", defaultValue = SwaggerConstant.EHI_API_KEY_DEFAULT_VALUE) })
	public EmptyResponse testApiKey() {
		return new EmptyResponse();
	}
}
