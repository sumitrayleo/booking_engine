/*******************************************************************************
 * FOR INTERNAL USE ONLY. NOT A CONTRIBUTION.
 *         
 * This software source code contains valuable, confidential, trade secret
 * information owned by Enterprise Rent-A-Car Company and is protected by
 * copyright laws and international copyright treaties, as well as other
 * intellectual property laws and treaties.
 * 
 * ACCESS TO AND USE OF THIS SOURCE CODE IS RESTRICTED TO AUTHORIZED PERSONS
 * WHO HAVE ENTERED INTO CONFIDENTIALITY AGREEMENTS WITH ENTERPRISE RENT-A-CAR
 * COMPANY.
 * 
 * This source code may not be licensed, disclosed or used except as authorized
 * in writing by a duly authorized officer of Enterprise Rent-A-Car Company.
 ******************************************************************************/
package com.ehi.msi.test;

import java.util.UUID;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ehi.msi.enterprisecarshare.spring.MsiContextTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MsiContextTestConfig.class }, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public abstract class AbstractSpringTest extends Assert {
    static {
        System.setProperty("spring.profiles.active", "dev");
        MDC.put("Ehi-CSMA-Request-Id", UUID.randomUUID().toString());
    }
}