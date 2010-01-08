package com.prutsoft.config.element.value;

import com.prutsoft.core.utils.collections.CollectionUtils;
import com.prutsoft.core.utils.collections.SetUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * The Set value type. Result is a set of strings.
 * Input is a comma separated values string.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-02
 */
class SetValueType extends AbstractValueType<Set<String>> {

    /**
     * The list values separator.
     */
    private static final String SEPARATOR = ",";

    public Set<String> valueOf(String value) {
        final Set<String> result;
        if (value != null) {
            result = SetUtils.strSetFromString(value, SEPARATOR);
        }
        else {
            result = new HashSet<String>(0);
        }
        return result;
    }

    public String stringOf(Set<String> value) {
        return value != null ? CollectionUtils.join(value, SEPARATOR) : null;
    }
}
