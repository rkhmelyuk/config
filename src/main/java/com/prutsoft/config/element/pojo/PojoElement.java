/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.pojo;

import com.prutsoft.config.NamedElement;
import com.prutsoft.config.NamedElementsContainer;
import com.prutsoft.config.element.ElementUtil;
import com.prutsoft.core.asserts.ArgumentAssert;
import com.prutsoft.core.code.Warnings;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The POJO configuration element.
 * Used to configure existing object.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-04
 */
public class PojoElement implements NamedElement, NamedElementsContainer, Serializable {

    private final Object lock = new Object();

    private final String name;
    private final Class pojoClass;
    private Map<String, NamedElement> elements = new HashMap<String, NamedElement>();
    private String description;

    private Object instance = null;

    public PojoElement(String name, Class pojoClass) {
        this.name = name;
        this.pojoClass = pojoClass;
    }

    public String getName() {
        return name;
    }

    public Class getPojoClass() {
        return pojoClass;
    }

    /**
     * Gets pojo description.
     * @return the pojo description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets pojo description.
     * @param description the new pojo description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public NamedElement getElement(String name) {
        return elements.get(name);
    }

    public Collection<NamedElement> getElements() {
        return Collections.unmodifiableCollection(elements.values());
    }

    public boolean addElement(NamedElement element) {
        ArgumentAssert.isNotNull(element, "Element can't be null.");
        if (hasElement(element.getName())) return false;
        elements.put(element.getName(), element);
        return true;
    }

    public boolean removeElement(String name) {
        return (elements.remove(name) != null);
    }

    public boolean hasElement(String name) {
        return elements.containsKey(name);
    }

    public Object getPojoInstance() {
        synchronized (lock) {
            if (instance == null) {
                instance = getPojoNewInstance();
            }
            return instance;
        }
    }

    public Object getPojoNewInstance() {
        try {
            Object pojo = pojoClass.newInstance();
            Method[] methods = pojoClass.getMethods();

            for (NamedElement each : elements.values()) {
                Object value = ElementUtil.getElementValue(each);
                setPropertyValue(pojo, methods, each, value);
            }

            return pojo;
        }
        catch (Exception e) {
            throw new IllegalStateException("Error to create new pojo instance.", e);
        }
    }

    @SuppressWarnings(Warnings.Unchecked)
    private void setPropertyValue(Object pojo, Method[] methods, NamedElement each, Object value) throws Exception {
        final String propertyName = each.getName().substring(0, 1).toUpperCase() + each.getName().substring(1);
        final Method[] propertyMethods = getPropertyMethods(methods, propertyName);
        final Method getter = propertyMethods[0];
        final Method setter = propertyMethods[1];

        if (value instanceof Collection) {
            // Try to get current collection
            Collection collection = (Collection) getter.invoke(pojo);
            if (collection != null) {
                collection.addAll((Collection) value);
            }
            else if (setter != null) {
                // If it's null than create new and add values to
                collection = (Collection) getter.getReturnType().newInstance();
                collection.addAll((Collection) value);
                setter.invoke(pojo, value);
            }
        }
        else if (setter != null) {
            setter.invoke(pojo, value);
        }
    }

    private Method[] getPropertyMethods(Method[] clazzMethods, String propertyName) {
        String setMethodName = "set" + propertyName;
        String getMethodName = "get" + propertyName;
        String isMethodName = "is" + propertyName;

        Method[] result = new Method[2];

        for (Method each : clazzMethods) {
            if (each.getName().equals(setMethodName)) {
                result[1] = each;
            }
            else if (each.getName().equals(getMethodName) || each.getName().equals(isMethodName)) {
                result[0] = each;
            }
        }

        return result;
    }

}
