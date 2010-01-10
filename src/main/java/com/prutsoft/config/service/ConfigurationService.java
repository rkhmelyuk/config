/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.service;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.Version;
import com.prutsoft.config.exception.ConfigurationException;
import com.prutsoft.config.resource.ResourceRegistry;

/**
 * The interface of the configuration service.
 * Use configuration manager to access configuration,
 * load and destroy configuration, access to the services.
 * <p>
 * Methods <code>staticConfiguration()</code>and <code>dynamicConfiguration()</code>
 * helps to used annotated interface to get configuration data.
 * There is difference between static and dynamic configuration: static configuration
 * will return data that was in configuration when it was accessed; dynamic configuration
 * always contains actual configuration.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 */
public interface ConfigurationService {

    /**
     * Gets the resource registry.
     *
     * @return the resource registry.
     */
    ResourceRegistry getResourceRegistry();

    /**
     * Gets the configuration reload service.
     *
     * @return the reload service.
     */
    ReloadService getReloadService();

    /**
     * Unloads all configurations and shutdown related services.
     */
    void destroy();

    /**
     * Loads specified configuration.
     *
     * @param configuration the configuration to load; can't be null.
     * @throws ConfigurationException error to load configuration.
     */
    void load(String configuration) throws ConfigurationException;

    /**
     * Unloads specified configuration.
     *
     * @param configuration the configuration to destroy; can't be null.
     * @return {@code true} if configuration was unloaded.
     */
    boolean unload(Configuration configuration);

    /**
     * Gets configuration with specified name.
     *
     * @param name the configuration name; can't be null.
     * @return the configuration with specified name or {@code null} if not found.
     */
    Configuration configuration(String name);

    /**
     * Gets configuration with specified name and version
     *
     * @param name    the configuration name; can't be null.
     * @param version the configuration version.
     * @return the configuration with specified name and version or {@code null} if not found.
     */
    Configuration configuration(String name, Version version);

    /**
     * Gets configuration with specified name and version
     *
     * @param name    the configuration name; can't be null.
     * @param version the configuration version.
     * @return the configuration with specified name and version or {@code null} if not found.
     */
    Configuration configuration(String name, String version);

    /**
     * Returns the static implementation of interface annotated with
     * {@link com.prutsoft.config.annotation.Configuration} annotation.
     *
     * @param clazz the annotated class; can't be null.
     * @param name  the name of the configuration; can't be null.
     * @param <T>   the type of the annotated interface.
     * @return the implementation annotated interface
     *         that returns settings or null if configuration is not found.
     * @throws com.prutsoft.config.annotation.PropertyNotFoundException error to find property to bind.
     * @throws com.prutsoft.config.annotation.ConfigurationBindException error to bind configuration.
     */
    <T> T staticConfiguration(Class<T> clazz, String name);

    /**
     * Returns the static implementation of interface annotated with
     * {@link com.prutsoft.config.annotation.Configuration} annotation.
     *
     * @param clazz   the annotated class; can't be null.
     * @param name    the name of the configuration; can't be null.
     * @param version the version of the configuration.
     * @param <T>     the type of the annotated interface.
     * @return the implementation annotated interface
     *         that returns settings or null if configuration is not found.
     * @throws com.prutsoft.config.annotation.PropertyNotFoundException error to find property to bind.
     * @throws com.prutsoft.config.annotation.ConfigurationBindException error to bind configuration.
     */
    <T> T staticConfiguration(Class<T> clazz, String name, Version version);

    /**
     * Returns the static implementation of interface annotated with
     * {@link com.prutsoft.config.annotation.Configuration} annotation.
     *
     * @param clazz   the annotated class; can't be null.
     * @param name    the name of the configuration; can't be null.
     * @param version the version of the configuration.
     * @param <T>     the type of the annotated interface.
     * @return the implementation annotated interface
     *         that returns settings or null if configuration is not found.
     * @throws com.prutsoft.config.annotation.PropertyNotFoundException error to find property to bind.
     * @throws com.prutsoft.config.annotation.ConfigurationBindException error to bind configuration.
     */
    <T> T staticConfiguration(Class<T> clazz, String name, String version);

    /**
     * Returns the dynamic implementation of interface annotated with
     * {@link com.prutsoft.config.annotation.Configuration} annotation.
     *
     * @param clazz the annotated class; can't be null.
     * @param name  the name of the configuration; can't be null.
     * @param <T>   the type of the annotated interface.
     * @return the implementation annotated interface
     *         that returns settings or null if configuration is not found.
     * @throws com.prutsoft.config.annotation.PropertyNotFoundException error to find property to bind.
     * @throws com.prutsoft.config.annotation.ConfigurationBindException error to bind configuration.
     */
    <T> T dynamicConfiguration(Class<T> clazz, String name);

    /**
     * Returns the dynamic implementation of interface annotated with
     * {@link com.prutsoft.config.annotation.Configuration} annotation.
     *
     * @param clazz   the annotated class; can't be null.
     * @param name    the name of the configuration; can't be null.
     * @param version the version of the configuration.
     * @param <T>     the type of the annotated interface.
     * @return the implementation annotated interface
     *         that returns settings or null if configuration is not found.
     * @throws com.prutsoft.config.annotation.PropertyNotFoundException error to find property to bind.
     * @throws com.prutsoft.config.annotation.ConfigurationBindException error to bind configuration.
     */
    <T> T dynamicConfiguration(Class<T> clazz, String name, Version version);

    /**
     * Returns the dynamic implementation of interface annotated with
     * {@link com.prutsoft.config.annotation.Configuration} annotation.
     *
     * @param clazz   the annotated class; can't be null.
     * @param name    the name of the configuration; can't be null.
     * @param version the version of the configuration.
     * @param <T>     the type of the annotated interface.
     * @return the implementation annotated interface
     *         that returns settings or null if configuration is not found.
     * @throws com.prutsoft.config.annotation.PropertyNotFoundException error to find property to bind.
     * @throws com.prutsoft.config.annotation.ConfigurationBindException error to bind configuration.
     */
    <T> T dynamicConfiguration(Class<T> clazz, String name, String version);
}
