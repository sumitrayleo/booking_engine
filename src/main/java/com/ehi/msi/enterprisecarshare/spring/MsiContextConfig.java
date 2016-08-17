package com.ehi.msi.enterprisecarshare.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.stereotype.Controller;

import com.thoughtworks.xstream.XStream;

@Configuration
@ComponentScan(basePackages = { "com.ehi.msi.enterprisecarshare", "com.ehi.cts" }, excludeFilters = { @Filter(Controller.class)})
@PropertySource({ "classpath:carshare.properties", "classpath:properties/msi-default-locale.properties" })
@EnableMBeanExport(registration = RegistrationPolicy.REPLACE_EXISTING)
public class MsiContextConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	/**
     * Returns a singleton spring managed bean instance of XStream
     * 
     * 
     * @return
     */
    @Bean
    public XStream xStream() {
        return new XStream();
    }

}
