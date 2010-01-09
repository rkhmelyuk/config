/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.exception;

/**
 * Error to read or write property element.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public class PropertyParseException extends ParseException {

    public PropertyParseException(String message) {
        super(message);
    }

    public PropertyParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
