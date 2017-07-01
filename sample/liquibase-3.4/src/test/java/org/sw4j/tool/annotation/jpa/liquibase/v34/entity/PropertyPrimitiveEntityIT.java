/*
 * Copyright (C) 2017 uwe
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
public class PropertyPrimitiveEntityIT extends ITSuperclass {

    @BeforeClass
    public static void setUpEntities() throws Exception {
        PropertyPrimitiveEntity entity = new PropertyPrimitiveEntity();
        entity.setId(1L);
        getEm().persist(entity);
    }

    @Test
    public void testCreateEntity() {
        PropertyPrimitiveEntity entity = new PropertyPrimitiveEntity();
        entity.setId(10L);
        getEm().persist(entity);
    }

    @Test
    public void testFindEntity() {
        PropertyPrimitiveEntity entity = getEm().find(PropertyPrimitiveEntity.class, 1L);
        Assert.assertNotNull(entity, "Expected an entity to be found.");
        Assert.assertEquals(entity.getId(), 1L, "Expected the entity to be found.");
    }

    @Test
    public void testMappingLongPrimitive() {
        PropertyPrimitiveEntity entity = new PropertyPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(102L);
        getEm().persist(entity);

        PropertyPrimitiveEntity foundEntity = getEm().find(PropertyPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), 102L, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongPrimitiveMaxValue() {
        PropertyPrimitiveEntity entity = new PropertyPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(Long.MAX_VALUE);
        getEm().persist(entity);

        PropertyPrimitiveEntity foundEntity = getEm().find(PropertyPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), Long.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongPrimitiveMinValue() {
        PropertyPrimitiveEntity entity = new PropertyPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(Long.MIN_VALUE);
        getEm().persist(entity);

        PropertyPrimitiveEntity foundEntity = getEm().find(PropertyPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), Long.MIN_VALUE, "Expected the mapped value.");
    }

}
