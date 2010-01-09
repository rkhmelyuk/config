/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element;

import com.prutsoft.config.ContextMap;
import com.prutsoft.config.NamedElement;
import com.prutsoft.config.NamedElementsContainer;
import com.prutsoft.config.element.control.SwitchElement;
import com.prutsoft.config.element.expression.ExpressionElement;
import com.prutsoft.config.element.pojo.PojoElement;
import com.prutsoft.config.element.property.Property;
import com.prutsoft.config.element.set.PropertySet;
import com.prutsoft.config.element.value.Value;
import com.prutsoft.config.exception.ValueAccessException;
import com.prutsoft.core.code.Warnings;

import java.util.Collection;
import java.util.HashSet;

/**
 * Useful utils to work with configuration elements.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-04
 */
public class ElementUtil {

    /**
     * Gets the named element value.
     * If named element is {@code null} or of unexpected type than returned {@code null}.
     *
     * @param element the named element.
     * @param context the context map for evaluated values.
     * @return the element value if any.
     * @throws com.prutsoft.config.exception.ValueAccessException error to get element value.
     */
    @SuppressWarnings(Warnings.Unchecked)
    public static Object getElementValue(ContextMap context, NamedElement element) throws ValueAccessException {
        if (element == null) return null;

        if (element instanceof PojoElement) {
            return ((PojoElement) element).getPojoNewInstance();
        }
        else if (element instanceof SwitchElement) {
            // converting to Switch element
            SwitchElement switchElement = (SwitchElement) element;
            Value switchValue = switchElement.getValue(context);
            return switchValue != null ? switchValue.getValue() : null;
        }
        else if (element instanceof PropertySet) {
            // converting set element to Set element
            PropertySet propertySet = (PropertySet) element;
            Collection result = new HashSet();
            for (NamedElement each : propertySet.getElements()) {
                result.add(getElementValue(each));
            }
            return result;
        }
        else if (element instanceof Property) {
            Property property = (Property) element;
            return property.getValue().getValue();
        }
        else if (element instanceof ExpressionElement) {
            ExpressionElement expression = (ExpressionElement) element;
            return expression.getValue(context);
        }

        return null;
    }

    /**
     * Gets the named element value.
     * If named element is {@code null} or of unexpected type than returned {@code null}.
     *
     * @param element the named element.
     * @return the element value if any.
     * @throws com.prutsoft.config.exception.ValueAccessException error to get element value.
     */
    public static Object getElementValue(NamedElement element) throws ValueAccessException {
        return getElementValue(null, element);
    }


    @SuppressWarnings(Warnings.Unchecked)
    public static Object getElementValue(ContextMap context, NamedElementsContainer elements,
                                         String... path) throws ValueAccessException {
        if (elements == null) {
            throw new ValueAccessException("No value found: elements map is null.");
        }
        if (path == null || path.length == 0) {
            throw new ValueAccessException("No value found: path is wrong.");
        }


        // TODO - Think how to increase the performance.

        final String name = path[0];
        if (path.length == 1) {
            if (name.contains(":")) {
                return getElementValue(elements, name.split(":"));
            }
            return getElementValue(context, elements.getElement(name));
        }
        else {
            NamedElement element = elements.getElement(name);
            if (element != null && element instanceof NamedElementsContainer) {
                String[] newPath = new String[path.length - 1];
                System.arraycopy(path, 1, newPath, 0, newPath.length);
                return getElementValue((NamedElementsContainer) element, newPath);
            }
        }

        return null;
    }

    public static Object getElementValue(NamedElementsContainer elements,
                                         String... path) throws ValueAccessException {
        return getElementValue(null, elements, path);
    }
}
