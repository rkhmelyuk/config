/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.exception;

/**
 * Error to read or write property value element.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public class ValueFormatException extends ValueAccessException {

    public ValueFormatException(String message) {
        super(message);
    }

    public ValueFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
