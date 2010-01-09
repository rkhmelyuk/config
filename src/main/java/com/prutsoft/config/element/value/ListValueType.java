/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.value;

import com.prutsoft.core.utils.collections.CollectionUtils;
import com.prutsoft.core.utils.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The List value type. Result is a list of strings.
 * Input is a comma separated values string.
 *
 * @author Ruslan Khmelyuk
 * @since 2010-01-02
 */
class ListValueType extends AbstractValueType<List<String>> {

    /**
     * The list values separator.
     */
    private static final String SEPARATOR = ",";

    public List<String> valueOf(String value) {
        final List<String> result;
        if (value != null) {
            result = ListUtils.strListFromString(value, SEPARATOR);
        }
        else {
            result = new ArrayList<String>(0);
        }
        return result;
    }

    public String stringOf(List<String> value) {
        return value != null ? CollectionUtils.join(value, SEPARATOR) : null;
    }
}
