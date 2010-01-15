/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
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
 * The parser for the include element.
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-15
 */
public class IncludeParser implements ElementParser {

    public boolean canParse(Node node) {
        return (node != null && "include".equals(node.getNodeName()));
    }

    public void parse(Node node, ConfigurationBuilder builder) throws ParseException {
        ArgumentAssert.isNotNull(node, "Node can't be null.");
        ArgumentAssert.isNotNull(builder, "Configuration builder can't be null.");

        builder.addConfigurationPath(ParserUtil.parseAttribute(node, "path", true));
    }
}
