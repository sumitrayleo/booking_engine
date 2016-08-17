package com.ehi.msi.enterprisecarshare.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ehi.msi.enterprisecarshare.enumeration.SerializerType;

/**
 * Custom Annotation to be used on various Serializer Instances. Instances
 * marked with this annotation is supposed to be spring managed components.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Serializer {

	public SerializerType value() default SerializerType.DEFAULT;
}
