package com.cognizant.orchestration.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.orchestration.dto.BaseResponse;
import com.cognizant.orchestration.dto.RegisterDeviceRequest;
import com.cognizant.orchestration.dto.RegisterDeviceResponse;
import com.cognizant.orchestration.exception.BookingApplException;
import com.cognizant.orchestration.util.ApplicationConstant;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * Rest Controller class for handling device related services like registration, lookup etc.
 * 
 */
@RestController
public class DeviceController {

    @Resource
    private Map<String, String> deviceDetailsMap;

    /**
     * Register device Id with application Name
     * 
     * @param registerDeviceRequest
     * @return success message
     */
    @RequestMapping(value = "/api/booking/device/info", method = RequestMethod.POST)
    public BaseResponse registerDevice(
        @ApiParam(value = "Register Device Request") @RequestBody final RegisterDeviceRequest registerDeviceRequest) {
        if (ObjectUtils.isEmpty(registerDeviceRequest)) {
            throw new BookingApplException("Please specifiy device registration request details");
        }
        deviceDetailsMap.put(registerDeviceRequest.getDeviceId(), ApplicationConstant.DEVICE_ACTIVE_Y);
        final BaseResponse response = new BaseResponse();
        response.setSuccess(true);
        response.setMessage(ApplicationConstant.DEVICE_REGISTERED_SUCCESS_MSG);
        return response;
    }

    /**
     * Return the device Id by app Name
     * 
     * @param deviceId
     * @return device Id
     * @throws BookingApplException
     */
    @RequestMapping(value = "/api/booking/device/info", method = RequestMethod.GET)
    public RegisterDeviceResponse getDeviceId(@ApiParam(value = "Get Device Id Request") @RequestParam final String deviceId) {
        RegisterDeviceResponse registerDeviceResponse = new RegisterDeviceResponse();
        if (StringUtils.isBlank(deviceId)) {
            throw new BookingApplException("Please specifiy an app name");
        }
        final String status = deviceDetailsMap.get(deviceId);
        if (ApplicationConstant.DEVICE_ACTIVE_Y.equals(status)) {
            registerDeviceResponse.setDeviceId(deviceId);
            registerDeviceResponse.setSuccess(true);
        } else {
            registerDeviceResponse.setSuccess(false);
            registerDeviceResponse.setMessage(ApplicationConstant.DEVICE_NOT_FOUND_MSG);
        }
        return registerDeviceResponse;
    }
}
