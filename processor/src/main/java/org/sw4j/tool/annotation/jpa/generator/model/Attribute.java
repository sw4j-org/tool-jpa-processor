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

import java.util.LinkedList;
import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class represents an attribute. Via the attribute you can access the columns assigned to the attribute.
 *
 * @author Uwe Plonus
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Attribute {

    /** The name of the attribute. */
    @XmlAttribute(name = "name")
    private final String name;

    /**
     * Constructor for an attribute.
     *
     * @param name the name of the attribute.
     */
    public Attribute(@Nonnull final String name) {
        this.name = name;
    }

    /**
     * Returns the name of the attribute.
     *
     * @return the name.
     */
    @Nonnull
    public String getName() {
        return name;
    }

}
