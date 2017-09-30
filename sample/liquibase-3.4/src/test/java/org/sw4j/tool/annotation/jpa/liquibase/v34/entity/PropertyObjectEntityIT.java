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
public class PropertyObjectEntityIT extends ITSuperclass {

    @BeforeClass
    public static void setUpEntities() throws Exception {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(1L);
        getEm().persist(entity);
    }

    @Test
    public void testCreateEntity() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(10L);
        getEm().persist(entity);
    }

    @Test
    public void testFindEntity() {
        PropertyObjectEntity entity = getEm().find(PropertyObjectEntity.class, 1L);
        Assert.assertNotNull(entity, "Expected an entity to be found.");
        Assert.assertEquals(entity.getId(), 1L, "Expected the entity to be found.");
    }

    @Test
    public void testMappingLongObject() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectLong(102L);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectLong(), Long.valueOf(102L), "Expected the mapped value.");
    }

    @Test
    public void testMappingLongObjectNull() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectLong(null);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertNull(foundEntity.getObjectLong(), "Expected the mapped value.");
    }

    @Test
    public void testMappingLongObjectMaxValue() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectLong(Long.MAX_VALUE);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectLong(), Long.valueOf(Long.MAX_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingLongObjectMinValue() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectLong(Long.MIN_VALUE);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found");
        Assert.assertEquals(foundEntity.getObjectLong(), Long.valueOf(Long.MIN_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingIntObject() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectInt(102);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectInt(), Integer.valueOf(102), "Expected the mapped value.");
    }

    @Test
    public void testMappingIntObjectNull() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectInt(null);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertNull(foundEntity.getObjectInt(), "Expected the mapped value.");
    }

    @Test
    public void testMappingIntObjectMaxValue() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectInt(Integer.MAX_VALUE);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectInt(), Integer.valueOf(Integer.MAX_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingIntObjectMinValue() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectInt(Integer.MIN_VALUE);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectInt(), Integer.valueOf(Integer.MIN_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingShortObject() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectShort((short)102);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectShort(), Short.valueOf((short)102), "Expected the mapped value.");
    }

    @Test
    public void testMappingShortObjectNull() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectShort(null);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertNull(foundEntity.getObjectShort(), "Expected the mapped value.");
    }

    @Test
    public void testMappingShortObjectMaxValue() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectShort(Short.MAX_VALUE);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectShort(), Short.valueOf(Short.MAX_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingShortObjectMinValue() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectShort(Short.MIN_VALUE);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectShort(), Short.valueOf(Short.MIN_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingByteObject() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectByte((byte)102);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectByte(), Byte.valueOf((byte)102), "Expected the mapped value.");
    }

    @Test
    public void testMappingByteObjectNull() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectByte(null);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertNull(foundEntity.getObjectByte(), "Expected the mapped value.");
    }

    @Test
    public void testMappingByteObjectMaxValue() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectByte(Byte.MAX_VALUE);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectByte(), Byte.valueOf(Byte.MAX_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingByteObjectMinValue() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectByte(Byte.MIN_VALUE);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectByte(), Byte.valueOf(Byte.MIN_VALUE), "Expected the mapped value.");
    }

    @Test
    public void testMappingBooleanObjectNull() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectBoolean(null);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertNull(foundEntity.getObjectBoolean(), "Expected the mapped value.");
    }

    @Test
    public void testMappingBooleanObjectTrue() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectBoolean(Boolean.TRUE);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectBoolean(), Boolean.TRUE, "Expected the mapped value.");
    }

    @Test
    public void testMappingBooleanObjectFalse() {
        PropertyObjectEntity entity = new PropertyObjectEntity();
        entity.setId(101L);
        entity.setObjectBoolean(Boolean.FALSE);
        getEm().persist(entity);

        PropertyObjectEntity foundEntity = getEm().find(PropertyObjectEntity.class, 101L);
        Assert.assertNotNull(foundEntity, "Expected an entity to be found.");
        Assert.assertEquals(foundEntity.getObjectBoolean(), Boolean.FALSE, "Expected the mapped value.");
    }

}
