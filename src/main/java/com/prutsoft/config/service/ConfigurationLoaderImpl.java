/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.service;

import com.prutsoft.config.ConcurrentConfiguration;
import com.prutsoft.config.Configuration;
import com.prutsoft.config.exception.ConfigurationLoadException;
import com.prutsoft.config.parser.ConfigurationParser;
import com.prutsoft.config.parser.xml.XmlConfigurationParser;
import com.prutsoft.config.resource.Resource;
import com.prutsoft.config.resource.ResourceRegistry;
import com.prutsoft.core.asserts.ArgumentAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * The default implementation of the configuration loader.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 */
public class ConfigurationLoaderImpl implements ConfigurationLoader {

    private static final Logger log = LoggerFactory.getLogger(ConfigurationLoaderImpl.class);

    private final ResourceRegistry resourceRegistry;
    private final ConfigurationParser parser = new XmlConfigurationParser();

    public ConfigurationLoaderImpl(ResourceRegistry resourceRegistry) {
        this.resourceRegistry = resourceRegistry;
    }

    public void load(String configurationName, ConfigurationHolder holder) throws ConfigurationLoadException {
        ArgumentAssert.isNotEmpty(configurationName, "Configuration is required.");
        ArgumentAssert.isNotNull(holder, "Configuration Holder can't be null.");

        // TODO - add support for multiple configuration parsers
        try {
            Resource resource = resourceRegistry.create(configurationName);
            if (resource == null) {
                throw new ConfigurationLoadException("Error to load configuration ["
                        + configurationName + "] as such resource is not supported.");
            }

            Configuration configuration = parser.parse(resource);
            holder.addConfiguration(new ConcurrentConfiguration(configuration), resource);
        }
        catch (ConfigurationLoadException e) {
            throw e;
        }
        catch (Exception e) {
            String msg = "Can't load configuration [" + configurationName + "]";
            log.error(msg, e);
            throw new ConfigurationLoadException(msg, e);
        }

    }

    public void load(List<String> configurations, ConfigurationHolder holder) throws ConfigurationLoadException {
        ArgumentAssert.isNotNull(configurations, "Configurations list can't be null.");
        ArgumentAssert.isNotNull(holder, "Configuration Holder can't be null.");

        for (String each : configurations) {
            load(each, holder);
        }
    }

    public void reload(ConcurrentConfiguration configuration, Resource resource) throws ConfigurationLoadException {
        ArgumentAssert.isNotNull(configuration, "Configuration is required.");
        ArgumentAssert.isNotNull(resource, "Resource can't be null.");

        if (!resource.exists()) return;

        try {
            resource.reload();
            final Configuration newConfiguration = parser.parse(resource);
            configuration.setConfiguration(newConfiguration);
        }
        catch (Exception e) {
            String msg = "Can't load configuration [" + configuration + "]";
            log.error(msg, e);
            throw new ConfigurationLoadException(msg, e);
        }

    }

}
