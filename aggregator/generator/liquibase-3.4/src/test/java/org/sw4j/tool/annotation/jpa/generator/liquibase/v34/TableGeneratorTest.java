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
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.CreateTable;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.DatabaseChangeLog.ChangeSet;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.ObjectFactory;
import org.sw4j.tool.annotation.jpa.generator.model.Attribute;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.sw4j.tool.annotation.jpa.generator.model.Table;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class TableGeneratorTest extends AbstractUnitTest {

    private ObjectFactory objectFactory;

    private TableGenerator unitUnderTest;

    @BeforeMethod
    public void setUp() {
        this.objectFactory = new ObjectFactory();
        this.unitUnderTest = new TableGenerator();
    }

    @Test
    public void testProcessEmptyEntity() throws Exception {
        Entity entity = new Entity("SimpleEntity", "org.sw4j.tool.annotation.jpa.entity.SimpleEntity");
        Table table = new Table("SimpleEntity", "", "", entity);
        entity.addTable(table);
        ChangeSet changeSet = objectFactory.createDatabaseChangeLogChangeSet();

        this.unitUnderTest.handleEntity(changeSet, entity);

        Assert.assertEquals(changeSet.getChangeSetChildren().size(), 1, "Expected the changeSet to have one element.");
        Assert.assertEquals(changeSet.getChangeSetChildren().get(0).getClass(), CreateTable.class,
                "Expected the element of the changeSet to be a createTable.");
        CreateTable createTable = (CreateTable)changeSet.getChangeSetChildren().get(0);
        Assert.assertEquals(createTable.getTableName(), "SimpleEntity",
                "Expected the tableName of the createTable to be \"SimpleEntity\".");
    }

    @Test
    public void testProcessEmptyEntityWithTableName() throws Exception {
        Entity entity = new Entity("SimpleEntity", "org.sw4j.tool.annotation.jpa.entity.SimpleEntity");
        Table table = new Table("TAB_SIMPLE", "", "", entity);
        entity.addTable(table);
        ChangeSet changeSet = objectFactory.createDatabaseChangeLogChangeSet();

        this.unitUnderTest.handleEntity(changeSet, entity);

        Assert.assertEquals(changeSet.getChangeSetChildren().size(), 1, "Expected the changeSet to have one element.");
        Assert.assertEquals(changeSet.getChangeSetChildren().get(0).getClass(), CreateTable.class,
                "Expected the element of the changeSet to be a createTable.");
        CreateTable createTable = (CreateTable)changeSet.getChangeSetChildren().get(0);
        Assert.assertEquals(createTable.getTableName(), "TAB_SIMPLE",
                "Expected the tableName of the createTable to be \"TAB_SIMPLE\".");
    }

    @Test
    public void testProcessEmptyEntityWithCatalogName() throws Exception {
        Entity entity = new Entity("SimpleEntity", "org.sw4j.tool.annotation.jpa.entity.SimpleEntity");
        Table table = new Table("SimpleEntity", "CATALOG", "", entity);
        entity.addTable(table);
        ChangeSet changeSet = objectFactory.createDatabaseChangeLogChangeSet();

        this.unitUnderTest.handleEntity(changeSet, entity);

        Assert.assertEquals(changeSet.getChangeSetChildren().size(), 1, "Expected the changeSet to have one element.");
        Assert.assertEquals(changeSet.getChangeSetChildren().get(0).getClass(), CreateTable.class,
                "Expected the element of the changeSet to be a createTable.");
        CreateTable createTable = (CreateTable)changeSet.getChangeSetChildren().get(0);
        Assert.assertEquals(createTable.getCatalogName(), "CATALOG",
                "Expected the catalogName of the createTable to be \"CATALOG\".");
    }

    @Test
    public void testProcessEmptyEntityWithSchemaName() throws Exception {
        Entity entity = new Entity("SimpleEntity", "org.sw4j.tool.annotation.jpa.entity.SimpleEntity");
        Table table = new Table("SimpleEntity", "", "SCHEMA", entity);
        entity.addTable(table);
        ChangeSet changeSet = objectFactory.createDatabaseChangeLogChangeSet();

        this.unitUnderTest.handleEntity(changeSet, entity);

        Assert.assertEquals(changeSet.getChangeSetChildren().size(), 1, "Expected the changeSet to have one element.");
        Assert.assertEquals(changeSet.getChangeSetChildren().get(0).getClass(), CreateTable.class,
                "Expected the element of the changeSet to be a createTable.");
        CreateTable createTable = (CreateTable)changeSet.getChangeSetChildren().get(0);
        Assert.assertEquals(createTable.getSchemaName(), "SCHEMA",
                "Expected the schemaName of the createTable to be \"SCHEMA\".");
    }

    @Test
    public void testProcessEntityWithAttribute() throws Exception {
        Entity entity = new Entity("SimpleEntity", "org.sw4j.tool.annotation.jpa.entity.SimpleEntity");
        Table table = new Table("SimpleEntity", "", "", entity);
        entity.addTable(table);
        Attribute attribute = new Attribute("attr", false, "int");
        entity.addAttribute(attribute);
        ChangeSet changeSet = objectFactory.createDatabaseChangeLogChangeSet();

        this.unitUnderTest.handleEntity(changeSet, entity);

        Assert.assertEquals(changeSet.getChangeSetChildren().size(), 1, "Expected the changeSet to have one element.");
        Assert.assertEquals(changeSet.getChangeSetChildren().get(0).getClass(), CreateTable.class,
                "Expected the element of the changeSet to be a createTable.");
        CreateTable createTable = (CreateTable)changeSet.getChangeSetChildren().get(0);
        Assert.assertEquals(createTable.getColumnOrAny().size(), 1,
                "Expected the createTable element to contain a single column.");
        Assert.assertEquals(createTable.getColumnOrAny().get(0).getClass(), Column.class,
                "Expected the column to be of class Column.");
    }

}
