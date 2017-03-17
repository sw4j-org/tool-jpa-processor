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
import static org.sw4j.tool.annotation.jpa.util.ITSuperclass.getEm;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class DataTypeEntityIT extends ITSuperclass {

    @BeforeClass()
    public static void setUpEntities() throws Exception {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(1L);
        getEm().persist(entity);
    }

    @Test
    public void testCreateEntity() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        getEm().persist(entity);
    }

    @Test
    public void testFindEntity() {
        DataTypeEntity entity = getEm().find(DataTypeEntity.class, 1L);
        Assert.assertNotNull(entity, "Expected the entity to be found.");
        Assert.assertEquals(entity.getId(), 1L, "Expected the entity to be found.");
    }

    @Test
    public void testMappingLongObject() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setLongObject(102L);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getLongObject(), (Long)102L, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongObjectMaxValue() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setLongObject(Long.MAX_VALUE);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getLongObject(), (Long)Long.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongObjectMinValue() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setLongObject(Long.MIN_VALUE);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getLongObject(), (Long)Long.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongPrimitive() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(102L);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), 102L, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongPrimitiveMaxValue() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(Long.MAX_VALUE);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), Long.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongPrimitiveMinValue() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(Long.MIN_VALUE);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), Long.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntObject() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setIntObject(102);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getIntObject(), (Integer)102, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntObjectMaxValue() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setIntObject(Integer.MAX_VALUE);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getIntObject(), (Integer)Integer.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntObjectMinValue() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setIntObject(Integer.MIN_VALUE);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getIntObject(), (Integer)Integer.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntPrimitive() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setPrimitiveInt(102);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveInt(), 102, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntPrimitiveMaxValue() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setPrimitiveInt(Integer.MAX_VALUE);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveInt(), Integer.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntPrimitiveMinValue() {
        DataTypeEntity entity = new DataTypeEntity();
        entity.setId(101L);
        entity.setPrimitiveInt(Integer.MIN_VALUE);
        getEm().persist(entity);

        DataTypeEntity foundEntity = getEm().find(DataTypeEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveInt(), Integer.MIN_VALUE, "Expected the mapped value.");
    }

}
