/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.service;

import com.prutsoft.config.Configuration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * The invocation handler for the dynamic configuration.
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-10
 */
class StaticConfigInvocationHandler implements InvocationHandler {

    private final Configuration configuration;

    StaticConfigInvocationHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return "HELLO";
    }
}
