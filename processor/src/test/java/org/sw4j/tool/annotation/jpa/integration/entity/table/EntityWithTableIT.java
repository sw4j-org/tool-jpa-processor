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
package org.sw4j.tool.annotation.jpa.integration.entity.table;

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
public class EntityWithTableIT extends ITSuperclass {

    /** Default constructor. */
    public EntityWithTableIT() {
    }

    @Test
    public void testEntityName() throws XPathExpressionException {
        Node entity = getNode("/model/entity[@name='EntityWithTable']");

        Assert.assertNotNull(entity, "Expected an entity with name \"EntityWithTable\" to exist.");
        Assert.assertEquals(entity.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(getAttribute("name", entity).getNodeValue(), "EntityWithTable",
                "Expected the entity name to be \"EntityWithTable\"");
        Assert.assertEquals(getAttribute("className", entity).getNodeValue(),
                "org.sw4j.tool.annotation.jpa.entity.table.EntityWithTable",
                "Expected the class name to be \"org.sw4j.tool.annotation.jpa.entity.table.EntityWithTable\"");
    }

    @Test
    public void testEntityTable() throws XPathExpressionException {
        Node table = getNode("/model/entity[@name='EntityWithTable']/table[@name='TAB_ENTITY']");

        Assert.assertNotNull(table, "Expected a table with name \"TAB_ENTITY\" to exist.");
        Assert.assertEquals(table.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(getAttribute("name", table).getNodeValue(), "TAB_ENTITY",
                "Expected the table name to be \"TAB_ENTITY\"");
        Assert.assertEquals(getAttribute("catalog", table).getNodeValue(), "",
                "Expected the catalog name to be \"\" (empty).");
        Assert.assertEquals(getAttribute("schema", table).getNodeValue(), "",
                "Expected the schema name to be \"\" (empty).");
    }

    @Test
    public void testEntityId() throws XPathExpressionException {
        Node attributeId = getNode("/model/entity[@name='EntityWithTable']/attribute[@name='id']");

        Assert.assertNotNull(attributeId, "Expected the entity with name \"EntityWithTable\" to have an " +
                "attribute with name \"id\" to exist.");
        Assert.assertEquals(attributeId.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(getAttribute("name", attributeId).getNodeValue(), "id",
                "Expected the entity with name \"EntityWithTable\" to have an attribute with the name \"id\".");
        Assert.assertEquals(getAttribute("isId", attributeId).getNodeValue(), "true",
                "Expected the entity with name \"EntityWithTable\" to have an attribute \"isId\" set to true.");
        Assert.assertEquals(getAttribute("dataType", attributeId).getNodeValue(), "int",
                "Expected the entity with name \"EntityWithTable\" to have an attribute with the dataType \"int\".");
    }

}
