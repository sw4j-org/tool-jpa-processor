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
public class FieldEntityIT extends ITSuperclass {

    @BeforeClass()
    public static void setUpEntities() throws Exception {
        FieldEntity entity = new FieldEntity();
        entity.setId(1L);
        getEm().persist(entity);
    }

    @Test
    public void testCreateEntity() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        getEm().persist(entity);
    }

    @Test
    public void testFindEntity() {
        FieldEntity entity = getEm().find(FieldEntity.class, 1L);
        Assert.assertNotNull(entity, "Expected an entity to be found.");
        Assert.assertEquals(entity.getId(), 1L, "Expected the entity to be found.");
    }

    @Test
    public void testMappingLongObject() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setLongObject(102L);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getLongObject(), (Long)102L, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongObjectMaxValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setLongObject(Long.MAX_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getLongObject(), (Long)Long.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongObjectMinValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setLongObject(Long.MIN_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getLongObject(), (Long)Long.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongPrimitive() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(102L);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), 102L, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongPrimitiveMaxValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(Long.MAX_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), Long.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongPrimitiveMinValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(Long.MIN_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), Long.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntObject() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setIntObject(102);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getIntObject(), (Integer)102, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntObjectMaxValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setIntObject(Integer.MAX_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getIntObject(), (Integer)Integer.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntObjectMinValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setIntObject(Integer.MIN_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getIntObject(), (Integer)Integer.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntPrimitive() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveInt(102);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveInt(), 102, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntPrimitiveMaxValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveInt(Integer.MAX_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveInt(), Integer.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntPrimitiveMinValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveInt(Integer.MIN_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveInt(), Integer.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingShortObject() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setShortObject((short)102);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getShortObject(), Short.valueOf((short)102), "Expected the mapped value.");
    }

    @Test
    public void testMappingShortObjectMaxValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setShortObject(Short.MAX_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getShortObject(), Short.valueOf(Short.MAX_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingShortObjectMinValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setShortObject(Short.MIN_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getShortObject(), Short.valueOf(Short.MIN_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingShortPrimitive() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveShort((short)102);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveShort(), (short)102, "Expected the mapped value.");
    }

    @Test
    public void testMappingShortPrimitiveMaxValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveShort(Short.MAX_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveShort(), Short.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingShortPrimitiveMinValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveShort(Short.MIN_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveShort(), Short.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingByteObject() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setByteObject((byte)102);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getByteObject(), Byte.valueOf((byte)102), "Expected the mapped value.");
    }

    @Test
    public void testMappingByteObjectMaxValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setByteObject(Byte.MAX_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getByteObject(), Byte.valueOf(Byte.MAX_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingByteObjectMinValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setByteObject(Byte.MIN_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getByteObject(), Byte.valueOf(Byte.MIN_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingBytePrimitive() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveByte((byte)102);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveByte(), (byte)102, "Expected the mapped value.");
    }

    @Test
    public void testMappingBytePrimitiveMaxValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveByte(Byte.MAX_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveByte(), Byte.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingBytePrimitiveMinValue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveByte(Byte.MIN_VALUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveByte(), Byte.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingBooleanObjectFalse() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setBooleanObject(Boolean.FALSE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getBooleanObject(), Boolean.FALSE, "Expected the mapped value.");
    }

    @Test
    public void testMappingBooleanObjectTrue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setBooleanObject(Boolean.TRUE);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getBooleanObject(), Boolean.TRUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingBooleanPrimitiveFalse() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveBoolean(false);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveBoolean(), false, "Expected the mapped value.");
    }

    @Test
    public void testMappingBooleanPrimitiveTrue() {
        FieldEntity entity = new FieldEntity();
        entity.setId(101L);
        entity.setPrimitiveBoolean(true);
        getEm().persist(entity);

        FieldEntity foundEntity = getEm().find(FieldEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveBoolean(), true, "Expected the mapped value.");
    }

}
