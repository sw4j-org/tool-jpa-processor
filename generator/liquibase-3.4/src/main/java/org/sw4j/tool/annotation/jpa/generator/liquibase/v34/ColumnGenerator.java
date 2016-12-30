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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.sw4j.tool.annotation.jpa.generator.liquibase.common.DataTypeUtils;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.Column;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.Constraints;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.CreateTable;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.ObjectFactory;
import org.sw4j.tool.annotation.jpa.generator.model.Attribute;

/**
 * This is a generator that handles attributes and creates the columns needed for it.
 *
 * @author Uwe Plonus
 */
public class ColumnGenerator {

    /** The object factory used to create the elements. */
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    /**
     * The default constructor.
     */
    public ColumnGenerator() {
    }

    /**
     * Handle a single entity and append a changeSet to the databaseChangeLog to create the table for the entity.
     *
     * @param createTable the create table statement the column should be appended to.
     * @param attribute the ttribute to process.
     */
    public void handleAttribute(@Nonnull final CreateTable createTable, @Nonnull final Attribute attribute) {
        Column column = OBJECT_FACTORY.createColumn();
        column.setName(attribute.getName());
        column.setType(DataTypeUtils.createSqlDataType(attribute.getDataType()));
        Constraints constraints = null;
        if (attribute.isId()) {
            constraints = createConstraints(constraints);
            constraints.setPrimaryKey(Boolean.TRUE.toString());
        }
        if (constraints != null) {
            column.getContent().add(constraints);
        }
        createTable.getColumnOrAny().add(column);
    }

    /**
     * Creates a new constraints if the given constraints is {@code null} else returns the given constraints.
     *
     * @param constraints the constraints to check.
     * @return either a new constraints or the given constraints.
     */
    private Constraints createConstraints(@Nullable final Constraints constraints) {
        if (constraints == null) {
            return OBJECT_FACTORY.createConstraints();
        } else {
            return constraints;
        }
    }

}
