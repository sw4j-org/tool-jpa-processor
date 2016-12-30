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
package org.sw4j.tool.annotation.jpa.generator.liquibase.v34;

import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.Column;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.Constraints;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.CreateTable;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.DatabaseChangeLog;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.ObjectFactory;
import org.sw4j.tool.annotation.jpa.generator.model.Attribute;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class ColumnGeneratorTest {

    private ObjectFactory objectFactory;

    private ColumnGenerator unitUnderTest;

    @BeforeMethod
    public void setUp() {
        this.objectFactory = new ObjectFactory();
        this.unitUnderTest = new ColumnGenerator();
    }

    @Test
    public void testProcessAttribute() throws Exception {
        Attribute attribute = new Attribute("attr", false, "int");

        CreateTable createTable = objectFactory.createCreateTable();

        this.unitUnderTest.handleAttribute(createTable, attribute);

        Assert.assertEquals(createTable.getColumnOrAny().size(), 1,
                "Expected the createTable object to contain a single object.");
        Assert.assertEquals(createTable.getColumnOrAny().get(0).getClass(), Column.class,
                "Expected the containted object to be a column.");
        Column column = (Column)createTable.getColumnOrAny().get(0);
        Assert.assertEquals(column.getName(), "attr", "Expected the column to be named \"attr\"");
        Assert.assertEquals(column.getType(), "java.sql.Types.INTEGER",
                "Expected the column to be of type \"java.sql.Types.INTEGER\"");
        Assert.assertEquals(column.getContent().size(), 0, "Expected the column to contain no nested elements.");
    }

    @Test
    public void testProcessAttributeIsId() throws Exception {
        Attribute attribute = new Attribute("attr", true, "int");

        CreateTable createTable = objectFactory.createCreateTable();

        this.unitUnderTest.handleAttribute(createTable, attribute);

        Assert.assertEquals(createTable.getColumnOrAny().size(), 1,
                "Expected the createTable object to contain a single object.");
        Assert.assertEquals(createTable.getColumnOrAny().get(0).getClass(), Column.class,
                "Expected the containted object to be a column.");
        Column column = (Column)createTable.getColumnOrAny().get(0);
        Assert.assertEquals(column.getName(), "attr", "Expected the column to be named \"attr\"");
        Assert.assertEquals(column.getType(), "java.sql.Types.INTEGER",
                "Expected the column to be of type \"java.sql.Types.INTEGER\"");
        Assert.assertEquals(column.getContent().size(), 1, "Expected the column to contain no nested elements.");
        Assert.assertEquals(column.getContent().get(0).getClass(), Constraints.class,
                "Expected the content of the column to be a constraints object.");
        Constraints constraints = (Constraints)column.getContent().get(0);
        Assert.assertEquals(constraints.getPrimaryKey(), "true",
                "Expected the constraints object to contain an attribute primaryKey of value \"true\".");
    }

}
