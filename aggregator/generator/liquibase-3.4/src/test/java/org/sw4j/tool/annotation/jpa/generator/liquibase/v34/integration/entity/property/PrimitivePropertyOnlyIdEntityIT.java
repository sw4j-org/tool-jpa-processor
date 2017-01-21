/*
 * Copyright (C) 2016 uwe
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
package org.sw4j.tool.annotation.jpa.generator.liquibase.v34.integration.entity.property;

import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.integration.entity.field.*;
import javax.xml.xpath.XPathExpressionException;
import org.sw4j.tool.annotation.jpa.generator.util.ITSuperclass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Node;

/**
 * Class to test the mapping of PrimitiveFieldOnlyIdEntity to a databaseChangeLog.
 *
 * @author Uwe Plonus
 */
public class PrimitivePropertyOnlyIdEntityIT extends ITSuperclass {

    @Test
    public void testChangeSetElement() throws XPathExpressionException {
        Node changeSet = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyOnlyIdEntity')]");

        Assert.assertNotNull(changeSet, "Expected a changeSet to be created.");
        Assert.assertNotNull(getAttribute("id", changeSet), "Expected the changeSet to have an id attribute");
        Assert.assertTrue(getAttribute("id", changeSet).getNodeValue()
                .startsWith("createTable_PrimitivePropertyOnlyIdEntity"),
                "Expected the id attribute to start with \"createTable_PrimitivePropertyOnlyIdEntity\"");
        Assert.assertNotNull(getAttribute("author", changeSet), "Expected the changeSet to have an author attribute");
        Assert.assertEquals(getAttribute("author", changeSet).getNodeValue(), "generated",
                "Expected the author attribute to be \"generated\"");
    }

    @Test
    public void testCreateTableElement() throws XPathExpressionException {
        Node createTable = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyOnlyIdEntity')]" +
                "/createTable[@tableName='PrimitivePropertyOnlyIdEntity']");

        Assert.assertNotNull(createTable, "Expected a createTable to be created.");
        Assert.assertNotNull(getAttribute("tableName", createTable),
                "Expected the createTable to have a tableName attribute.");
        Assert.assertEquals(getAttribute("tableName", createTable).getNodeValue(), "PrimitivePropertyOnlyIdEntity",
                "Expected the tableName attribute to be \"PrimitivePropertyOnlyIdEntity\"");
    }

    @Test
    public void testIdColumnElementName() throws XPathExpressionException {
        Node idColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyOnlyIdEntity')]" +
                "/createTable[@tableName='PrimitivePropertyOnlyIdEntity']" +
                "/column[@name='id']");

        Assert.assertNotNull(idColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", idColumn), "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", idColumn).getNodeValue(), "id",
                "Expected the name attribute to be \"id\"");
    }

    @Test
    public void testIdColumnElementType() throws XPathExpressionException {
        Node idColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyOnlyIdEntity')]" +
                "/createTable[@tableName='PrimitivePropertyOnlyIdEntity']" +
                "/column[@name='id']");

        Assert.assertNotNull(idColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", idColumn), "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", idColumn).getNodeValue(), "java.sql.Types.INTEGER",
                "Expected the type attribute to be \"java.sql.Types.INTEGER\"");
    }

    @Test
    public void testIdColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyOnlyIdEntity')]" +
                "/createTable[@tableName='PrimitivePropertyOnlyIdEntity']" +
                "/column[@name='id']/constraints");

        Assert.assertNotNull(constraints, "Expected constraints to be created.");
        Assert.assertNotNull(getAttribute("primaryKey", constraints),
                "Expected the constraints to have a primaryKey attribute.");
        Assert.assertEquals(getAttribute("primaryKey", constraints).getNodeValue(), "true",
                "Expected the primaryKey attribute to be \"true\"");
    }

}
