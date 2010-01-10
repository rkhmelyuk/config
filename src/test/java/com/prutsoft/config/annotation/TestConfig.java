/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.annotation;

/**
 * Test interface for custom annotation based configuration.
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-10
 */
@Configuration
public interface TestConfig {

    @Property("firstName")
    String getFirstName();

    @Property("surname")
    String getLastName();

    @Property("hello")
    String sayHello(@Param("name") String username,
                    @Param("exclamation") String exclamation);
}
