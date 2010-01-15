/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config;

import com.prutsoft.config.element.metadata.Metadata;
import com.prutsoft.config.element.reload.ReloadPolicy;
import com.prutsoft.core.asserts.ArgumentAssert;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This is configuration wrapper. Used to replace old configuration with new one
 * while reload. Use concurrent locking to be safe on substitute configuration.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 *
 * TODO - Convert to the proxy implementation
 */
public class ReloadableConfiguration implements Configuration, ConfigurationWrapper, Serializable {

    /**
     * The read-write lock used to lock read of configuration while
     * it is locked on reloading.
     */
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Configuration configuration;

    public ReloadableConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration newConfiguration) {
        ArgumentAssert.isNotNull(newConfiguration, "Configuration can't be null.");

        try {
            readWriteLock.writeLock().lock();
            this.configuration = newConfiguration;
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public String getName() {
        readWriteLock.readLock().lock();
        try {
            return configuration.getName();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Version getVersion() {
        readWriteLock.readLock().lock();
        try {
            return configuration.getVersion();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Metadata getMetadata() {
        readWriteLock.readLock().lock();
        try {
            return configuration.getMetadata();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public ReloadPolicy getReloadPolicy() {
        readWriteLock.readLock().lock();
        try {
            return configuration.getReloadPolicy();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Collection<Configuration> getIncludedConfigurations() {
        readWriteLock.readLock().lock();
        try {
            return configuration.getIncludedConfigurations();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public <T> T getPojo(Class<T> clazz, String property) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getPojo(clazz, property);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Integer getInteger(String... property) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getInteger(property);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Long getLong(String... property) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getLong(property);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Boolean getBoolean(String... property) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getBoolean(property);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Float getFloat(String... property) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getFloat(property);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Double getDouble(String... property) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getDouble(property);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public String getString(String... property) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getString(property);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public List<String> getStringsList(String... property) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getStringsList(property);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Set<String> getStringsSet(String... property) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getStringsSet(property);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Map<String, String> getDictionary(String... property) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getDictionary(property);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Object getValue(String... property) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getValue(property);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Integer getInteger(String property, ContextMap context) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getInteger(property, context);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Long getLong(String property, ContextMap context) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getLong(property, context);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Boolean getBoolean(String property, ContextMap context) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getBoolean(property, context);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Float getFloat(String property, ContextMap context) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getFloat(property, context);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Double getDouble(String property, ContextMap context) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getDouble(property, context);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public String getString(String property, ContextMap context) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getString(property, context);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public List<String> getStringsList(String property, ContextMap context) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getStringsList(property, context);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Set<String> getStringsSet(String property, ContextMap context) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getStringsSet(property, context);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Map<String, String> getDictionary(String property, ContextMap context) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getDictionary(property, context);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Object getValue(String property, ContextMap context) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getValue(property, context);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Integer getInteger(String property, Integer defaultValue) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getInteger(property, defaultValue);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Long getLong(String property, Long defaultValue) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getLong(property, defaultValue);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Boolean getBoolean(String property, Boolean defaultValue) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getBoolean(property, defaultValue);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Float getFloat(String property, Float defaultValue) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getFloat(property, defaultValue);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Double getDouble(String property, Double defaultValue) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getDouble(property, defaultValue);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public String getString(String property, String defaultValue) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getString(property, defaultValue);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public List<String> getStringsList(String property, List<String> defaultValue) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getStringsList(property, defaultValue);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Set<String> getStringsSet(String property, Set<String> defaultValue) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getStringsSet(property, defaultValue);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Map<String, String> getDictionary(String property, Map<String, String> defaultValue) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getDictionary(property, defaultValue);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Object getValue(String property, Map<String, String> defaultValue) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getValue(property, defaultValue);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public NamedElement getElement(String name) {
        readWriteLock.readLock().lock();
        try {
            return configuration.getElement(name);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Collection<NamedElement> getElements() {
        readWriteLock.readLock().lock();
        try {
            return configuration.getElements();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public boolean addElement(NamedElement element) {
        readWriteLock.readLock().lock();
        try {
            return configuration.addElement(element);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public boolean removeElement(String name) {
        readWriteLock.readLock().lock();
        try {
            return configuration.removeElement(name);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public boolean hasElement(String name) {
        readWriteLock.readLock().lock();
        try {
            return configuration.hasElement(name);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    // ------------------------------------------------

    public int hashCode() {
        readWriteLock.readLock().lock();
        try {
            return configuration.hashCode();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    public String toString() {
        readWriteLock.readLock().lock();
        try {
            return configuration.toString();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }
}
