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

import javax.annotation.Nonnull;

/**
 * This class contains utility methods to convert the datatypes from Java to SQL.
 *
 * @author Uwe Plonus
 */
public final class DataTypeUtils {

    /**
     * Hide the default constructor of this utility class.
     */
    private DataTypeUtils() { }

    /**
     * Create the java.sql.Types type for the given Java datatype.
     *
     * @param javaDataType the Java datatype to convert.
     * @return the created java.sql.Types datatype created.
     */
    public static String createSqlDataType(@Nonnull final String javaDataType) {
        if ("boolean".equals(javaDataType) || "java.lang.Boolean".equals(javaDataType)) {
            return "java.sql.Types.BOOLEAN";
        } else if ("byte".equals(javaDataType) || "java.lang.Byte".equals(javaDataType)) {
            return "java.sql.Types.TINYINT";
        } else if ("short".equals(javaDataType) || "java.lang.Short".equals(javaDataType)) {
            return "java.sql.Types.SMALLINT";
        } else if ("int".equals(javaDataType) || "java.lang.Integer".equals(javaDataType)) {
            return "java.sql.Types.INTEGER";
        } else if ("long".equals(javaDataType) || "java.lang.Long".equals(javaDataType)) {
            return "java.sql.Types.BIGINT";
        } else {
            return "java.sql.Types.BINARY";
        }
    }

}
