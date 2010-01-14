/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config;

import com.prutsoft.core.asserts.ArgumentAssert;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Context with useful methods.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-04
 */
public class ContextMap implements Serializable {

    private static final ContextMap EMPTY_CONTEXT_MAP = new ReadOnlyContextMap();

    /**
     * Returns new empty content map that is unmodifiable
     * @return the empty unmodifiable context map.
     */
    public static ContextMap empty() {
        return EMPTY_CONTEXT_MAP;
    }

    // ---------------------------------------------------------------- Instance members

    private Map<String, Object> map = new HashMap<String, Object>();

    public ContextMap() {
    }

    public ContextMap(Map<String, Object> map) {
        this.map.putAll(map);
    }

    public ContextMap(String key, Object value) {
        put(key, value);
    }

    public ContextMap(String key1, Object value1, String key2, Object value2) {
        put(key1, value1, key2, value2);
    }

    public ContextMap(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        put(key1, value1, key2, value2, key3, value3);
    }

    public ContextMap(String key1, Object value1, String key2, Object value2,
                      String key3, Object value3, String key4, Object value4) {
        put(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    public ContextMap(String key1, Object value1, String key2, Object value2,
                      String key3, Object value3, String key4, Object value4,
                      String key5, Object value5) {
        put(key1, value1, key2, value2, key3, value3, key4, value4, key5, value5);
    }

    public ContextMap(String key1, Object value1, String key2, Object value2,
                      String key3, Object value3, String key4, Object value4,
                      String key5, Object value5, String key6, Object value6) {
        put(key1, value1, key2, value2,
                key3, value3, key4, value4,
                key5, value5, key6, value6);
    }


    public Map<String, Object> getMap() {
        return map;
    }

    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    public void put(String key1, Object value1, String key2, Object value2) {
        map.put(key1, value1);
        map.put(key2, value2);
    }

    public void put(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key2, value2);
    }

    public void put(String key1, Object value1, String key2, Object value2,
                    String key3, Object value3, String key4, Object value4) {
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
    }

    public void put(String key1, Object value1, String key2, Object value2,
                    String key3, Object value3, String key4, Object value4,
                    String key5, Object value5) {
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
    }

    public void put(String key1, Object value1, String key2, Object value2,
                    String key3, Object value3, String key4, Object value4,
                    String key5, Object value5, String key6, Object value6) {
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
    }

    public void put(Object... keysAndValues) {
        ArgumentAssert.isTrue(keysAndValues.length % 2 == 0, "Each key should have value and vice versa.");

        for (int i = 0; i < keysAndValues.length; i += 2) {
            final String key = (String) keysAndValues[i];
            final Object value = keysAndValues[i + 1];

            put(key, value);
        }
    }

    public Object get(String key) {
        return map.get(key);
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public Object remove(String key) {
        return map.remove(key);
    }

    public void putAll(Map<String, Object> map) {
        this.map.putAll(map);
    }

    // ------------------------------------------------------------

    private static class ReadOnlyContextMap extends ContextMap {

        @Override
        public Map<String, Object> getMap() {
            return Collections.emptyMap();
        }

        @Override
        public Object put(String key, Object value) {
            throw new UnsupportedOperationException("Operation is not supported.");
        }

        @Override
        public void put(String key1, Object value1, String key2, Object value2) {
            throw new UnsupportedOperationException("Operation is not supported.");
        }

        @Override
        public void put(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
            throw new UnsupportedOperationException("Operation is not supported.");
        }

        @Override
        public void put(String key1, Object value1, String key2, Object value2,
                        String key3, Object value3, String key4, Object value4) {
            throw new UnsupportedOperationException("Operation is not supported.");
        }

        @Override
        public void put(String key1, Object value1, String key2, Object value2,
                        String key3, Object value3, String key4, Object value4,
                        String key5, Object value5) {
            throw new UnsupportedOperationException("Operation is not supported.");
        }

        @Override
        public void put(String key1, Object value1, String key2, Object value2,
                        String key3, Object value3, String key4, Object value4,
                        String key5, Object value5, String key6, Object value6) {
            throw new UnsupportedOperationException("Operation is not supported.");
        }

        @Override
        public void put(Object... keysAndValues) {
            throw new UnsupportedOperationException("Operation is not supported.");
        }

        @Override
        public Object get(String key) {
            return null;
        }

        @Override
        public boolean contains(String key) {
            return false;
        }

        @Override
        public Object remove(String key) {
            throw new UnsupportedOperationException("Operation is not supported.");
        }

        @Override
        public void putAll(Map<String, Object> map) {
            throw new UnsupportedOperationException("Operation is not supported.");
        }
    }

}
