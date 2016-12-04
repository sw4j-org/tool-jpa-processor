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
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.DatabaseChangeLog;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.DatabaseChangeLog.ChangeSet;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.ObjectFactory;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;

/**
 * This is the changeSet generator. It created a changeSet inside a databaseChangeLog for a given entity.
 *
 * @author Uwe Plonus
 */
public class ChangesetGenerator {

    /** The object factory used to create the elements. */
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    /**
     * The default constructor.
     */
    public ChangesetGenerator() {
    }

    /**
     * Handle a single entity and append a changeSet to the databaseChangeLog to create the table for the entity.
     *
     * @param changelog the changelog the changeset should be appended to.
     * @param entity the entity to process.
     */
    public void handleEntity(@Nonnull final DatabaseChangeLog changelog, @Nonnull final Entity entity) {
        String entityName = entity.getName();
        ChangeSet changeSet = OBJECT_FACTORY.createDatabaseChangeLogChangeSet();
        changeSet.setAuthor("generated");
        changeSet.setId(new StringBuilder("createTable_").append(entityName).toString());
        changelog.getChangeSetOrIncludeOrIncludeAll().add(changeSet);
    }

}
