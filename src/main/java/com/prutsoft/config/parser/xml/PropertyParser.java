/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser.xml;

import com.prutsoft.config.element.property.Property;
import com.prutsoft.config.element.value.RegularValue;
import com.prutsoft.config.element.value.ValueTypeRegistry;
import com.prutsoft.config.exception.ParseException;
import com.prutsoft.config.parser.ConfigurationBuilder;
import com.prutsoft.core.asserts.ArgumentAssert;
import com.prutsoft.core.code.Warnings;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * The regular property parser.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public class PropertyParser implements ElementParser {

    private ElementParser valueParser;
    private ElementParser descriptionParser;

    public ElementParser getValueParser() {
        if (valueParser == null) {
            valueParser = new ValueParser();
        }
        return valueParser;
    }

    public void setValueParser(ElementParser valueParser) {
        this.valueParser = valueParser;
    }

    public ElementParser getDescriptionParser() {
        if (descriptionParser == null) {
            descriptionParser = new DescriptionParser();
        }
        return descriptionParser;
    }

    public void setDescriptionParser(ElementParser descriptionParser) {
        this.descriptionParser = descriptionParser;
    }

    // -----------------------------------------------------------------------------

    public boolean canParse(Node node) {
        return node != null && node.getNodeName().equals("property");
    }

    @SuppressWarnings(Warnings.Unchecked)
    public void parse(Node node, ConfigurationBuilder builder) throws ParseException {
        ArgumentAssert.isNotNull(node, "Node can't be null.");
        ArgumentAssert.isNotNull(builder, "Configuration builder can't be null.");

        final String name = ParserUtil.parseAttribute(node, "name", true);
        final Property property = new Property(name);
        builder.addProperty(property);

        String type = null;
        final Node typeNode = node.getAttributes().getNamedItem("type");
        if (typeNode != null) {
            type = typeNode.getNodeValue();
        }

        final Node valueNode = node.getAttributes().getNamedItem("value");
        if (valueNode != null) {
            final String nodeValue = valueNode.getNodeValue();

            // set property value
            property.setValue(RegularValue.create(nodeValue,
                    ValueTypeRegistry.getValueType(type)));
        }

        builder.getBuffer().clear();

        ParserUtil.parseChildren(builder, node, getParsersList());

        property.setDescription(builder.getBuffer().getDescription());
        if (builder.getBuffer().getValue() != null) {
            property.setValue(builder.getBuffer().getValue());
        }

        if (property.getValue() == null) {
            throw new ParseException("Property value is not specified.");
        }

        builder.getBuffer().clear();
    }

    private List<ElementParser> getParsersList() {
        final List<ElementParser> parsers = new ArrayList<ElementParser>();
        parsers.add(getValueParser());
        parsers.add(getDescriptionParser());
        return parsers;
    }
}
