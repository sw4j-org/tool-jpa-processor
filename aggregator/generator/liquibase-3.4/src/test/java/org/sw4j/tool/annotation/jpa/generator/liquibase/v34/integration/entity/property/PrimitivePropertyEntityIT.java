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
public class PrimitivePropertyEntityIT extends ITSuperclass {

    @Test
    public void testChangeSetElement() throws XPathExpressionException {
        Node changeSet = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]");

        Assert.assertNotNull(changeSet, "Expected a changeSet to be created.");
        Assert.assertNotNull(getAttribute("id", changeSet), "Expected the changeSet to have an id attribute");
        Assert.assertTrue(getAttribute("id", changeSet).getNodeValue()
                .startsWith("createTable_PrimitivePropertyEntity"),
                "Expected the id attribute to start with \"createTable_PrimitivePropertyEntity\"");
        Assert.assertNotNull(getAttribute("author", changeSet), "Expected the changeSet to have an author attribute");
        Assert.assertEquals(getAttribute("author", changeSet).getNodeValue(), "generated",
                "Expected the author attribute to be \"generated\"");
    }

    @Test
    public void testCreateTableElement() throws XPathExpressionException {
        Node createTable = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']");

        Assert.assertNotNull(createTable, "Expected a createTable to be created.");
        Assert.assertNotNull(getAttribute("tableName", createTable),
                "Expected the createTable to have a tableName attribute.");
        Assert.assertEquals(getAttribute("tableName", createTable).getNodeValue(), "PrimitivePropertyEntity",
                "Expected the tableName attribute to be \"PrimitivePropertyEntity\"");
    }

    @Test
    public void testPrimitiveLongColumnElementName() throws XPathExpressionException {
        Node primitiveLongColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveLong']");

        Assert.assertNotNull(primitiveLongColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", primitiveLongColumn), "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", primitiveLongColumn).getNodeValue(), "primitiveLong",
                "Expected the name attribute to be \"primitiveLong\"");
    }

    @Test
    public void testPrimitiveLongColumnElementType() throws XPathExpressionException {
        Node primitiveLongColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveLong']");

        Assert.assertNotNull(primitiveLongColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", primitiveLongColumn), "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", primitiveLongColumn).getNodeValue(), "java.sql.Types.BIGINT",
                "Expected the type attribute to be \"java.sql.Types.BIGINT\"");
    }

    @Test
    public void testPrimitiveLongColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveLong']/constraints");

        Assert.assertNotNull(constraints, "Expected constraints to be created.");
        Assert.assertNotNull(getAttribute("primaryKey", constraints),
                "Expected the constraints to have a primaryKey attribute.");
        Assert.assertEquals(getAttribute("primaryKey", constraints).getNodeValue(), "true",
                "Expected the primaryKey attribute to be \"true\"");
    }

    @Test
    public void testObjectLongColumnElementName() throws XPathExpressionException {
        Node objectLongColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='objectLong']");

        Assert.assertNotNull(objectLongColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", objectLongColumn), "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", objectLongColumn).getNodeValue(), "objectLong",
                "Expected the name attribute to be \"objectLong\"");
    }

    @Test
    public void testObjectLongColumnElementType() throws XPathExpressionException {
        Node objectLongColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='objectLong']");

        Assert.assertNotNull(objectLongColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", objectLongColumn), "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", objectLongColumn).getNodeValue(), "java.sql.Types.BIGINT",
                "Expected the type attribute to be \"java.sql.Types.BIGINT\"");
    }

    @Test
    public void testObjectLongColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='objectLong']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

    @Test
    public void testPrimitiveIntColumnElementName() throws XPathExpressionException {
        Node primitiveIntColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveInt']");

        Assert.assertNotNull(primitiveIntColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", primitiveIntColumn), "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", primitiveIntColumn).getNodeValue(), "primitiveInt",
                "Expected the name attribute to be \"primitiveInt\"");
    }

    @Test
    public void testPrimitiveIntColumnElementType() throws XPathExpressionException {
        Node primitiveIntColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveInt']");

        Assert.assertNotNull(primitiveIntColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", primitiveIntColumn), "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", primitiveIntColumn).getNodeValue(), "java.sql.Types.INTEGER",
                "Expected the type attribute to be \"java.sql.Types.INTEGER\"");
    }

    @Test
    public void testPrimitiveIntColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveInt']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

    @Test
    public void testObjectIntegerColumnElementName() throws XPathExpressionException {
        Node objectIntegerColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
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
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='objectInteger']");

        Assert.assertNotNull(objectIntegerColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", objectIntegerColumn), "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", objectIntegerColumn).getNodeValue(), "java.sql.Types.INTEGER",
                "Expected the type attribute to be \"java.sql.Types.INTEGER\"");
    }

    @Test
    public void testObjectIntegerColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='objectInteger']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

    @Test
    public void testPrimitiveShortColumnElementName() throws XPathExpressionException {
        Node primitiveShortColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveShort']");

        Assert.assertNotNull(primitiveShortColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", primitiveShortColumn),
                "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", primitiveShortColumn).getNodeValue(), "primitiveShort",
                "Expected the name attribute to be \"primitiveShort\"");
    }

    @Test
    public void testPrimitiveShortColumnElementType() throws XPathExpressionException {
        Node primitiveShortColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveShort']");

        Assert.assertNotNull(primitiveShortColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", primitiveShortColumn),
                "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", primitiveShortColumn).getNodeValue(), "java.sql.Types.SMALLINT",
                "Expected the type attribute to be \"java.sql.Types.SMALLINT\"");
    }

    @Test
    public void testPrimitiveShortColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveShort']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

    @Test
    public void testObjectShortColumnElementName() throws XPathExpressionException {
        Node objectShortColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
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
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='objectShort']");

        Assert.assertNotNull(objectShortColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", objectShortColumn), "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", objectShortColumn).getNodeValue(), "java.sql.Types.SMALLINT",
                "Expected the type attribute to be \"java.sql.Types.SMALLINT\"");
    }

    @Test
    public void testObjectShortColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='objectShort']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

    @Test
    public void testPrimitiveByteColumnElementName() throws XPathExpressionException {
        Node primitiveByteColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveByte']");

        Assert.assertNotNull(primitiveByteColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", primitiveByteColumn),
                "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", primitiveByteColumn).getNodeValue(), "primitiveByte",
                "Expected the name attribute to be \"primitiveByte\"");
    }

    @Test
    public void testPrimitiveByteColumnElementType() throws XPathExpressionException {
        Node primitiveByteColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveByte']");

        Assert.assertNotNull(primitiveByteColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", primitiveByteColumn),
                "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", primitiveByteColumn).getNodeValue(), "java.sql.Types.TINYINT",
                "Expected the type attribute to be \"java.sql.Types.TINYINT\"");
    }

    @Test
    public void testPrimitiveByteColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveByte']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

    @Test
    public void testObjectByteColumnElementName() throws XPathExpressionException {
        Node objectByteColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
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
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='objectByte']");

        Assert.assertNotNull(objectByteColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", objectByteColumn), "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", objectByteColumn).getNodeValue(), "java.sql.Types.TINYINT",
                "Expected the type attribute to be \"java.sql.Types.TINYINT\"");
    }

    @Test
    public void testObjectByteColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='objectByte']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

    @Test
    public void testPrimitiveBooleanColumnElementName() throws XPathExpressionException {
        Node primitiveBooleanColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveBoolean']");

        Assert.assertNotNull(primitiveBooleanColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("name", primitiveBooleanColumn),
                "Expected the column to have a name attribute.");
        Assert.assertEquals(getAttribute("name", primitiveBooleanColumn).getNodeValue(), "primitiveBoolean",
                "Expected the name attribute to be \"primitiveBoolean\"");
    }

    @Test
    public void testPrimitiveBooleanColumnElementType() throws XPathExpressionException {
        Node primitiveBooleanColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveBoolean']");

        Assert.assertNotNull(primitiveBooleanColumn, "Expected a column to be created.");
        Assert.assertNotNull(getAttribute("type", primitiveBooleanColumn),
                "Expected the column to have a type attribute.");
        Assert.assertEquals(getAttribute("type", primitiveBooleanColumn).getNodeValue(), "java.sql.Types.BOOLEAN",
                "Expected the type attribute to be \"java.sql.Types.BOOLEAN\"");
    }

    @Test
    public void testPrimitiveBooleanColumnConstraints() throws XPathExpressionException {
        Node constraints = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='primitiveBoolean']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

    @Test
    public void testObjectBooleanColumnElementName() throws XPathExpressionException {
        Node objectBooleanColumn = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
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
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
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
                "/changeSet[starts-with(@id, 'createTable_PrimitivePropertyEntity')]" +
                "/createTable[@tableName='PrimitivePropertyEntity']" +
                "/column[@name='objectBoolean']/constraints");

        Assert.assertNull(constraints, "Expected no constraints to be created.");
    }

}
