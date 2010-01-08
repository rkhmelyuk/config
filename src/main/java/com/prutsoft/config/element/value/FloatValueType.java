package com.prutsoft.config.element.value;

import com.prutsoft.config.exception.ValueFormatException;

/**
 * The Double value type.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-02
 */
class FloatValueType extends AbstractValueType<Float> {

    public Float valueOf(String value) throws ValueFormatException {
        try {
            return value != null ? Float.valueOf(value) : null;
        }
        catch (Exception e) {
            throw new ValueFormatException("Error to parse float value: " + value);
        }
    }

    public String stringOf(Float value) {
        return value != null ? Float.toString(value) : null;
    }
}
