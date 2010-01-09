/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser.xml;

import com.prutsoft.config.element.expression.ExpressionElement;
import com.prutsoft.config.element.expression.ExpressionFactory;
import com.prutsoft.config.exception.ParseException;
import com.prutsoft.config.parser.ConfigurationBuilder;
import com.prutsoft.core.annotation.NotNull;
import com.prutsoft.core.asserts.ArgumentAssert;
import org.w3c.dom.Node;

/**
 * The expression element parser.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public class ExpressionElementParser implements ElementParser {
    
    public boolean canParse(Node node) {
        return node != null && node.getNodeName().equals("expression");
    }

    public void parse(@NotNull Node node, @NotNull ConfigurationBuilder builder) throws ParseException {
        ArgumentAssert.isNotNull(node, "Node can't be null.");
        ArgumentAssert.isNotNull(builder, "Configuration builder can't be null.");

        final String name = ParserUtil.parseAttribute(node, "name", true);
        final String value = ParserUtil.parseAttribute(node, "value", true);

        builder.addExpression(new ExpressionElement(name, 
                ExpressionFactory.createExpression(value)));
    }
}
