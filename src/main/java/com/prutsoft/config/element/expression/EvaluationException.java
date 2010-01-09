/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.expression;

import com.prutsoft.config.exception.ValueAccessException;

/**
 * Error to evaluate expression.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public class EvaluationException extends ValueAccessException {

    public EvaluationException(String message) {
        super(message);
    }

    public EvaluationException(String message, Throwable cause) {
        super(message, cause);
    }
}
