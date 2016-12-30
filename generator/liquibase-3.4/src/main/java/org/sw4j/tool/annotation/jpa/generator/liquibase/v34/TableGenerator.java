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
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.CreateTable;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.DatabaseChangeLog.ChangeSet;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.ObjectFactory;
import org.sw4j.tool.annotation.jpa.generator.model.Attribute;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;

/**
 * This is a generator that handles entities and creates the tables needed for it.
 *
 * @author Uwe Plonus
 */
public class TableGenerator {

    /** The object factory used to create the elements. */
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    /** The column generator used to create the column (and subsequent) object. */
    private static final ColumnGenerator COLUMN_GENERATOR = new ColumnGenerator();

    /**
     * The default constructor.
     */
    public TableGenerator() {
    }

    /**
     * Handle a single entity and append a changeSet to the databaseChangeLog to create the table for the entity.
     *
     * @param changeSet the changelog the changeset should be appended to.
     * @param entity the entity to process.
     */
    public void handleEntity(@Nonnull final ChangeSet changeSet, @Nonnull final Entity entity) {
        String entityName = entity.getName();
        CreateTable createTable = OBJECT_FACTORY.createCreateTable();
        createTable.setTableName(entityName);
        changeSet.getChangeSetChildren().add(createTable);
        for (Attribute attribute: entity.getAttributes()) {
            COLUMN_GENERATOR.handleAttribute(createTable, attribute);
        }
    }

}
