/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.annotation;

import java.lang.annotation.*;

/**
 * The configuration property with it's name.
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Property {

    /**
     * The property name.
     * @return the property name.
     */
    String value();
}
