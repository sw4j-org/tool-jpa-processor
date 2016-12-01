/*
 * Copyright (C) 2016 Uwe Plonus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sw4j.tool.annotation.jpa.integration.entity.property;

import javax.xml.xpath.XPathExpressionException;
import org.sw4j.tool.annotation.jpa.integration.util.ITSuperclass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Node;

/**
 * This class contains tests for the annotation {@code @Entity}.
 *
 * @author Uwe Plonus
 */
public class SimplePropertyEntityIT extends ITSuperclass {

    /** Default constructor. */
    public SimplePropertyEntityIT() {
    }

    @Test
    public void testSimpleEntity() throws XPathExpressionException {
        Node entity = getNode("/model/entity[@name=\"SimplePropertyEntity\"]");

        Assert.assertNotNull(entity, "Expected an entity with name \"SimplePropertyEntity\" to exist.");
        Assert.assertEquals(entity.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(getAttribute("name", entity).getNodeValue(), "SimplePropertyEntity",
                "Expected the entity name to be \"SimplePropertyEntity\"");
        Assert.assertEquals(getAttribute("className", entity).getNodeValue(),
                "org.sw4j.tool.annotation.jpa.entity.properties.SimplePropertyEntity",
                "Expected the class name to be \"org.sw4j.tool.annotation.jpa.entity.properties.SimplePropertyEntity\"");
    }

    @Test
    public void testSimpleEntityId() throws XPathExpressionException {
        Node attributeId = getNode("/model/entity[@name=\"SimplePropertyEntity\"]/attribute[@name=\"id\"]");

        Assert.assertNotNull(attributeId, "Expected the entity with name \"SimplePropertyEntity\" to have an " +
                "attribute with name \"id\" to exist.");
        Assert.assertEquals(attributeId.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(getAttribute("name", attributeId).getNodeValue(), "id",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute with the name \"id\".");
        Assert.assertEquals(getAttribute("isId", attributeId).getNodeValue(), "true",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute \"isId\" set to true.");
        Assert.assertEquals(getAttribute("dataType", attributeId).getNodeValue(), "int",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute with the dataType \"int\".");
    }

    @Test
    public void testSimpleEntityValue() throws XPathExpressionException {
        Node attributeId = getNode("/model/entity[@name=\"SimplePropertyEntity\"]/attribute[@name=\"value\"]");

        Assert.assertNotNull(attributeId, "Expected the entity with name \"SimplePropertyEntity\" to have an " +
                "attribute with name \"value\" to exist.");
        Assert.assertEquals(attributeId.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(getAttribute("name", attributeId).getNodeValue(), "value",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute with the name \"value\".");
        Assert.assertEquals(getAttribute("isId", attributeId).getNodeValue(), "false",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute \"isId\" set to false.");
        Assert.assertEquals(getAttribute("dataType", attributeId).getNodeValue(), "int",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute with the dataType \"int\".");
    }

    @Test
    public void testSimpleEntityFlag() throws XPathExpressionException {
        Node attributeId = getNode("/model/entity[@name=\"SimplePropertyEntity\"]/attribute[@name=\"flag\"]");

        Assert.assertNotNull(attributeId, "Expected the entity with name \"SimplePropertyEntity\" to have an " +
                "attribute with name \"flag\" to exist.");
        Assert.assertEquals(attributeId.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(getAttribute("name", attributeId).getNodeValue(), "flag",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute with the name \"flag\".");
        Assert.assertEquals(getAttribute("isId", attributeId).getNodeValue(), "false",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute \"isId\" set to false.");
        Assert.assertEquals(getAttribute("dataType", attributeId).getNodeValue(), "boolean",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute with the dataType \"boolean\".");
    }

    @Test
    public void testSimpleEntityClassFlag() throws XPathExpressionException {
        Node attributeId = getNode("/model/entity[@name=\"SimplePropertyEntity\"]/attribute[@name=\"classFlag\"]");

        Assert.assertNotNull(attributeId, "Expected the entity with name \"SimplePropertyEntity\" to have an " +
                "attribute with name \"classFlag\" to exist.");
        Assert.assertEquals(attributeId.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(getAttribute("name", attributeId).getNodeValue(), "classFlag",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute with the name \"classFlag\".");
        Assert.assertEquals(getAttribute("isId", attributeId).getNodeValue(), "false",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute \"isId\" set to false.");
        Assert.assertEquals(getAttribute("dataType", attributeId).getNodeValue(), "java.lang.Boolean",
                "Expected the entity with name \"SimplePropertyEntity\" to have an attribute with the dataType \"java.lang.Boolean\".");
    }

}
