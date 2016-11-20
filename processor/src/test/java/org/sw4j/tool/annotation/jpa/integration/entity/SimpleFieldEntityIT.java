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
package org.sw4j.tool.annotation.jpa.integration.entity;

import javax.xml.xpath.XPathExpressionException;
import org.sw4j.tool.annotation.jpa.integration.util.TestSuperclass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Node;

/**
 * This class contains tests for the annotation {@code @Entity}.
 *
 * @author Uwe Plonus
 */
public class SimpleFieldEntityIT extends TestSuperclass {

    /** Default constructor. */
    public SimpleFieldEntityIT() {
    }

    @Test
    public void testSimpleEntity() throws XPathExpressionException {
        Node entity = getNode("/model/entities/entity[@name=\"SimpleFieldEntity\"]");

        Assert.assertNotNull(entity, "Expected an entity with name \"SimpleFieldEntity\" to exist.");
        Assert.assertEquals(entity.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(entity.getAttributes().getNamedItem("name").getNodeValue(),
                "SimpleFieldEntity", "Expected the entity name to be \"SimpleFieldEntity\"");
    }

    @Test
    public void testSimpleEntityHasAttributes() throws XPathExpressionException {
        Node attributes = getNode("/model/entities/entity[@name=\"SimpleFieldEntity\"]/attributes");

        Assert.assertNotNull(attributes, "Expected the entity with name \"SimpleFieldEntity\" to have attributes.");
    }

    @Test
    public void testSimpleEntityId() throws XPathExpressionException {
        Node attributeId = getNode("/model/entities/entity[@name=\"SimpleFieldEntity\"]/attributes/" +
                "attribute[@name=\"id\"]");

        Assert.assertNotNull(attributeId, "Expected the entity with name \"SimpleFieldEntity\" to have an attribute " +
                "with name \"id\" to exist.");
        Assert.assertEquals(attributeId.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(attributeId.getAttributes().getNamedItem("name").getNodeValue(),
                "id", "Expected the entity with name \"SimpleFieldEntity\" to have an attribute with the name \"id\"");
    }

    @Test
    public void testSimpleEntityValue() throws XPathExpressionException {
        Node attributeId = getNode("/model/entities/entity[@name=\"SimpleFieldEntity\"]/attributes/" +
                "attribute[@name=\"value\"]");

        Assert.assertNotNull(attributeId, "Expected the entity with name \"SimpleFieldEntity\" to have an attribute " +
                "with name \"value\" to exist.");
        Assert.assertEquals(attributeId.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(attributeId.getAttributes().getNamedItem("name").getNodeValue(), "value",
                "Expected the entity with name \"SimpleFieldEntity\" to have an attribute with the name \"value\"");
    }

}
