/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.resource;

import com.prutsoft.config.exception.ConfigurationException;

/**
 * Error to load resource content.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-05
 */
public class ResourceLoadException extends ConfigurationException {

    public ResourceLoadException(String message) {
        super(message);
    }

    public ResourceLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
