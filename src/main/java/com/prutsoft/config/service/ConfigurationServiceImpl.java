/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.service;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.Version;
import com.prutsoft.config.element.value.ValueTypeRegistry;
import com.prutsoft.config.exception.ConfigurationException;
import com.prutsoft.config.resource.ResourceRegistry;
import com.prutsoft.core.asserts.ArgumentAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The configuration service implementation.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 * 
 * @see com.prutsoft.config.service.ConfigurationService
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

    public ValueTypeRegistry getValueTypeRegistry() {
        return new ValueTypeRegistry();
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
            log.info("Unloaded configuration [{}]",  configuration);
        }
        return unloaded;
    }

    /**
     * Ges the configuration by name.
     *
     * @param name the configuration name; can't be null.
     * @return the configuration by name and version.
     */
    public Configuration getConfiguration(String name) {
        return configurationsHolder.getConfiguration(name);
    }

    /**
     * Ges the configuration by name and version.
     *
     * @param name    the configuration name; can't be null.
     * @param version the configuration version; can't be null.
     * @return the configuration by name and version.
     */
    public Configuration getConfiguration(String name, Version version) {
        return configurationsHolder.getConfiguration(name, version);
    }

    /**
     * Ges the configuration by name and version.
     *
     * @param name    the configuration name; can't be null.
     * @param version the configuration version; can't be null.
     * @return the configuration by name and version.
     */
    public Configuration getConfiguration(String name, String version) {
        return configurationsHolder.getConfiguration(name, version);
    }
}
