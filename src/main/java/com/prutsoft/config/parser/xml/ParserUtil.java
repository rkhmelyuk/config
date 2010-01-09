/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser.xml;

import com.prutsoft.config.exception.ParseException;
import com.prutsoft.config.parser.ConfigurationBuilder;
import com.prutsoft.core.utils.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

/**
 * The utils for the XML parsers.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
class ParserUtil {

    /**
     * Parse node children using specified parsers.
     *
     * @param builder the configuration builder.
     * @param node    the node which children should be parsed.
     * @param parsers the list with configuration parsers.
     * @throws com.prutsoft.config.exception.ParseException
     *          error to parser node children.
     */
    public static void parseChildren(ConfigurationBuilder builder, Node node,
                                     List<ElementParser> parsers) throws ParseException {
        final NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node child = children.item(i);
            if (child == null) continue;

            // Try to find appropriate parser for the child node.
            for (ElementParser each : parsers) {
                if (each.canParse(child)) {
                    each.parse(child, builder);
                    break;
                }
            }
        }
    }

    /**
     * Parsers specified attribute of the node.
     *
     * @param node the node which attribute need to parser.
     * @param name the attribute name.
     * @param required whether attribute and it's value is required to be set.
     * @return the attribute value.
     * @throws ParseException attribute is not found or value is not set but required.
     */
    public static String parseAttribute(Node node, String name, boolean required) throws ParseException {
        Node attribute = node.getAttributes().getNamedItem(name);
        if (attribute == null) {
            if (required) {
                throw new ParseException("Attribute '" + name + "' is not found.");
            }
            return null;
        }

        String value = attribute.getNodeValue();
        if (required && StringUtils.isEmptyTrimmed(value)) {
            throw new ParseException("Attribute '" + name + "' can't be empty..");
        }
        return value;
    }


}
