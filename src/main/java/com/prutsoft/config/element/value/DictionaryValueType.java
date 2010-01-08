package com.prutsoft.config.element.value;

import com.prutsoft.config.exception.ValueFormatException;
import com.prutsoft.core.utils.collections.ListUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Dictionary value type.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-02
 */
class DictionaryValueType extends AbstractValueType<Map<String, String>> {

    private static final String PAIRS_SEPARATOR = ",";
    private static final String KEY_VALUE_SEPARATOR = ":";

    public Map<String, String> valueOf(String value) throws ValueFormatException {
        final Map<String, String> result = new HashMap<String, String>();
        if (value != null) {
            final List<String> pairs = ListUtils.strListFromString(value, PAIRS_SEPARATOR);
            for (String each : pairs) {
                final String[] pair = each.split(KEY_VALUE_SEPARATOR);
                if (pair.length != 2) {
                    throw new ValueFormatException("Dictionary key-value pair has wrong format, expected: 'key:value'");
                }

                final String key = pair[0].trim();
                if (result.containsKey(key)) {
                    throw new ValueFormatException("Dictionary already contains specified key: " + key);
                }
                result.put(key, pair[1].trim());
            }
        }

        return result;
    }

    public String stringOf(Map<String, String> value) {
        if (value == null) return null;

        final StringBuilder result = new StringBuilder(value.size() * 10);

        boolean first = true;
        for (Map.Entry<String, String> eachEntry : value.entrySet()) {
            if (first) {
                first = false;
            }
            else {
                result.append(PAIRS_SEPARATOR);
            }

            result // {
                    .append(eachEntry.getKey())
                    .append(KEY_VALUE_SEPARATOR)
                    .append(eachEntry.getValue());
            // {
        }
        return result.toString();
    }
}
