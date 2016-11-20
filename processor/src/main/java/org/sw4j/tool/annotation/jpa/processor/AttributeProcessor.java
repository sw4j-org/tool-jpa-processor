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
package org.sw4j.tool.annotation.jpa.processor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.lang.model.element.Element;
import org.sw4j.tool.annotation.jpa.generator.model.Attribute;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.sw4j.tool.annotation.jpa.processor.exceptions.AnnotationProcessorException;

/**
 * This is a processor to handle attributes of classes with an @Entity annotation.
 *
 * @author Uwe Plonus
 */
public class AttributeProcessor {

    /**
     * Default constructor for the attribute processor.
     *
     */
    public AttributeProcessor() {
    }

    /**
     * Process a single entity annotated with {@code @Entity}. An attribute may be defined by either a field or a
     * property or both. Which one is used depends on the access of the entity.
     *
     * @param entity the entity this attribute belongs to.
     * @param attributeName the name of the attribute.
     * @param fieldElement the possible field of the attribute.
     * @param propertyElement the possible property of the attribute.
     * @throws AnnotationProcessorException if the entity cannot be handled.
     */
    public void process(@Nonnull final Entity entity, @Nonnull final String attributeName,
            @Nullable final Element fieldElement, @Nullable final Element propertyElement)
            throws AnnotationProcessorException {
        Attribute attribute = new Attribute(attributeName);
        entity.addAttribute(attribute);
    }

}
