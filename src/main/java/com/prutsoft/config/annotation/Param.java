/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.annotation;

import java.lang.annotation.*;

/**
 * The param for the configuration property context.
 * This annotation must be used to the method parameters,
 * which will be used as expression variable value.
 * Use this annotation to set also context name for the parameter.
 * For example:
 * <pre>
 *  @ Configuration
 *  String getUserName(@Param("id") int userId)
 * </pre>
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Param {

    /**
     * The parameter name.
     * @return the parameter name.
     */
    String value();
    
}
