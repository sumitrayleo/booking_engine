package com.cognizant.orchestration.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;

/**
 * Configuration Class for Swagger.Through this class Swagger to be configured
 * using custom implementation for those controllers for which Swagger to be
 * implemented.
 */
@Configuration
@Profile(value = { "dev", "tst" })
// Profile dev is for local, Profile tst is for non-prod environments where
// swagger will work and for profile "prd" it would not
@EnableSwagger
public class SwaggerConfig {
	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	/**
	 * Method to configure which controllers, more specifically, which Spring
	 * RequestMappings to include in the swagger Resource Listing.
	 * 
	 * @return
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(
				this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(
				"/api/.*");
		return swaggerSpringMvcPlugin;
	}

	/**
	 * API Info as it appears on the swagger-ui page
	 */
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("CarShare Mobile Service Interface",
				"CarShare Mobile Service Interface Documentation",
				"API terms of service", "MSI Team", "API License Information",
				"");
		return apiInfo;
	}

}
