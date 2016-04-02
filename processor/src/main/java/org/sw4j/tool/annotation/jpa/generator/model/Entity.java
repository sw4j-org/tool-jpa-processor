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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class represents an entity. Via the entity you can access the attributes and tables assigned
 * to the entity.
 *
 * @author Uwe Plonus
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Entity {

    /** The name of the entity. */
    @XmlAttribute(name = "name")
    private String name;

    /**
     * Default constructor.
     */
    public Entity() {
    }

    /**
     * Returns the name of the entity.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the entity.
     *
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

}
