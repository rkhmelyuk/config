/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.service;

import com.prutsoft.config.ConcurrentConfiguration;
import com.prutsoft.config.Configuration;
import com.prutsoft.config.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The configurations re-loader service implementation.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 */
public class ReloadServiceImpl implements ReloadService {

    private static final Logger log = LoggerFactory.getLogger(ReloadServiceImpl.class);

    private static final long ONE_SECOND = 1000L;

    private ConfigurationLoader configurationLoader;

    private Timer configurationReloadTimer = new Timer("Configuration Reload Timer", true);
    private Map<Configuration, TimerTask> reloadTasks = new HashMap<Configuration, TimerTask>();
    private Map<Configuration, Resource> suspendedTasks = new HashMap<Configuration, Resource>();

    public ReloadServiceImpl(ConfigurationLoader configurationLoader, ConfigurationHolder configurationHolder) {
        this.configurationLoader = configurationLoader;

        configurationHolder.addNewConfigurationListener(new ConfigurationHolderChangeListener() {
            public void process(Configuration configuration, Resource resource) {
                if (configuration.getReloadPolicy() != null && configuration.getReloadPolicy().isOnChange()) {
                    startFor(configuration, resource);
                }
            }
        });
        configurationHolder.addRemoveConfigurationListener(new ConfigurationHolderChangeListener() {
            public void process(Configuration configuration, Resource resource) {
                removeFor(configuration);
            }
        });
    }

    private void startFor(Configuration configuration, Resource resource) {
        startTimerTask(configuration, resource);

        log.debug("Started reload for configuration [{}]", configuration.getName());
    }

    private void removeFor(Configuration configuration) {
        TimerTask task = reloadTasks.remove(configuration);
        if (task != null) {
            task.cancel();
            configurationReloadTimer.purge();
            log.debug("Removed reload for configuration [{}]", configuration.getName());
        }
    }

    public void suspend() {
        suspendedTasks.clear();
        for (Configuration each : reloadTasks.keySet()) {
            suspend(each);
        }
    }

    public void resume() {
        for (Configuration each : suspendedTasks.keySet()) {
            resume(each);
        }
        suspendedTasks.clear();
    }

    public void suspend(Configuration configuration) {
        ConfigurationReloadTimerTask task = (ConfigurationReloadTimerTask) reloadTasks.get(configuration);
        if (task != null) {
            suspendedTasks.put(task.getConfiguration(), task.getResource());
            task.cancel();

            log.debug("Suspended reload of configuration [{}]", configuration.getName());
        }
    }

    public void resume(Configuration configuration) {
        Resource resource = suspendedTasks.remove(configuration);
        if (resource != null) {
            suspendedTasks.remove(configuration);
            startTimerTask(configuration, resource);

            log.debug("Resumed reload of configuration [{}]", configuration.getName());
        }
    }

    public void shutdown() {
        configurationReloadTimer.cancel();
        log.debug("Shutdown Reload Service...");
    }

    private void startTimerTask(Configuration configuration, Resource resource) {
        final TimerTask task = new ConfigurationReloadTimerTask(configuration, resource, configurationLoader);
        reloadTasks.put(configuration, task);

        final long reloadMillis = configuration.getReloadPolicy().getCheckEvery() * ONE_SECOND;
        configurationReloadTimer.schedule(task, reloadMillis, reloadMillis);
    }


    /**
     * Responsible for reloading configuration if related resource was changed.
     */
    private static class ConfigurationReloadTimerTask extends TimerTask {

        private final Configuration configuration;
        private final Resource resource;
        private ConfigurationLoader configurationLoader;

        private ConfigurationReloadTimerTask(Configuration configuration, Resource resource,
                                             ConfigurationLoader configurationLoader) {
            this.resource = resource;
            this.configuration = configuration;
            this.configurationLoader = configurationLoader;
        }

        public Configuration getConfiguration() {
            return configuration;
        }

        public Resource getResource() {
            return resource;
        }

        public void run() {
            if (!resource.isChanged()) return;
            try {
                configurationLoader.reload((ConcurrentConfiguration) configuration, resource);
                log.debug("Reloaded configuration [{}]", configuration);
            }
            catch (Exception e) {
                log.error("Error to reload configuration [" + configuration + "]", e);
            }
        }
    }
}
