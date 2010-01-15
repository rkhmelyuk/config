/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.test;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.ContextMap;
import com.prutsoft.config.Version;
import com.prutsoft.config.service.ConfigurationService;
import com.prutsoft.config.service.ConfigurationServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * The test cases for the configuration service.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public class ConfigurationServiceTestCase {

    final String configuration = "string:<configuration name='test' version='1.0.23'>  " +
            "<metadata>" +
            "<property name='x' value='y'/>" +
            "<property name='x2' value='y2'/>" +
            "</metadata>" +
            "<reload>" +
            "<onchange check-every='5s'/>" +
            "</reload>" +
            "<expression name='t' value='\"wdt\"'/>" +
            "<expression name='tL' value='23+27'/>" +
            "<property name='t1'><value type='integer'>2292</value></property>" +
            "<set name='x1'><set name='x1'><property name='xxx' value='23' type='integer'/></set><property name='x2' value='xx'/></set>" +
            "<switch name='ttk'>" +
            "<on condition='x == 1'><value>x1</value></on>" +
            "<on condition='x == 2'><value>x2</value></on>" +
            "<else><value>xxx</value></else>" +
            "</switch>" +
            "" +
            "<pojo name='student' class='com.prutsoft.config.test.Student'>" +
            "<property name='firstName' value='Ruslan'/>" +
            "<property name='lastName' value='Khmelyuk'/>" +
            "</pojo>" +
            " </configuration>";

    private ConfigurationService getConfigurationService() throws Exception {
        ConfigurationService service = new ConfigurationServiceImpl();
        service.load(configuration);
        return service;
    }

    @Test
    public void testConfigurationInfo() throws Exception {
        Configuration config = getConfigurationService().configuration("test", "1.0.23");

        Assert.assertEquals("test", config.getName());
        Assert.assertEquals(Version.parse("1.0.23"), config.getVersion());
        Assert.assertFalse(Version.parse("1.0.0").equals(config.getVersion()));
    }

    @Test
    public void testProperty() throws Exception {
        Configuration config = getConfigurationService().configuration("test", "1.0.23");

        Assert.assertEquals(2292, (long) config.getInteger("t1"));
    }

    @Test
    public void testSet() throws Exception {
        Configuration config = getConfigurationService().configuration("test", "1.0.23");

        Assert.assertEquals(23, (long) config.getInteger("x1:x1:xxx"));
        Assert.assertEquals(23, (long) config.getInteger("x1", "x1", "xxx"));
    }

    @Test
    public void testExpression() throws Exception {
        Configuration config = getConfigurationService().configuration("test", "1.0.23");

        Assert.assertEquals("wdt", config.getString("t"));
        Assert.assertEquals(50, (long) config.getLong("tL"));
    }

    @Test
    public void testSwitch() throws Exception {
        Configuration config = getConfigurationService().configuration("test", "1.0.23");

        Assert.assertEquals("x1", config.getString("ttk", new ContextMap("x", 1)));
        Assert.assertEquals("x2", config.getString("ttk", new ContextMap("x", 2)));
        Assert.assertEquals("xxx", config.getString("ttk", new ContextMap("x", 22)));
        Assert.assertFalse("xx2".equals(config.getString("ttk", new ContextMap("x", 22))));
    }

    @Test
    public void testPojo() throws Exception {
        Configuration config = getConfigurationService().configuration("test", "1.0.23");

        Student s = (Student) config.getValue("student");

        Assert.assertEquals("Ruslan", s.getFirstName());
        Assert.assertEquals("Khmelyuk", s.getLastName());
    }

}
