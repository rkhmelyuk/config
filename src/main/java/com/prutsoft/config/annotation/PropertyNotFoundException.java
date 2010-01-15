/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.annotation;

/**
 * Exception to bind property that is not found.
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-10
 */
public class PropertyNotFoundException extends ConfigurationBindException {

    public PropertyNotFoundException(String message) {
        super(message);
    }

    public PropertyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
