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
package org.sw4j.tool.annotation.jpa.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.sw4j.tool.annotation.jpa.generator.model.Model;
import org.sw4j.tool.annotation.jpa.processor.exceptions.AnnotationProcessorException;
import org.sw4j.tool.annotation.jpa.processor.exceptions.EntityNotTopLevelClassException;
import org.sw4j.tool.annotation.jpa.processor.exceptions.MissingEntityAnnotationException;

/**
 * This is a processor to handle classes with an @Entity annotation.
 *
 * @author Uwe Plonus
 */
public class EntityProcessor {

    /**
     * The processor to handle attributes of the processed entity.
     */
    private final AttributeProcessor attributeProcessor;


    /**
     * Default constructor for the entity processor.
     *
     */
    public EntityProcessor() {
        this.attributeProcessor = new AttributeProcessor();
    }

    /**
     * Process a single entity annotated with {@code @Entity}.
     *
     * @param element the element to process (must be an {@code @Entity}.
     * @param model the model where the final entity is added to.
     * @throws MissingEntityAnnotationException if the given element is not annotated with {@code @Entity}.
     * @throws EntityNotTopLevelClassException if the given element is not a top level class but another type or not top
     *  level (e.g. embedded).
     * @throws AnnotationProcessorException if the entity cannot be handled.
     */
    public void process(@Nonnull final Element element, @Nonnull final Model model)
            throws AnnotationProcessorException {
        javax.persistence.Entity entityAnnotation = element.getAnnotation(javax.persistence.Entity.class);
        if (entityAnnotation == null) {
            throw new MissingEntityAnnotationException(element.getSimpleName().toString());
        }

        if (ElementKind.CLASS.equals(element.getKind()) &&
                ElementKind.PACKAGE.equals(element.getEnclosingElement().getKind())) {
            // This is a top level class therefore we can continue.
            Entity entity;
            if ("".equals(entityAnnotation.name())) {
                entity = new Entity(element.getSimpleName().toString());
            } else {
                entity = new Entity(entityAnnotation.name());
            }
            model.addEntity(entity);

            Map<String, Element> possibleFields = new HashMap<>();
            Map<String, Element> possibleProperties = new HashMap<>();

            List<? extends Element> enclosedElements = element.getEnclosedElements();
            for (Element enclosedElement: enclosedElements) {
                if (ElementKind.FIELD.equals(enclosedElement.getKind())) {
                    possibleFields.put(enclosedElement.getSimpleName().toString(), enclosedElement);
                } else if (ElementKind.METHOD.equals(enclosedElement.getKind())) {
                    String methodName = enclosedElement.getSimpleName().toString();
                    if (methodName.startsWith("get")) {
                        StringBuilder propertyName = new StringBuilder(methodName.substring(3));
                        propertyName.replace(0, 1, propertyName.substring(0, 1).toLowerCase(Locale.ROOT));
                        possibleProperties.put(propertyName.toString(), enclosedElement);
                    }
                }
            }
            Set<String> handledAttributes = new HashSet<>();
            for (Map.Entry<String, Element> possibleField: possibleFields.entrySet()) {
                handledAttributes.add(possibleField.getKey());
                attributeProcessor.process(entity, possibleField.getKey(), possibleField.getValue(),
                        possibleProperties.get(possibleField.getKey()));
            }
        } else {
            throw new EntityNotTopLevelClassException(element.getSimpleName().toString());
        }
    }

}
