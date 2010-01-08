package com.prutsoft.config.element.value;

import com.prutsoft.config.exception.ValueFormatException;

/**
 * The Integer value type.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-02
 */
class IntegerValueType extends AbstractValueType<Integer> {

    public Integer valueOf(String value) throws ValueFormatException {
        try {
            return value != null ? Integer.valueOf(value) : null;
        }
        catch (Exception e) {
            throw new ValueFormatException("Error to parse integer value: " + value);
        }
    }

    public String stringOf(Integer value) {
        return value != null ? Integer.toString(value) : null;
    }
}
