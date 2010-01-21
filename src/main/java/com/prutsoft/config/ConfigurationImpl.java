/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config;

import com.prutsoft.config.element.ElementUtil;
import com.prutsoft.config.element.metadata.Metadata;
import com.prutsoft.config.element.reload.ReloadPolicy;
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
    private ReloadPolicy reloadPolicy;

    private List<Configuration> configurations = new ArrayList<Configuration>();
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

    public ReloadPolicy getReloadPolicy() {
        return reloadPolicy;
    }

    public void setReloadPolicy(ReloadPolicy reloadPolicy) {
        this.reloadPolicy = reloadPolicy;
    }

    public Collection<Configuration> getIncludedConfigurations() {
        return configurations;
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

    @SuppressWarnings(Warnings.Unchecked)
    public <T> T getPojo(Class<T> clazz, String path) {
        return (T) getValue(path);
    }

    public Integer getInteger(String... path) {
        return (Integer) ElementUtil.getElementValue(null, this, path);
    }

    public Long getLong(String... path) {
        return (Long) ElementUtil.getElementValue(null, this, path);
    }

    public Boolean getBoolean(String... path) {
        return (Boolean) ElementUtil.getElementValue(null, this, path);
    }

    public Float getFloat(String... path) {
        return (Float) ElementUtil.getElementValue(null, this, path);
    }

    public Double getDouble(String... path) {
        return (Double) ElementUtil.getElementValue(null, this, path);
    }

    public String getString(String... path) {
        Object result = ElementUtil.getElementValue(null, this, path);
        return result != null ? String.valueOf(result) : null;
    }

    @SuppressWarnings(Warnings.Unchecked)
    public List<String> getStringsList(String... path) {
        return (List<String>) ElementUtil.getElementValue(null, this, path);
    }

    @SuppressWarnings(Warnings.Unchecked)
    public Set<String> getStringsSet(String... path) {
        return (Set<String>) ElementUtil.getElementValue(null, this, path);
    }

    @SuppressWarnings(Warnings.Unchecked)
    public Map<String, String> getDictionary(String... path) {
        return (Map<String, String>) ElementUtil.getElementValue(null, this, path);
    }

    public Object getValue(String... path) {
        return ElementUtil.getElementValue(null, this, path);
    }

    public Integer getInteger(String property, ContextMap path) {
        return (Integer) ElementUtil.getElementValue(path, this, property);
    }

    public Long getLong(String path, ContextMap context) {
        return (Long) ElementUtil.getElementValue(context, this, path);
    }

    public Boolean getBoolean(String path, ContextMap context) {
        return (Boolean) ElementUtil.getElementValue(context, this, path);
    }

    public Float getFloat(String path, ContextMap context) {
        return (Float) ElementUtil.getElementValue(context, this, path);
    }

    public Double getDouble(String path, ContextMap context) {
        return (Double) ElementUtil.getElementValue(context, this, path);
    }

    public String getString(String path, ContextMap context) {
        Object result = ElementUtil.getElementValue(context, this, path);
        return result != null ? String.valueOf(result) : null;
    }

    @SuppressWarnings(Warnings.Unchecked)
    public List<String> getStringsList(String path, ContextMap context) {
        return (List<String>) ElementUtil.getElementValue(context, this, path);
    }

    @SuppressWarnings(Warnings.Unchecked)
    public Set<String> getStringsSet(String path, ContextMap context) {
        return (Set<String>) ElementUtil.getElementValue(context, this, path);
    }

    @SuppressWarnings(Warnings.Unchecked)
    public Map<String, String> getDictionary(String path, ContextMap context) {
        return (Map<String, String>) ElementUtil.getElementValue(context, this, path);
    }

    public Object getValue(String path, ContextMap context) {
        return ElementUtil.getElementValue(context, this, path);
    }

    public Integer getInteger(String path, Integer defaultValue) {
        final Integer value = getInteger(path);
        return value != null ? value : defaultValue;
    }

    public Long getLong(String path, Long defaultValue) {
        final Long value = getLong(path);
        return value != null ? value : defaultValue;
    }

    public Boolean getBoolean(String path, Boolean defaultValue) {
        final Boolean value = getBoolean(path);
        return value != null ? value : defaultValue;
    }

    public Float getFloat(String path, Float defaultValue) {
        final Float value = getFloat(path);
        return value != null ? value : defaultValue;
    }

    public Double getDouble(String path, Double defaultValue) {
        final Double value = getDouble(path);
        return value != null ? value : defaultValue;
    }

    public String getString(String path, String defaultValue) {
        Object result = getString(path);
        return result != null ? String.valueOf(result) : defaultValue;
    }

    public List<String> getStringsList(String path, List<String> defaultValue) {
        final List<String> value = getStringsList(path);
        return value != null ? value : defaultValue;
    }

    public Set<String> getStringsSet(String path, Set<String> defaultValue) {
        final Set<String> value = getStringsSet(path);
        return value != null ? value : defaultValue;
    }

    public Map<String, String> getDictionary(String path, Map<String, String> defaultValue) {
        final Map<String, String> value = getDictionary(path);
        return value != null ? value : defaultValue;
    }

    public Object getValue(String path, Map<String, String> defaultValue) {
        final Object value = getValue(path);
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
