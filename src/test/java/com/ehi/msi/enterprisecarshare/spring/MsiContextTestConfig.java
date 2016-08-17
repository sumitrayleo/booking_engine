package com.ehi.msi.enterprisecarshare.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

@ComponentScan(basePackages = { "com.ehi.msi.enterprisecarshare", "com.ehi.cts" }, excludeFilters = { @Filter(Controller.class)})
@PropertySource({ "classpath:carshare.properties", "classpath:properties/msi-default-locale.properties" })
public class MsiContextTestConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
