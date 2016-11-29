/*
 * Copyright (C) 2016 Uwe Plonus
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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This is a model that contains all elements needed to create a schema for JPA entities.
 *
 * @author Uwe Plonus
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "model")
@XmlType(name = "",
        propOrder = {
            "entities",
        })
public class Model {

    /** The entities of the model. */
    @XmlElement(name = "entity")
    private final List<Entity> entities;

    /**
     * Default constructor.
     */
    public Model() {
        entities = new LinkedList<>();
    }

    /**
     * Adds an entity to the model.
     *
     * @param entity the entity to add.
     */
    public void addEntity(@Nonnull final Entity entity) {
        entities.add(entity);
    }

    /**
     * Returns all entities of the model.
     *
     * @return a list containing all elements of the model.
     */
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

}
