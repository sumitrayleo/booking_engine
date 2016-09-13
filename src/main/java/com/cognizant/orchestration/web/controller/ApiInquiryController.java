package com.cognizant.orchestration.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.orchestration.dto.BaseResponse;

@RestController
public class ApiInquiryController {
    @RequestMapping(value = "/api/testApiKey", method = RequestMethod.GET)
    public BaseResponse testApiKey() {
        final BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSuccess(true);
        return baseResponse;
    }
}
