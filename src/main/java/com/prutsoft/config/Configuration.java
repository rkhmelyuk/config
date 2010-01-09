/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config;

import com.prutsoft.config.element.metadata.Metadata;
import com.prutsoft.core.annotation.NotNull;
import com.prutsoft.core.annotation.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents configuration with it's internal information and properties.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2009-12-20
 */
public interface Configuration extends NamedElement, NamedElementsContainer {

    /**
     * Gets the configuration name.
     *
     * @return the configuration name.
     */
    @NotNull
    String getName();

    /**
     * Gets the configuration version.
     *
     * @return the configuration version.
     */
    @NotNull
    Version getVersion();

    /**
     * Gets the configuration metadata.
     *
     * @return the configuration metadata.
     */
    @NotNull
    Metadata getMetadata();

    /**
     * Gets the pojo value.
     *
     * @param clazz    the class of pojo type.
     * @param property the property name; can't be null or empty.
     * @param <T>      the pojo class type.
     * @return the pojo as value.
     */
    @Nullable
    <T> T getPojo(Class<T> clazz, String property);

    /**
     * Gets integer property.
     *
     * @param property the property name; can't be null or empty.
     * @return the integer property value.
     */
    @Nullable
    Integer getInteger(String... property);

    /**
     * Gets long property.
     *
     * @param property the property name; can't be null or empty.
     * @return the long property value.
     */
    @Nullable
    Long getLong(String... property);

    /**
     * Gets boolean property.
     *
     * @param property the property name; can't be null or empty.
     * @return the boolean property value.
     */
    @Nullable
    Boolean getBoolean(String... property);

    /**
     * Gets float property.
     *
     * @param property the property name; can't be null or empty.
     * @return the float property value.
     */
    @Nullable
    Float getFloat(String... property);

    /**
     * Gets double property.
     *
     * @param property the property name; can't be null or empty.
     * @return the double property value.
     */
    Double getDouble(String... property);

    /**
     * Gets string property.
     *
     * @param property the property name; can't be null or empty.
     * @return the string property value.
     */
    @Nullable
    String getString(String... property);

    /**
     * Gets strings list property.
     *
     * @param property the property name; can't be null or empty.
     * @return the strings list property value.
     */
    @Nullable
    List<String> getStringsList(String... property);

    /**
     * Gets strings set property.
     *
     * @param property the property name; can't be null or empty.
     * @return the strings set property value.
     */
    @Nullable
    Set<String> getStringsSet(String... property);

    /**
     * Gets dictionary property.
     *
     * @param property the property name; can't be null or empty.
     * @return the dictionary property value.
     */
    @Nullable
    Map<String, String> getDictionary(String... property);

     /**
     * Gets property value.
     *
     * @param property the property name; can't be null or empty.
     * @return the property value.
     */
    @Nullable
    Object getValue(String... property);

    /**
     * Gets integer property.
     *
     * @param property the property name; can't be null or empty.
     * @param context  the context for evaluating property value.
     * @return the integer property value.
     */
    @Nullable
    Integer getInteger(String property, ContextMap context);

    /**
     * Gets long property.
     *
     * @param property the property name; can't be null or empty.
     * @param context  the context for evaluating property value.
     * @return the long property value.
     */
    @Nullable
    Long getLong(String property, ContextMap context);

    /**
     * Gets boolean property.
     *
     * @param property the property name; can't be null or empty.
     * @param context  the context for evaluating property value.
     * @return the boolean property value.
     */
    @Nullable
    Boolean getBoolean(String property, ContextMap context);

    /**
     * Gets float property.
     *
     * @param property the property name; can't be null or empty.
     * @param context  the context for evaluating property value.
     * @return the float property value.
     */
    @Nullable
    Float getFloat(String property, ContextMap context);

    /**
     * Gets double property.
     *
     * @param property the property name; can't be null or empty.
     * @param context  the context for evaluating property value.
     * @return the double property value.
     */
    @Nullable
    Double getDouble(String property, ContextMap context);

    /**
     * Gets string property.
     *
     * @param property the property name; can't be null or empty.
     * @param context  the context for evaluating property value.
     * @return the string property value.
     */
    @Nullable
    String getString(String property, ContextMap context);

    /**
     * Gets strings list property.
     *
     * @param property the property name; can't be null or empty.
     * @param context  the context for evaluating property value.
     * @return the strings list property value.
     */
    @Nullable
    List<String> getStringsList(String property, ContextMap context);

    /**
     * Gets strings set property.
     *
     * @param property the property name; can't be null or empty.
     * @param context  the context for evaluating property value.
     * @return the strings set property value.
     */
    @Nullable
    Set<String> getStringsSet(String property, ContextMap context);

    /**
     * Gets dictionary property.
     *
     * @param property the property name; can't be null or empty.
     * @param context  the context for evaluating property value.
     * @return the dictionary property value.
     */
    @Nullable
    Map<String, String> getDictionary(String property, ContextMap context);

    /**
     * Gets property value.
     *
     * @param property the property name; can't be null or empty.
     * @param context  the context for evaluating property value.
     * @return the property value.
     */
    @Nullable
    Object getValue(String property, ContextMap context);

    /**
     * Gets integer property.
     *
     * @param property     the property name; can't be null or empty.
     * @param defaultValue the default value if property value is {@code null}.
     * @return the integer property value.
     */
    @Nullable
    Integer getInteger(String property, Integer defaultValue);

    /**
     * Gets long property.
     *
     * @param property     the property name; can't be null or empty.
     * @param defaultValue the default value if property value is {@code null}.
     * @return the long property value.
     */
    @Nullable
    Long getLong(String property, Long defaultValue);

    /**
     * Gets boolean property.
     *
     * @param property     the property name; can't be null or empty.
     * @param defaultValue the default value if property value is {@code null}.
     * @return the boolean property value.
     */
    @Nullable
    Boolean getBoolean(String property, Boolean defaultValue);

    /**
     * Gets float property.
     *
     * @param property     the property name; can't be null or empty.
     * @param defaultValue the default value if property value is {@code null}.
     * @return the float property value.
     */
    @Nullable
    Float getFloat(String property, Float defaultValue);

    /**
     * Gets double property.
     *
     * @param property     the property name; can't be null or empty.
     * @param defaultValue the default value if property value is {@code null}.
     * @return the double property value.
     */
    @Nullable
    Double getDouble(String property, Double defaultValue);

    /**
     * Gets string property.
     *
     * @param property     the property name; can't be null or empty.
     * @param defaultValue the default value if property value is {@code null}.
     * @return the string property value.
     */
    @Nullable
    String getString(String property, String defaultValue);

    /**
     * Gets strings list property.
     *
     * @param property     the property name; can't be null or empty.
     * @param defaultValue the default value if property value is {@code null}.
     * @return the strings list property value.
     */
    @Nullable
    List<String> getStringsList(String property, List<String> defaultValue);

    /**
     * Gets strings set property.
     *
     * @param property     the property name; can't be null or empty.
     * @param defaultValue the default value if property value is {@code null}.
     * @return the strings set property value.
     */
    @Nullable
    Set<String> getStringsSet(String property, Set<String> defaultValue);

    /**
     * Gets dictionary property.
     *
     * @param property     the property name; can't be null or empty.
     * @param defaultValue the default value if property value is {@code null}.
     * @return the dictionary property value.
     */
    @Nullable
    Map<String, String> getDictionary(String property, Map<String, String> defaultValue);

    /**
     * Gets property value.
     *
     * @param property     the property name; can't be null or empty.
     * @param defaultValue the default value if property value is {@code null}.
     * @return the property value.
     */
    @Nullable
    Object getValue(String property, Map<String, String> defaultValue);

}
