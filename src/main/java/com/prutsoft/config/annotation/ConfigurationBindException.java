/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.annotation;

/**
 * Error to bind annotated configuration.
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-10
 */
public class ConfigurationBindException extends RuntimeException {

    public ConfigurationBindException(String message) {
        super(message);
    }

    public ConfigurationBindException(String message, Throwable cause) {
        super(message, cause);
    }
}
