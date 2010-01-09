/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config;

import com.prutsoft.config.element.ElementUtil;
import com.prutsoft.config.element.metadata.Metadata;
import com.prutsoft.core.ToStringBuilder;
import com.prutsoft.core.asserts.ArgumentAssert;
import com.prutsoft.core.code.Warnings;
import com.prutsoft.core.utils.ObjectUtils;

import java.util.*;

/**
 * The configuration implementation.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2009-12-20
 */
public class ConfigurationImpl implements Configuration {

    private String name;
    private Version version;
    private Metadata metadata;

    private Map<String, NamedElement> elements = new HashMap<String, NamedElement>();

    public ConfigurationImpl(String name) {
        this.name = name;
    }

    public ConfigurationImpl(String name, Version version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public NamedElement getElement(String name) {
        return elements.get(name);
    }

    public Collection<NamedElement> getElements() {
        return Collections.unmodifiableCollection(elements.values());
    }

    public boolean addElement(NamedElement element) {
        ArgumentAssert.isNotNull(element, "Element can't be null.");
        if (hasElement(element.getName())) return false;
        elements.put(element.getName(), element);
        return true;
    }

    public boolean removeElement(String name) {
        return (elements.remove(name) != null);
    }

    public boolean hasElement(String name) {
        return elements.containsKey(name);
    }

    public <T> T getPojo(Class<T> clazz, String property) {
        return null;
    }

    public Integer getInteger(String... property) {
        return (Integer) ElementUtil.getElementValue(this, property);
    }

    public Long getLong(String... property) {
        return (Long) ElementUtil.getElementValue(this, property);
    }

    public Boolean getBoolean(String... property) {
        return (Boolean) ElementUtil.getElementValue(this, property);
    }

    public Float getFloat(String... property) {
        return (Float) ElementUtil.getElementValue(this, property);
    }

    public Double getDouble(String... property) {
        return (Double) ElementUtil.getElementValue(this, property);
    }

    public String getString(String... property) {
        Object result = ElementUtil.getElementValue(this, property);
        return result != null ? String.valueOf(result) : null;
    }

    @SuppressWarnings(Warnings.Unchecked)
    public List<String> getStringsList(String... property) {
        return (List<String>) ElementUtil.getElementValue(this, property);
    }

    @SuppressWarnings(Warnings.Unchecked)
    public Set<String> getStringsSet(String... property) {
        return (Set<String>) ElementUtil.getElementValue(this, property);
    }

    @SuppressWarnings(Warnings.Unchecked)
    public Map<String, String> getDictionary(String... property) {
        return (Map<String, String>) ElementUtil.getElementValue(this, property);
    }

    public Object getValue(String... property) {
        return ElementUtil.getElementValue(this, property);
    }

    public Integer getInteger(String property, ContextMap context) {
        return (Integer) ElementUtil.getElementValue(context, this, property);
    }

    public Long getLong(String property, ContextMap context) {
        return (Long) ElementUtil.getElementValue(context, this, property);
    }

    public Boolean getBoolean(String property, ContextMap context) {
        return (Boolean) ElementUtil.getElementValue(context, this, property);
    }

    public Float getFloat(String property, ContextMap context) {
        return (Float) ElementUtil.getElementValue(context, this, property);
    }

    public Double getDouble(String property, ContextMap context) {
        return (Double) ElementUtil.getElementValue(context, this, property);
    }

    public String getString(String property, ContextMap context) {
        Object result = ElementUtil.getElementValue(context, this, property);
        return result != null ? String.valueOf(result) : null;
    }

    @SuppressWarnings(Warnings.Unchecked)
    public List<String> getStringsList(String property, ContextMap context) {
        return (List<String>) ElementUtil.getElementValue(context, this, property);
    }

    @SuppressWarnings(Warnings.Unchecked)
    public Set<String> getStringsSet(String property, ContextMap context) {
        return (Set<String>) ElementUtil.getElementValue(context, this, property);
    }

    @SuppressWarnings(Warnings.Unchecked)
    public Map<String, String> getDictionary(String property, ContextMap context) {
        return (Map<String, String>) ElementUtil.getElementValue(context, this, property);
    }

    public Object getValue(String property, ContextMap context) {
        return ElementUtil.getElementValue(context, this, property);
    }

    public Integer getInteger(String property, Integer defaultValue) {
        final Integer value = getInteger(property);
        return value != null ? value : defaultValue;
    }

    public Long getLong(String property, Long defaultValue) {
        final Long value = getLong(property);
        return value != null ? value : defaultValue;
    }

    public Boolean getBoolean(String property, Boolean defaultValue) {
        final Boolean value = getBoolean(property);
        return value != null ? value : defaultValue;
    }

    public Float getFloat(String property, Float defaultValue) {
        final Float value = getFloat(property);
        return value != null ? value : defaultValue;
    }

    public Double getDouble(String property, Double defaultValue) {
        final Double value = getDouble(property);
        return value != null ? value : defaultValue;
    }

    public String getString(String property, String defaultValue) {
        Object result = getString(property);
        return result != null ? String.valueOf(result) : defaultValue;
    }

    public List<String> getStringsList(String property, List<String> defaultValue) {
        final List<String> value = getStringsList(property);
        return value != null ? value : defaultValue;
    }

    public Set<String> getStringsSet(String property, Set<String> defaultValue) {
        final Set<String> value = getStringsSet(property);
        return value != null ? value : defaultValue;
    }

    public Map<String, String> getDictionary(String property, Map<String, String> defaultValue) {
        final Map<String, String> value = getDictionary(property);
        return value != null ? value : defaultValue;
    }

    public Object getValue(String property, Map<String, String> defaultValue) {
        final Object value = getValue(property);
        return value != null ? value : defaultValue;
    }

    // ------------------------------------------------

    public int hashCode() {
        return ObjectUtils.hashCode(name, version);
    }

    public String toString() {
        return new ToStringBuilder(this.getClass(), 50)
                .field("Name", name)
                .field("Version", version)
                .toString();
    }
}
