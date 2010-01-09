/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser.xml;

import com.prutsoft.config.exception.ParseException;
import com.prutsoft.config.parser.ConfigurationBuilder;
import com.prutsoft.core.asserts.ArgumentAssert;
import org.w3c.dom.Node;

/**
 * The property value parser.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public class DescriptionParser implements ElementParser {

    public boolean canParse(Node node) {
        return node != null && node.getNodeName().equals("description");
    }

    public void parse(Node node, ConfigurationBuilder builder) throws ParseException {
        ArgumentAssert.isNotNull(node, "Node can't be null.");
        ArgumentAssert.isNotNull(builder, "Configuration builder can't be null.");

        builder.getBuffer().setDescription(node.getTextContent());
    }
}
