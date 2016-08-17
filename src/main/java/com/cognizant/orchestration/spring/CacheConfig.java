package com.cognizant.orchestration.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:cache-ehcache-context.xml" })
public class CacheConfig {

	public CacheConfig() {
		super();
	}

}