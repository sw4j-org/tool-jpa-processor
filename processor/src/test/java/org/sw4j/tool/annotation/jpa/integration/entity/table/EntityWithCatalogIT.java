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
public class EntityWithCatalogIT extends ITSuperclass {

    /** Default constructor. */
    public EntityWithCatalogIT() {
    }

    @Test
    public void testEntityName() throws XPathExpressionException {
        Node entity = getNode("/model/entity[@name='EntityWithCatalog']");

        Assert.assertNotNull(entity, "Expected an entity with name \"EntityWithCatalog\" to exist.");
        Assert.assertEquals(entity.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(getAttribute("name", entity).getNodeValue(), "EntityWithCatalog",
                "Expected the entity name to be \"EntityWithCatalog\"");
        Assert.assertEquals(getAttribute("className", entity).getNodeValue(),
                "org.sw4j.tool.annotation.jpa.entity.table.EntityWithCatalog",
                "Expected the class name to be \"org.sw4j.tool.annotation.jpa.entity.table.EntityWithCatalog\"");
    }

    @Test
    public void testEntityTable() throws XPathExpressionException {
        Node table = getNode("/model/entity[@name='EntityWithCatalog']/table[@name='EntityWithCatalog']");

        Assert.assertNotNull(table, "Expected a table with name \"EntityWithCatalog\" to exist.");
        Assert.assertEquals(table.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(getAttribute("name", table).getNodeValue(), "EntityWithCatalog",
                "Expected the table name to be \"EntityWithCatalog\"");
        Assert.assertEquals(getAttribute("catalog", table).getNodeValue(), "catalog",
                "Expected the catalog name to be \"catalog\".");
        Assert.assertEquals(getAttribute("schema", table).getNodeValue(), "",
                "Expected the schema name to be \"\" (empty).");
    }

    @Test
    public void testEntityId() throws XPathExpressionException {
        Node attributeId = getNode("/model/entity[@name='EntityWithCatalog']/attribute[@name='id']");

        Assert.assertNotNull(attributeId, "Expected the entity with name \"EntityWithCatalog\" to have an " +
                "attribute with name \"id\" to exist.");
        Assert.assertEquals(attributeId.getNodeType(), Node.ELEMENT_NODE, "Expected an element.");
        Assert.assertEquals(getAttribute("name", attributeId).getNodeValue(), "id",
                "Expected the entity with name \"EntityWithCatalog\" to have an attribute with the name \"id\".");
        Assert.assertEquals(getAttribute("isId", attributeId).getNodeValue(), "true",
                "Expected the entity with name \"EntityWithCatalog\" to have an attribute \"isId\" set to true.");
        Assert.assertEquals(getAttribute("dataType", attributeId).getNodeValue(), "int",
                "Expected the entity with name \"EntityWithCatalog\" to have an attribute with the dataType \"int\".");
    }

}
