/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.annotation;

import com.prutsoft.config.service.ConfigurationService;
import com.prutsoft.config.service.ConfigurationServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * The test cases for the configuration service.
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-06
 */
public class AnnotationTestCase {

    final String configuration = "string:<configuration name='test' version='1.0.0'>" +
            "<property name='firstName' value='Ruslan'/>" +
            "<property name='surname' value='Khmelyuk'/>" +
            "<expression name='hello' value='\"Hello \" + name + exclamation'/>" +
            " </configuration>";

    private ConfigurationService getConfigurationService() throws Exception {
        ConfigurationService service = new ConfigurationServiceImpl();
        service.load(configuration);
        return service;
    }

    @Test
    public void testDynamicProperty() throws Exception {
        TestConfig config = getConfigurationService().dynamicConfiguration(TestConfig.class, "test");

        Assert.assertEquals("Ruslan", config.getFirstName());
        Assert.assertEquals("Khmelyuk", config.getLastName());
    }

    @Test
    public void testDynamicExpression() throws Exception {
        TestConfig config = getConfigurationService().dynamicConfiguration(TestConfig.class, "test", "1.0.0");

        Assert.assertEquals("Hello Ruslan!", config.sayHello("Ruslan", "!"));
    }
}
