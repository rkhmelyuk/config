/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.service;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.Version;
import com.prutsoft.config.resource.Resource;
import com.prutsoft.core.annotation.NotNull;
import com.prutsoft.core.asserts.ArgumentAssert;

import java.util.*;

/**
 * The configurations holder.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 */
public class ConfigurationHolder {

    private Map<String, List<Configuration>> configurations;

    private List<ConfigurationHolderChangeListener> addConfigurationListeners;
    private List<ConfigurationHolderChangeListener> removeConfigurationListeners;

    
    public ConfigurationHolder() {
        configurations = new TreeMap<String, List<Configuration>>();
        addConfigurationListeners = new ArrayList<ConfigurationHolderChangeListener>();
        removeConfigurationListeners = new ArrayList<ConfigurationHolderChangeListener>();
    }

    /**
     * Add configuration with appropriate resource to the holder.
     *
     * @param configuration the configuration to add; can't be null.
     * @param resource      the resource to add; can't be null.
     * @return {@code true} if added, otherwise returns {@code false}.
     */
    public boolean addConfiguration(@NotNull Configuration configuration, @NotNull Resource resource) {
        ArgumentAssert.isNotNull(configuration, "Configuration can't be null.");
        ArgumentAssert.isNotNull(resource, "Resource can't be null.");

        List<Configuration> list = configurations.get(configuration.getName());
        if (list == null) {
            list = new ArrayList<Configuration>();
            configurations.put(configuration.getName(), list);
        }
        boolean result = list.add(configuration);
        if (result) {
            fireNewConfiguration(configuration, resource);
        }
        return result;
    }

    /**
     * Removes configuration.
     *
     * @param configuration the configuration to remove.
     * @return {@code true} if removed, otherwise {@code false}.
     */
    public boolean removeConfiguration(Configuration configuration) {
        ArgumentAssert.isNotNull(configuration, "Configuration can't be null.");
        List<Configuration> list = configurations.get(configuration.getName());
        if (list != null) {
            fireRemoveConfiguration(configuration, null);
            list.remove(configuration);
            if (list.isEmpty()) {
                configurations.remove(configuration.getName());
            }
            return true;
        }
        return false;
    }

    /**
     * Removes all configurations.
     */
    public void removeConfigurations() {
        configurations.clear();
    }

    /**
     * Gets the collection of all configurations.
     *
     * @return the collection of all configurations.
     */
    public Collection<Configuration> getConfigurations() {
        Collection<Configuration> result = new ArrayList<Configuration>();
        for (List<Configuration> each : configurations.values()) {
            result.addAll(each);
        }
        return result;
    }

    /**
     * Gets the configuration by it's name.
     *
     * @param name the configuration name.
     * @return the found configuration or {@code null} otherwise.
     */
    public Configuration getConfiguration(String name) {
        List<Configuration> list = configurations.get(name);
        if (list != null) {
            if (!list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

    /**
     * Gets the configuration by it's name and version.
     *
     * @param name    the configuration name.
     * @param version the configuration version.
     * @return the found configuration or {@code null} otherwise.
     */
    public Configuration getConfiguration(String name, String version) {
        List<Configuration> list = configurations.get(name);
        if (list != null) {
            if (!list.isEmpty()) {
                Version ver = version != null ? Version.parse(version) : null;
                for (Configuration each : list) {
                    if (ver == null && each.getVersion() == null) {
                        return each;
                    }
                    if (each.getVersion().equals(ver)) {
                        return each;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Gets the configuration by it's name and version.
     *
     * @param name    the configuration name.
     * @param version the configuration version.
     * @return the found configuration or {@code null} otherwise.
     */
    public Configuration getConfiguration(String name, Version version) {
        List<Configuration> list = configurations.get(name);
        if (list != null) {
            if (!list.isEmpty()) {
                for (Configuration each : list) {
                    if (version == null && each.getVersion() == null) {
                        return each;
                    }
                    if (each.getVersion().equals(version)) {
                        return each;
                    }
                }
            }
        }
        return null;
    }

    public void addNewConfigurationListener(ConfigurationHolderChangeListener listener) {
        ArgumentAssert.isNotNull(listener, "Listener can't be null.");
        addConfigurationListeners.add(listener);
    }
    public void addRemoveConfigurationListener(ConfigurationHolderChangeListener listener) {
        ArgumentAssert.isNotNull(listener, "Listener can't be null.");
        removeConfigurationListeners.add(listener);
    }

    private void fireNewConfiguration(Configuration configuration, Resource resource) {
        for (ConfigurationHolderChangeListener each : addConfigurationListeners) {
            each.process(configuration, resource);
        }
    }

    private void fireRemoveConfiguration(Configuration configuration, Resource resource) {
        for (ConfigurationHolderChangeListener each : removeConfigurationListeners) {
            each.process(configuration, resource);
        }
    }

}
