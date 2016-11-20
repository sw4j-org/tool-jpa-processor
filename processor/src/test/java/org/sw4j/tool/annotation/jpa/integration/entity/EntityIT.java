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
public class EntityIT extends TestSuperclass {

    /** Default constructor. */
    public EntityIT() {
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
    public void testNamedEntity() throws XPathExpressionException {
        Node entity = getNode("/model/entities/entity[@name=\"NamedEntity\"]");

        Assert.assertNotNull(entity, "Expected an entity with name \"NamedEntity\" to exist.");
        Assert.assertEquals(entity.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(entity.getAttributes().getNamedItem("name").getNodeValue(),
                "NamedEntity", "Expected the entity name to be \"NamedEntity\"");
    }

}
