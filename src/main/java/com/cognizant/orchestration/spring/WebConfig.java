package com.cognizant.orchestration.spring;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.cognizant.orchestration.web.handler.ExtendedExceptionHandlerExceptionResolver;
import com.cognizant.orchestration.web.handler.GlobalExceptionHandler;
import com.cognizant.orchestration.web.interceptor.LoggingInterceptor;
import com.cognizant.orchestration.web.interceptor.SecurityHandlerInterceptor;

@Configuration
@ComponentScan(basePackages = { "com.cognizant.orchestration" }, includeFilters = @Filter(Controller.class))
@EnableAspectJAutoProxy
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Value("${app.docs}")
    private String docsLocation;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityHandlerInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/status");
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/api/**");
    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> messageConverters) {
        MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJacksonHttpMessageConverter.setPrettyPrint(false);
        messageConverters.add(mappingJacksonHttpMessageConverter);
        super.configureMessageConverters(messageConverters);
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        globalExceptionHandler.setMessageSource(messageSource());
        return globalExceptionHandler;
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        ExtendedExceptionHandlerExceptionResolver customResolver = new ExtendedExceptionHandlerExceptionResolver();
        customResolver.setExceptionHandler(globalExceptionHandler());
        customResolver.setMessageConverters(getMessageConverters());
        customResolver.afterPropertiesSet();

        exceptionResolvers.add(customResolver);
        exceptionResolvers.add(new ResponseStatusExceptionResolver());
        exceptionResolvers.add(new DefaultHandlerExceptionResolver());
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setCacheSeconds(10);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public Properties buildProperties() throws IOException {
        Resource resource = new ClassPathResource("/build.properties");
        return PropertiesLoaderUtils.loadProperties(resource);
    }

    // Added for Spring view resolver
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return resolver;

    }

    // This resolves URL from file names
    @Bean(name = "urlViewController")
    public UrlFilenameViewController getUrlViewController() {
        return new UrlFilenameViewController();
    }

    // This view resolver resolves HTML views to JSP
    @Bean
    public SimpleUrlHandlerMapping getUrlHandlerMapping() {
        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.put("/**/*.html", "urlViewController");
        handlerMapping.setMappings(mappings);
        return handlerMapping;
    }

    // Added for Swagger UI
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/docs/**").addResourceLocations(docsLocation);
    }

}
