package com.cognizant.orchestration.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

@ComponentScan(basePackages = { "com.cognizant.orchestration"}, excludeFilters = { @Filter(Controller.class)})
@PropertySource({ "classpath:application.properties", "classpath:properties/default-locale.properties" })
public class BookingApplContextTestConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
