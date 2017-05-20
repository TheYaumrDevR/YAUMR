package de.ethasia.yaumr.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Classes annoted by this annotation can use the AutowiringMethod on their methods.
 * These are then called by the ClassInstanceContainer during startup.
 * 
 * @author R
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface AutowiringClass {}