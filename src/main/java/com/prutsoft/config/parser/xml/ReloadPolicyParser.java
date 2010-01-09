/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser.xml;

import com.prutsoft.config.element.reload.ReloadPolicy;
import com.prutsoft.config.exception.ParseException;
import com.prutsoft.config.parser.ConfigurationBuilder;
import com.prutsoft.core.annotation.NotNull;
import com.prutsoft.core.asserts.ArgumentAssert;
import com.prutsoft.core.utils.ConversionUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The reload policy information parser.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-09
 */
public class ReloadPolicyParser implements ElementParser {

    public boolean canParse(Node node) {
        return node != null && node.getNodeName().equals("reload");
    }

    public void parse(@NotNull Node node, @NotNull ConfigurationBuilder builder) throws ParseException {
        ArgumentAssert.isNotNull(node, "Node can't be null.");
        ArgumentAssert.isNotNull(builder, "Configuration builder can't be null.");

        final ReloadPolicy reloadPolicy = new ReloadPolicy();
        builder.setReloadPolicy(reloadPolicy);

        final NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node child = children.item(i);
            if (child == null) continue;

            if (child.getNodeName().equals("onchange")) {
                reloadPolicy.setOnChange(true);

                String checkEvery = ParserUtil.parseAttribute(child, "check-every", true);
                reloadPolicy.setCheckEvery(parsePeriod(checkEvery.trim()));
            }
        }
    }

    private Integer parsePeriod(String checkEvery) throws ParseException {
        int multiplier;
        if (checkEvery.endsWith("s")) {
            multiplier = 1;
        }
        else if (checkEvery.endsWith("m")) {
            multiplier = 60;
        }
        else if (checkEvery.endsWith("h")) {
            multiplier = 3600;
        }
        else if (checkEvery.endsWith("d")) {
            multiplier = 86400;
        }
        else {
            throw new ParseException("Unknown duration marker: " + checkEvery.charAt(checkEvery.length() - 1)
                    + " at attribute 'check-every' on entry 'reload'.");
        }

        return ConversionUtils.getInteger(checkEvery.substring(0, checkEvery.length() - 1)) * multiplier;
    }
}
