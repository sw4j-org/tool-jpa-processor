/*
 * Copyright (C) 2017 Uwe Plonus
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
package org.sw4j.tool.annotation.jpa.liquibase.v34.entity;

import org.sw4j.tool.annotation.jpa.util.ITSuperclass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class TableEntityIT extends ITSuperclass {

    @BeforeClass()
    public static void setUpEntities() throws Exception {
        TableEntity entity = new TableEntity();
        entity.setId(1);
        getEm().persist(entity);
    }

    @Test
    public void testTableEntity() {
        TableEntity entity = new TableEntity();
        entity.setId(100);
        getEm().persist(entity);
    }

    @Test
    public void testFindEntity() {
        TableEntity entity = getEm().find(TableEntity.class, 1);
        Assert.assertNotNull(entity, "Expected the entity to be found.");
        Assert.assertEquals(entity.getId(), 1, "Expected the entity to be found.");
    }

}
