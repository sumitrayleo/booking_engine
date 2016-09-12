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

import com.cognizant.orchestration.dto.EmptyResponse;
import com.cognizant.orchestration.dto.RegisterDeviceRequest;
import com.cognizant.orchestration.exception.BookingApplException;
import com.cognizant.orchestration.util.DeviceDetailsConstant;
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
    public EmptyResponse registerDevice(
        @ApiParam(value = "Register Device Request") @RequestBody final RegisterDeviceRequest registerDeviceRequest) {
        if (ObjectUtils.isEmpty(registerDeviceRequest)) {
            throw new BookingApplException("Please specifiy device registration request details");
        }
        deviceDetailsMap.put(registerDeviceRequest.getAppName(), registerDeviceRequest.getDeviceId());
        final EmptyResponse response = new EmptyResponse();
        response.setSuccess(DeviceDetailsConstant.DEVICE_REGISTERED_SUCCESS_MSG);
        return response;
    }

    /**
     * Return the device Id by app Name
     * 
     * @param appName
     * @return device Id
     * @throws BookingApplException
     */
    @RequestMapping(value = "/api/booking/device/info", method = RequestMethod.GET)
    public String getDeviceId(@ApiParam(value = "Get Device Id Request") @RequestParam final String appName) {
        if (StringUtils.isBlank(appName)) {
            throw new BookingApplException("Please specifiy an app name");
        }
        final String deviceId = deviceDetailsMap.get(appName);
        if (StringUtils.isEmpty(deviceId)) {
            return DeviceDetailsConstant.DEVICE_NOT_FOUND_MSG;
        } else {
            return String.format(DeviceDetailsConstant.DEVICE_FOUND,deviceId);
        }
    }
}
