/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.service;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.ConfigurationWrapper;
import com.prutsoft.config.Version;
import com.prutsoft.config.annotation.ConfigurationBindException;
import com.prutsoft.config.exception.ConfigurationException;
import com.prutsoft.config.resource.ResourceRegistry;
import com.prutsoft.core.asserts.ArgumentAssert;
import com.prutsoft.core.code.Warnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;

/**
 * The configuration service implementation.
 *
 * @author Ruslan Khmelyuk
 * @see com.prutsoft.config.service.ConfigurationService
 * @since 1.0.0, 2010-01-07
 */
public class ConfigurationServiceImpl implements ConfigurationService {

    private static final Logger log = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

    private ResourceRegistry resourceRegistry = ResourceRegistry.create();
    private ConfigurationHolder configurationsHolder = new ConfigurationHolder();
    private ConfigurationLoader configurationLoader = new ConfigurationLoaderImpl(resourceRegistry);
    private ReloadService reloadService = new ReloadServiceImpl(configurationLoader, configurationsHolder);

    public ResourceRegistry getResourceRegistry() {
        return resourceRegistry;
    }

    public void setResourceRegistry(ResourceRegistry resourceRegistry) {
        this.resourceRegistry = resourceRegistry;
    }

    public ReloadService getReloadService() {
        return reloadService;
    }

    public void setReloadService(ReloadService reloadService) {
        this.reloadService = reloadService;
    }

    public void destroy() {
        reloadService.shutdown();
        configurationsHolder.removeConfigurations();

        log.info("Destroyed configuration service");
    }

    public void load(String configuration) throws ConfigurationException {
        ArgumentAssert.isNotEmpty(configuration, "Configuration can't be null or empty.");
        configurationLoader.load(configuration, configurationsHolder);

        log.info("Loaded configuration [{}]", configuration);
    }

    public boolean unload(Configuration configuration) {
        ArgumentAssert.isNotNull(configuration, "Configuration can't be null");
        boolean unloaded = configurationsHolder.removeConfiguration(configuration);
        if (unloaded) {
            log.info("Unloaded configuration [{}]", configuration);
        }
        return unloaded;
    }

    public Configuration configuration(String name) {
        ArgumentAssert.isNotEmpty(name, "Name can't be null or empty.");
        return configurationsHolder.getConfiguration(name);
    }

    public Configuration configuration(String name, Version version) {
        ArgumentAssert.isNotEmpty(name, "Name can't be null or empty.");
        return configurationsHolder.getConfiguration(name, version);
    }

    public Configuration configuration(String name, String version) {
        ArgumentAssert.isNotEmpty(name, "Name can't be null or empty.");
        return configurationsHolder.getConfiguration(name, version);
    }

    public <T> T staticConfiguration(Class<T> clazz, String name) {
        return staticConfiguration(clazz, name, (Version) null);
    }

    public <T> T staticConfiguration(Class<T> clazz, String name, String version) {
        return staticConfiguration(clazz, name,
                version != null ? Version.parse(version) : null);
    }

    @SuppressWarnings(Warnings.Unchecked)
    public <T> T staticConfiguration(Class<T> clazz, String name, Version version) {
        ArgumentAssert.isNotNull(clazz, "Class can't be null.");
        ArgumentAssert.isNotEmpty(name, "Name can't be null or empty.");

        if (!clazz.isAnnotationPresent(com.prutsoft.config.annotation.Configuration.class)) {
            throw new ConfigurationBindException("Class " + clazz.getName()
                    + " is not market as configuration with annotation "
                    + com.prutsoft.config.annotation.Configuration.class.getName());
        }

        Configuration configuration = configuration(name, version);

        if (configuration == null) {
            throw new ConfigurationBindException("Can't find configuration with name "
                    + name + " and version " + version);
        }
        else if (configuration instanceof ConfigurationWrapper) {
            configuration = ((ConfigurationWrapper) configuration).getConfiguration();
        }

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[] {clazz}, new ConfigurationInvocationHandler(configuration));
    }

    public <T> T dynamicConfiguration(Class<T> clazz, String name) {
        return dynamicConfiguration(clazz, name, (Version) null);
    }

    public <T> T dynamicConfiguration(Class<T> clazz, String name, String version) {
        return dynamicConfiguration(clazz, name,
                version != null ? Version.parse(version) : null);
    }

    @SuppressWarnings(Warnings.Unchecked)
    public <T> T dynamicConfiguration(Class<T> clazz, String name, Version version) {
        ArgumentAssert.isNotNull(clazz, "Class can't be null.");
        ArgumentAssert.isNotEmpty(name, "Name can't be null or empty.");

        if (!clazz.isAnnotationPresent(com.prutsoft.config.annotation.Configuration.class)) {
            throw new ConfigurationBindException("Class " + clazz.getName()
                    + " is not market as configuration with annotation "
                    + com.prutsoft.config.annotation.Configuration.class.getName());
        }

        final Configuration configuration = configuration(name, version);

        if (configuration == null) {
            throw new ConfigurationBindException("Can't find configuration with name "
                    + name + " and version " + version);
        }

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[] {clazz}, new ConfigurationInvocationHandler(configuration));
    }
}
