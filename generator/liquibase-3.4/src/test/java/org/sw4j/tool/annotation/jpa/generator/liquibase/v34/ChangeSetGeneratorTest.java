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

import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.DatabaseChangeLog;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.DatabaseChangeLog.ChangeSet;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.ObjectFactory;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class ChangeSetGeneratorTest extends AbstractUnitTest {

    private ObjectFactory objectFactory;

    private ChangeSetGenerator unitUnderTest;

    @BeforeMethod
    public void setUp() {
        this.objectFactory = new ObjectFactory();
        this.unitUnderTest = new ChangeSetGenerator();
    }

    @Test
    public void testProcessOnlyEntityModel() throws Exception {
        Entity entity = new Entity("SimpleEntity", "org.sw4j.tool.annotation.jpa.entity.SimpleEntity");
        DatabaseChangeLog databaseChangeLog = objectFactory.createDatabaseChangeLog();

        this.unitUnderTest.handleEntity(databaseChangeLog, entity);

        Assert.assertEquals(databaseChangeLog.getChangeSetOrIncludeOrIncludeAll().size(), 1,
                "Expected the databaseChangeLog to have one element.");
        Assert.assertEquals(databaseChangeLog.getChangeSetOrIncludeOrIncludeAll().get(0).getClass(), ChangeSet.class,
                "Expected the element of the databaseChangeLog to be a changeSet.");
        ChangeSet changeSet = (ChangeSet)databaseChangeLog.getChangeSetOrIncludeOrIncludeAll().get(0);
        Assert.assertEquals(changeSet.getAuthor(), "generated",
                "Expected the author of the changeSet to be \"generated\".");
        Assert.assertEquals(changeSet.getId(), "createTable_SimpleEntity",
                "Expected the id of the changeSet to be \"createTable_SimpleEntity\"");
    }

}
