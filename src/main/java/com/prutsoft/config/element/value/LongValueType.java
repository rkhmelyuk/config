/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.value;

import com.prutsoft.config.exception.ValueFormatException;

/**
 * The Long value type.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-02
 */
class LongValueType extends AbstractValueType<Long> {

    public Long valueOf(String value) throws ValueFormatException {
        try {
            return value != null ? Long.valueOf(value) : null;
        }
        catch (Exception e) {
            throw new ValueFormatException("Error to parse long value: " + value);
        }
    }

    public String stringOf(Long value) {
        return value != null ? Long.toString(value) : null;
    }
}
