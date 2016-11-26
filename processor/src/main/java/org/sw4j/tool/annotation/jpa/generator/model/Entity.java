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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * This class represents an entity. Via the entity you can access the attributes and tables assigned to the entity.
 *
 * @author Uwe Plonus
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "",
        propOrder = {
            "attributes",
        })
public class Entity {

    /** The name of the entity. */
    @XmlAttribute(name = "name", required = true)
    private final String name;

    /** The class name of the entity. */
    @XmlAttribute(name = "className", required = true)
    private final String className;

    /** The entities of the model. */
    @XmlElementWrapper(name = "attributes", required = false)
    @XmlElement(name = "attribute")
    private List<Attribute> attributes;

    /**
     * Constructor for an entity.
     *
     * @param name the name of the entity.
     * @param className the class name of the entity.
     */
    public Entity(@Nonnull final String name, @Nonnull final String className) {
        this.name = name;
        this.className = className;
    }

    /**
     * Returns the name of the entity.
     *
     * @return the name.
     */
    @Nonnull
    public String getName() {
        return this.name;
    }

    /**
     * Returns the class name of the entity.
     *
     * @return the class name.
     */
    @Nonnull
    public String getClassName() {
        return this.className;
    }

    /**
     * Adds an attribute to the entity.
     *
     * @param attribute the attribute to add.
     */
    public synchronized void addAttribute(@Nonnull final Attribute attribute) {
        if (this.attributes == null) {
            this.attributes = new LinkedList<>();
        }
        this.attributes.add(attribute);
    }

    /**
     * Returns all attributes of the entity.
     *
     * @return a list containing all attributes of the entity.
     */
    @Nonnull
    public synchronized List<Attribute> getAttributes() {
        return this.attributes == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(this.attributes);
    }

}
