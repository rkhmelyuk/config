package com.prutsoft.config.element.value;

import com.prutsoft.config.exception.ValueFormatException;

/**
 * The Double value type.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-02
 */
class DoubleValueType extends AbstractValueType<Double> {

    public Double valueOf(String value) throws ValueFormatException {
        try {
            return value != null ? Double.valueOf(value) : null;
        }
        catch (Exception e) {
            throw new ValueFormatException("Error to parse double value: " + value);
        }
    }

    public String stringOf(Double value) {
        return value != null ? Double.toString(value) : null;
    }
}
