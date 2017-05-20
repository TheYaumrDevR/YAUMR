package de.ethasia.yaumr.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Static methods without parameter can use this annotation. 
 * When annotated by this annotation and inside a class with 
 * the @AutowiringClass annotation these methods are called by 
 * the ClassInstanceContainer during startup.
 * 
 * @author R
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AutowiringMethod {}
