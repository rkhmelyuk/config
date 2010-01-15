/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.service;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.ContextMap;
import com.prutsoft.config.annotation.ConfigurationBindException;
import com.prutsoft.config.annotation.Param;
import com.prutsoft.config.annotation.Property;
import com.prutsoft.config.annotation.PropertyNotFoundException;
import com.prutsoft.core.code.Warnings;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * The invocation handler for the dynamic configuration.
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-10
 */
class ConfigurationInvocationHandler implements InvocationHandler {

    private final Configuration configuration;

    ConfigurationInvocationHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final Property annotation = method.getAnnotation(Property.class);
        if (annotation == null) {
            throw new ConfigurationBindException("Method [" + method.getName()
                    + "] is not annotated as property.");
        }

        final String propertyName = annotation.value();
        if (!configuration.hasElement(propertyName)) {
            throw new PropertyNotFoundException("Property [" + propertyName
                    + "] is not found in configuration [" + configuration + "]");
        }

        try {
            if (args == null || args.length == 0) {
                return configuration.getValue(propertyName);
            }
            final Map<String, Object> valuesMap = new HashMap<String, Object>();
            for (int index = 0; index < args.length; index++) {
                Object value = args[index];
                Param param = getAnnotation(method.getParameterAnnotations()[index], Param.class);
                if (param != null) {
                    valuesMap.put(param.value(), value);
                }
            }

            return configuration.getValue(propertyName, new ContextMap(valuesMap));
        }
        catch (Exception e) {
            throw new ConfigurationBindException("Can't get property [" + propertyName + "] " +
                    "from configuration [" + configuration + "]", e);
        }
    }

    @SuppressWarnings(Warnings.Unchecked)
    private <T extends Annotation> T getAnnotation(Annotation[] annotations, Class<T> annotationType) {
        for (Annotation each : annotations) {
            if (each.annotationType().equals(annotationType)) {
                return (T) each;
            }
        }
        return null;
    }
}
