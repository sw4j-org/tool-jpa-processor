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
public class FieldPrimitiveEntityIT extends ITSuperclass {

    @BeforeClass()
    public static void setUpEntities() throws Exception {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(1L);
        getEm().persist(entity);
    }

    @Test
    public void testCreateEntity() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        getEm().persist(entity);
    }

    @Test
    public void testFindEntity() {
        FieldPrimitiveEntity entity = getEm().find(FieldPrimitiveEntity.class, 1L);
        Assert.assertNotNull(entity, "Expected an entity to be found.");
        Assert.assertEquals(entity.getId(), 1L, "Expected the entity to be found.");
    }

    @Test
    public void testMappingLongPrimitive() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(102L);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), 102L, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongPrimitiveMaxValue() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(Long.MAX_VALUE);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), Long.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingLongPrimitiveMinValue() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveLong(Long.MIN_VALUE);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveLong(), Long.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntPrimitive() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveInt(102);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveInt(), 102, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntPrimitiveMaxValue() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveInt(Integer.MAX_VALUE);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveInt(), Integer.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingIntPrimitiveMinValue() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveInt(Integer.MIN_VALUE);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveInt(), Integer.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingShortPrimitive() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveShort((short)102);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveShort(), (short)102, "Expected the mapped value.");
    }

    @Test
    public void testMappingShortPrimitiveMaxValue() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveShort(Short.MAX_VALUE);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveShort(), Short.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingShortPrimitiveMinValue() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveShort(Short.MIN_VALUE);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveShort(), Short.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingBytePrimitive() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveByte((byte)102);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveByte(), (byte)102, "Expected the mapped value.");
    }

    @Test
    public void testMappingBytePrimitiveMaxValue() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveByte(Byte.MAX_VALUE);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveByte(), Byte.MAX_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingBytePrimitiveMinValue() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveByte(Byte.MIN_VALUE);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveByte(), Byte.MIN_VALUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingBooleanPrimitiveFalse() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveBoolean(false);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveBoolean(), false, "Expected the mapped value.");
    }

    @Test
    public void testMappingBooleanPrimitiveTrue() {
        FieldPrimitiveEntity entity = new FieldPrimitiveEntity();
        entity.setId(101L);
        entity.setPrimitiveBoolean(true);
        getEm().persist(entity);

        FieldPrimitiveEntity foundEntity = getEm().find(FieldPrimitiveEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected the entity to be found.");
        Assert.assertEquals(foundEntity.getPrimitiveBoolean(), true, "Expected the mapped value.");
    }

}
