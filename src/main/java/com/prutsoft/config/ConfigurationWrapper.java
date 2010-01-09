/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config;

import com.prutsoft.config.element.metadata.Metadata;
import com.prutsoft.core.asserts.ArgumentAssert;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is configuration wrapper. Used to replace old configuration with new one
 * while reload.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 */
public class ConfigurationWrapper implements Configuration, Serializable {

    private Configuration configuration;

    public ConfigurationWrapper(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setConfiguration(Configuration newConfiguration) {
        ArgumentAssert.isNotNull(newConfiguration, "Configuration can't be null.");

        // TODO -- locking
        this.configuration = newConfiguration;
    }

    public String getName() {
        return configuration.getName();
    }

    public Version getVersion() {
        return configuration.getVersion();
    }

    public Metadata getMetadata() {
        return configuration.getMetadata();
    }

    public <T> T getPojo(Class<T> clazz, String property) {
        return configuration.getPojo(clazz, property);
    }

    public Integer getInteger(String... property) {
        return configuration.getInteger(property);
    }

    public Long getLong(String... property) {
        return configuration.getLong(property);
    }

    public Boolean getBoolean(String... property) {
        return configuration.getBoolean(property);
    }

    public Float getFloat(String... property) {
        return configuration.getFloat(property);
    }

    public Double getDouble(String... property) {
        return configuration.getDouble(property);
    }

    public String getString(String... property) {
        return configuration.getString(property);
    }

    public List<String> getStringsList(String... property) {
        return configuration.getStringsList(property);
    }

    public Set<String> getStringsSet(String... property) {
        return configuration.getStringsSet(property);
    }

    public Map<String, String> getDictionary(String... property) {
        return configuration.getDictionary(property);
    }

    public Object getValue(String... property) {
        return configuration.getValue(property);
    }

    public Integer getInteger(String property, ContextMap context) {
        return configuration.getInteger(property, context);
    }

    public Long getLong(String property, ContextMap context) {
        return configuration.getLong(property, context);
    }

    public Boolean getBoolean(String property, ContextMap context) {
        return configuration.getBoolean(property, context);
    }

    public Float getFloat(String property, ContextMap context) {
        return configuration.getFloat(property,  context);
    }

    public Double getDouble(String property, ContextMap context) {
        return configuration.getDouble(property,  context);
    }

    public String getString(String property, ContextMap context) {
        return configuration.getString(property, context);
    }

    public List<String> getStringsList(String property, ContextMap context) {
        return configuration.getStringsList(property, context);
    }

    public Set<String> getStringsSet(String property, ContextMap context) {
        return configuration.getStringsSet(property, context);
    }

    public Map<String, String> getDictionary(String property, ContextMap context) {
        return configuration.getDictionary(property, context);
    }

    public Object getValue(String property, ContextMap context) {
        return configuration.getValue(property, context);
    }

    public Integer getInteger(String property, Integer defaultValue) {
        return configuration.getInteger(property, defaultValue);
    }

    public Long getLong(String property, Long defaultValue) {
        return configuration.getLong(property, defaultValue);
    }

    public Boolean getBoolean(String property, Boolean defaultValue) {
        return configuration.getBoolean(property, defaultValue);
    }

    public Float getFloat(String property, Float defaultValue) {
        return configuration.getFloat(property, defaultValue);
    }

    public Double getDouble(String property, Double defaultValue) {
        return configuration.getDouble(property, defaultValue);
    }

    public String getString(String property, String defaultValue) {
        return configuration.getString(property, defaultValue);
    }

    public List<String> getStringsList(String property, List<String> defaultValue) {
        return configuration.getStringsList(property, defaultValue);
    }

    public Set<String> getStringsSet(String property, Set<String> defaultValue) {
        return configuration.getStringsSet(property, defaultValue);
    }

    public Map<String, String> getDictionary(String property, Map<String, String> defaultValue) {
        return configuration.getDictionary(property, defaultValue);
    }

    public Object getValue(String property, Map<String, String> defaultValue) {
        return configuration.getValue(property, defaultValue);
    }

    public NamedElement getElement(String name) {
        return configuration.getElement(name);
    }

    public Collection<NamedElement> getElements() {
        return configuration.getElements();
    }

    public boolean addElement(NamedElement element) {
        return configuration.addElement(element);
    }

    public boolean removeElement(String name) {
        return configuration.removeElement(name);
    }

    public boolean hasElement(String name) {
        return configuration.hasElement(name);
    }

    // ------------------------------------------------

    public int hashCode() {
        return configuration.hashCode();
    }

    public String toString() {
        return configuration.toString(); 
    }
}
