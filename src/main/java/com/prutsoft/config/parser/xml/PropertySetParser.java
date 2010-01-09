/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser.xml;

import com.prutsoft.config.element.control.SwitchElement;
import com.prutsoft.config.element.expression.ExpressionElement;
import com.prutsoft.config.element.pojo.PojoElement;
import com.prutsoft.config.element.property.Property;
import com.prutsoft.config.element.set.PropertySet;
import com.prutsoft.config.exception.ParseException;
import com.prutsoft.config.parser.ConfigurationBuilder;
import com.prutsoft.core.asserts.ArgumentAssert;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * The property set parser.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public class PropertySetParser implements ElementParser {

    private ElementParser propertyParser;
    private ElementParser expressionParser;
    private ElementParser switchParser;
    private ElementParser setParser;
    private ElementParser pojoParser;

    public ElementParser getPropertyParser() {
        if (propertyParser == null) {
            propertyParser = new PropertyParser();
        }
        return propertyParser;
    }

    public void setPropertyParser(ElementParser propertyParser) {
        this.propertyParser = propertyParser;
    }

    public ElementParser getExpressionParser() {
        if (expressionParser == null) {
            expressionParser = new ExpressionElementParser();
        }
        return expressionParser;
    }

    public void setExpressionParser(ElementParser expressionParser) {
        this.expressionParser = expressionParser;
    }

    public ElementParser getSwitchParser() {
        if (switchParser == null) {
            switchParser = new SwitchElementParser();
        }
        return switchParser;
    }

    public void setSwitchParser(ElementParser switchParser) {
        this.switchParser = switchParser;
    }

    public ElementParser getSetParser() {
        if (setParser == null) {
            setParser = new PropertySetParser();
        }
        return setParser;
    }

    public void setSetParser(ElementParser setParser) {
        this.setParser = setParser;
    }

    public ElementParser getPojoParser() {
        if (pojoParser == null) {
            pojoParser = new PojoParser();
        }
        return pojoParser;
    }

    public void setPojoParser(ElementParser pojoParser) {
        this.pojoParser = pojoParser;
    }


    public boolean canParse(Node node) {
        return node != null && node.getNodeName().equals("set");
    }

    public void parse(Node node, ConfigurationBuilder builder) throws ParseException {
        ArgumentAssert.isNotNull(node, "Node can't be null.");
        ArgumentAssert.isNotNull(builder, "Configuration builder can't be null.");

        final String name = ParserUtil.parseAttribute(node, "name", true);

        final PropertySet set = new PropertySet(name);
        builder.addSet(set);

        ConfigurationBuilder setBuilder = new ConfigurationBuilder();
        ParserUtil.parseChildren(setBuilder, node, getParsersList());

        for (Property each : setBuilder.getProperties()) {
            set.addElement(each);
        }
        for (SwitchElement each : setBuilder.getSwitches()) {
            set.addElement(each);
        }
        for (ExpressionElement each : setBuilder.getExpressions()) {
            set.addElement(each);
        }
        for (PropertySet each : setBuilder.getSets()) {
            set.addElement(each);
        }
        for (PojoElement each : setBuilder.getPojos()) {
            set.addElement(each);
        }
    }

    private List<ElementParser> getParsersList() {
        final List<ElementParser> parsers = new ArrayList<ElementParser>();
        parsers.add(getPropertyParser());
        parsers.add(getExpressionParser());
        parsers.add(getSetParser());
        parsers.add(getSwitchParser());
        parsers.add(getPojoParser());
        return parsers;
    }
}
