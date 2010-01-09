/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser.xml;

import com.prutsoft.config.exception.ParseException;
import com.prutsoft.config.parser.ConfigurationBuilder;
import com.prutsoft.core.annotation.NotNull;
import org.w3c.dom.Node;

/**
 * The Configuration named element parser.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public interface ElementParser {

    /**
     * Checks whether this parser can parse specified node.
     *
     * @param node the node to check to parser.
     * @return {@code true} if can parser, otherwise returns {@code false}.
     */
    boolean canParse(Node node);

    /**
     * Parses node from specified node.
     *
     * @param node    the xml node with node; can't be null.
     * @param builder the configuration builder; can't be null.
     * @throws ParseException error to parse node.
     */
    void parse(@NotNull Node node, @NotNull ConfigurationBuilder builder) throws ParseException;

}
