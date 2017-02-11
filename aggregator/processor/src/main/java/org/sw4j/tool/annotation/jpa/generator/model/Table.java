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
package org.sw4j.tool.annotation.jpa.generator.model;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class represents a table. The table is assigned to an entity and you can access the columns assigned to this
 * table.
 *
 * @author Uwe Plonus
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Table {

    /** The name of the table. */
    private final String name;

    /** The catalog of the table. */
    private final String catalog;

    /** The schema of the table. */
    private final String schema;

    /** The entity this table belongs to. */
    private final Entity entity;

    /**
     * Constructor for a table.
     *
     * @param name the name of the table.
     * @param catalog the catalog of the table.
     * @param schema the schema of the table.
     * @param entity the entity the table belongs to.
     */
    public Table(@Nonnull final String name, @Nonnull final String catalog, @Nonnull final String schema,
            @Nonnull final Entity entity) {
        this.name = name;
        this.catalog = catalog;
        this.schema = schema;
        this.entity = entity;
    }

    /**
     * Returns the name of the table.
     *
     * @return the name.
     */
    @Nonnull
    @XmlAttribute(name = "name", required = true)
    public String getName() {
        return name;
    }

    /**
     * Returns the catalog of the table.
     *
     * @return the catalog.
     */
    @Nonnull
    @XmlAttribute(name = "catalog", required = false)
    public String getCatalog() {
        return catalog;
    }

    /**
     * Returns the schema of the table.
     *
     * @return the schema.
     */
    @Nonnull
    @XmlAttribute(name = "schema", required = false)
    public String getSchema() {
        return schema;
    }

    /**
     * Returns the entity of the table.
     *
     * @return the entity.
     */
    @Nonnull
    public Entity getEntity() {
        return entity;
    }

}
