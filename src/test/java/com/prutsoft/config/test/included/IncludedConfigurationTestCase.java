/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.test.included;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.service.ConfigurationService;
import com.prutsoft.config.service.ConfigurationServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * The included configuration test case.
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-15
 */
public class IncludedConfigurationTestCase {

    private static final String CONFIGURATION = "classpath:com/prutsoft/config/test/included/config1.xml";

    private ConfigurationService getConfigurationService() throws Exception {
        ConfigurationService service = new ConfigurationServiceImpl();
        service.load(CONFIGURATION);
        return service;
    }

    @Test
    public void testConfigurationInfo() throws Exception {
        Configuration config = getConfigurationService().configuration("config1", "1.0.0");

        Assert.assertEquals("Banerman", config.getString("JJ"));
        Assert.assertEquals("Smith", config.getString("John"));
        Assert.assertEquals("Khmelyuk", config.getString("Ruslan"));
        Assert.assertNull(config.getString("Johny"));
    }
}
