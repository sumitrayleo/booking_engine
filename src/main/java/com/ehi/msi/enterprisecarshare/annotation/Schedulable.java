package com.ehi.msi.enterprisecarshare.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom Annotation to be used on Service Health Check Threads
 * 
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Schedulable {

	public String groupName();

	public String instanceName() default "";

	public String countryCode() default "US";
}
