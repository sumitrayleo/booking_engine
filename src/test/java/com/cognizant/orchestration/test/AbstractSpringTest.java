package com.cognizant.orchestration.test;

import java.util.UUID;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cognizant.orchestration.spring.BookingApplContextTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BookingApplContextTestConfig.class }, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public abstract class AbstractSpringTest extends Assert {
    static {
        System.setProperty("spring.profiles.active", "dev");
        MDC.put("Booking-Request-Id", UUID.randomUUID().toString());
    }
}