/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.annotation;

import java.lang.annotation.*;

/**
 * The configuration annotation
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Configuration {

}
