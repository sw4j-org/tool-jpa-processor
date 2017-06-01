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
package org.sw4j.tool.annotation.jpa.generator.liquibase.v34.integration.entity.field;

import javax.xml.xpath.XPathExpressionException;
import org.sw4j.tool.annotation.jpa.generator.util.ITSuperclass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Node;

/**
 * Class to test the mapping of ObjectFieldEntity to a databaseChangeLog.
 *
 * @author Uwe Plonus
 */
public class ObjectFieldEntityIT extends ITSuperclass {

    @Test
    public void testChangeSetElement() throws XPathExpressionException {
        Node changeSet = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]");

        Assert.assertNotNull(changeSet, "Expected a changeSet to be created.");
        Assert.assertNotNull(getAttribute("id", changeSet), "Expected the changeSet to have an id attribute");
        Assert.assertTrue(getAttribute("id", changeSet).getNodeValue()
                .startsWith("createTable_ObjectFieldEntity"),
                "Expected the id attribute to start with \"createTable_ObjectFieldEntity\"");
        Assert.assertNotNull(getAttribute("author", changeSet), "Expected the changeSet to have an author attribute");
        Assert.assertEquals(getAttribute("author", changeSet).getNodeValue(), "generated",
                "Expected the author attribute to be \"generated\"");
    }

    @Test
    public void testCreateTableElement() throws XPathExpressionException {
        Node createTable = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']");

        Assert.assertNotNull(createTable, "Expected a createTable to be created.");
        Assert.assertNotNull(getAttribute("tableName", createTable),
                "Expected the createTable to have a tableName attribute.");
        Assert.assertEquals(getAttribute("tableName", createTable).getNodeValue(), "ObjectFieldEntity",
                "Expected the tableName attribute to be \"ObjectFieldEntity\"");
    }

    @Test
    public void testObjectLongColumnElementName() throws XPathExpressionException {
        Node objectLongColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectLong']");

        Assert.assertNotNull(objectLongColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", objectLongColumn), "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", objectLongColumn).getNodeValue(), "objectLong",
                "Expected the name attribute to be \"objectLong\"");
    }

    @Test
    public void testObjectLongColumnElementType() throws XPathExpressionException {
        Node objectLongColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectLong']");

        Assert.assertNotNull(objectLongColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", objectLongColumn), "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", objectLongColumn).getNodeValue(), "java.sql.Types.BIGINT",
                "Expected the type attribute to be \"java.sql.Types.BIGINT\"");

    }

    @Test
    public void testObjectLongColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectLong']/constraints");

        Assert.assertNotNull(constraints, "Expected constraints to be created.");
        Assert.assertNotNull(getAttribute("primaryKey", constraints),
                "Expected the constraints to have a primaryKey attribute.");
        Assert.assertEquals(getAttribute("primaryKey", constraints).getNodeValue(), "true",
                "Expected the primaryKey attribute to be \"true\"");
    }

    @Test
    public void testObjectIntegerColumnElementName() throws XPathExpressionException {
        Node objectIntegerColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectInteger']");

        Assert.assertNotNull(objectIntegerColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", objectIntegerColumn),
                "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", objectIntegerColumn).getNodeValue(), "objectInteger",
                "Expected the name attribute to be \"objectInteger\"");
    }

    @Test
    public void testObjectIntegerColumnElementType() throws XPathExpressionException {
        Node objectIntegerColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectInteger']");

        Assert.assertNotNull(objectIntegerColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", objectIntegerColumn), "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", objectIntegerColumn).getNodeValue(), "java.sql.Types.INTEGER",
                "Expected the type attribute to be \"java.sql.Types.INTEGER\"");
    }

    @Test
    public void testObjectIntegerColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectInteger']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

    @Test
    public void testObjectShortColumnElementName() throws XPathExpressionException {
        Node objectShortColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectShort']");

        Assert.assertNotNull(objectShortColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", objectShortColumn),
                "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", objectShortColumn).getNodeValue(), "objectShort",
                "Expected the name attribute to be \"objectShort\"");
    }

    @Test
    public void testObjectShortColumnElementType() throws XPathExpressionException {
        Node objectShortColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectShort']");

        Assert.assertNotNull(objectShortColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", objectShortColumn), "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", objectShortColumn).getNodeValue(), "java.sql.Types.SMALLINT",
                "Expected the type attribute to be \"java.sql.Types.SMALLINT\"");
    }

    @Test
    public void testObjectShortColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectShort']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

    @Test
    public void testObjectByteColumnElementName() throws XPathExpressionException {
        Node objectByteColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectByte']");

        Assert.assertNotNull(objectByteColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", objectByteColumn),
                "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", objectByteColumn).getNodeValue(), "objectByte",
                "Expected the name attribute to be \"objectByte\"");
    }

    @Test
    public void testObjectByteColumnElementType() throws XPathExpressionException {
        Node objectByteColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectByte']");

        Assert.assertNotNull(objectByteColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", objectByteColumn), "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", objectByteColumn).getNodeValue(), "java.sql.Types.TINYINT",
                "Expected the type attribute to be \"java.sql.Types.TINYINT\"");
    }

    @Test
    public void testObjectByteColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectByte']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

    @Test
    public void testObjectBooleanColumnElementName() throws XPathExpressionException {
        Node objectBooleanColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectBoolean']");

        Assert.assertNotNull(objectBooleanColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", objectBooleanColumn),
                "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", objectBooleanColumn).getNodeValue(), "objectBoolean",
                "Expected the name attribute to be \"objectBoolean\"");
    }

    @Test
    public void testObjectBooleanColumnElementType() throws XPathExpressionException {
        Node objectBooleanColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectBoolean']");

        Assert.assertNotNull(objectBooleanColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", objectBooleanColumn),
                "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", objectBooleanColumn).getNodeValue(), "java.sql.Types.BOOLEAN",
                "Expected the type attribute to be \"java.sql.Types.BOOLEAN\"");
    }

    @Test
    public void testObjectBooleanColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_ObjectFieldEntity')]" +
                "/createTable[@tableName='ObjectFieldEntity']" +
                "/column[@name='objectBoolean']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

}
