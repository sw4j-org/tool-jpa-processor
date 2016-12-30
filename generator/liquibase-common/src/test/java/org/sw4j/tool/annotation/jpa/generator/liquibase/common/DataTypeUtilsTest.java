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
package org.sw4j.tool.annotation.jpa.generator.liquibase.common;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class DataTypeUtilsTest {

    @Test
    public void testPrimitiveBoolean() {
        Assert.assertEquals(DataTypeUtils.createSqlDataType("boolean"), "java.sql.Types.BOOLEAN");
    }

    @Test
    public void testObjectBoolean() {
        Assert.assertEquals(DataTypeUtils.createSqlDataType("java.lang.Boolean"), "java.sql.Types.BOOLEAN");
    }

    @Test
    public void testPrimitiveByte() {
        Assert.assertEquals(DataTypeUtils.createSqlDataType("byte"), "java.sql.Types.TINYINT");
    }

    @Test
    public void testObjectByte() {
        Assert.assertEquals(DataTypeUtils.createSqlDataType("java.lang.Byte"), "java.sql.Types.TINYINT");
    }

    @Test
    public void testPrimitiveShort() {
        Assert.assertEquals(DataTypeUtils.createSqlDataType("short"), "java.sql.Types.SMALLINT");
    }

    @Test
    public void testObjectShort() {
        Assert.assertEquals(DataTypeUtils.createSqlDataType("java.lang.Short"), "java.sql.Types.SMALLINT");
    }

    @Test
    public void testPrimitiveInt() {
        Assert.assertEquals(DataTypeUtils.createSqlDataType("int"), "java.sql.Types.INTEGER");
    }

    @Test
    public void testObjectInteger() {
        Assert.assertEquals(DataTypeUtils.createSqlDataType("java.lang.Integer"), "java.sql.Types.INTEGER");
    }

    @Test
    public void testPrimitiveLong() {
        Assert.assertEquals(DataTypeUtils.createSqlDataType("long"), "java.sql.Types.BIGINT");
    }

    @Test
    public void testObjectLong() {
        Assert.assertEquals(DataTypeUtils.createSqlDataType("java.lang.Long"), "java.sql.Types.BIGINT");
    }

    @Test
    public void testObject() {
        Assert.assertEquals(DataTypeUtils.createSqlDataType("java.lang.Object"), "java.sql.Types.BINARY");
    }

}
