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
 * This class represents an attribute. Via the attribute you can access the columns assigned to the attribute.
 *
 * @author Uwe Plonus
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Attribute {

    /** The name of the attribute. */
    @XmlAttribute(name = "name")
    private final String name;

    /** Flag to indicate this attribute as Id. */
    @XmlAttribute(name = "isId")
    private final boolean isId;

    /** The Java datatype of this attribute. */
    @XmlAttribute(name = "dataType")
    private final String dataType;

    /**
     * Constructor for an attribute.
     *
     * @param name the name of the attribute.
     * @param isId if this attribute should be marked as Id.
     */
    public Attribute(@Nonnull final String name, final boolean isId, @Nonnull final String dataType) {
        this.name = name;
        this.isId = isId;
        this.dataType = dataType;
    }

    /**
     * Returns the name of the attribute.
     *
     * @return the name.
     */
    @Nonnull
    public String getName() {
        return this.name;
    }

    /**
     * Returns if this attribute is an Id.
     *
     * @return {@code true} if this attribute is an Id.
     */
    public boolean isId() {
        return this.isId;
    }

    /**
     * Returns the java data type of this attribute.
     *
     * @return the data type of this attribute.
     */
    public String getDataType() {
        return this.dataType;
    }

}
