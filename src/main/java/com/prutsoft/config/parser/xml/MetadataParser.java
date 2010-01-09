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
import com.prutsoft.core.utils.collections.ListUtils;
import org.w3c.dom.Node;

/**
 * The metadata element parser.
 * This class uses by default {@link com.prutsoft.config.parser.xml.MetadataPropertyParser}
 * to parser properties.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public class MetadataParser implements ElementParser {

    private ElementParser propertyParser;

    public ElementParser getPropertyParser() {
        if (propertyParser == null) {
            propertyParser = new MetadataPropertyParser();
        }
        return propertyParser;
    }

    public void setPropertyParser(ElementParser propertyParser) {
        this.propertyParser = propertyParser;
    }

    // ----------------------------------------------------------------

    public boolean canParse(Node node) {
        return node != null && node.getNodeName().equals("metadata");
    }

    public void parse(Node node, ConfigurationBuilder builder) throws ParseException {
        ArgumentAssert.isNotNull(node, "Node can't be null.");
        ArgumentAssert.isNotNull(builder, "Configuration builder can't be null.");

        ParserUtil.parseChildren(builder, node, ListUtils.listOf(getPropertyParser()));
    }

}
