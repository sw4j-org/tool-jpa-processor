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
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.tools.Diagnostic;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.sw4j.tool.annotation.jpa.generator.model.Model;

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

    /** The processing environment used to access the tool facilities. */
    private ProcessingEnvironment processingEnv;

    /**
     * Default constructor for the entity processor.
     *
     */
    public EntityProcessor() {
        this.attributeProcessor = new AttributeProcessor();
    }

    /**
     * Initializes the processor with the processing environment.
     *
     * @param processingEnv environment to access facilities the tool framework provides to the processor.
     */
    public void init(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        this.attributeProcessor.init(this.processingEnv);
    }

    public void process(@Nonnull final Set<? extends Element> elements, @Nonnull final Model model) {
        for (Element element: elements) {
            this.process(element, model);
        }
    }

    /**
     * Process a single entity annotated with {@code @Entity}.
     *
     * @param element the element to process (must be an {@code @Entity}.
     * @param model the model where the final entity is added to.
     */
    public void process(@Nonnull final Element element, @Nonnull final Model model) {
        javax.persistence.Entity entityAnnotation = element.getAnnotation(javax.persistence.Entity.class);
        if (entityAnnotation == null) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
                    new StringBuilder("The processed class \"").append(element.getSimpleName())
                            .append("\" is not an entity.").toString(), element);
        } else if (ElementKind.CLASS.equals(element.getKind()) &&
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
                if (this.attributeProcessor.isField(enclosedElement)) {
                    possibleFields.put(enclosedElement.getSimpleName().toString(), enclosedElement);
                } else if (this.attributeProcessor.isProperty(enclosedElement)) {
                    possibleProperties.put(
                            this.attributeProcessor.getPropertyFromMethod(enclosedElement), enclosedElement);
                }
            }
            Set<String> handledAttributes = new HashSet<>();
            for (Map.Entry<String, Element> possibleField: possibleFields.entrySet()) {
                handledAttributes.add(possibleField.getKey());
                this.attributeProcessor.process(entity, possibleField.getKey(), possibleField.getValue(),
                        possibleProperties.get(possibleField.getKey()));
            }
            for (Map.Entry<String, Element> possibleProperty: possibleProperties.entrySet()) {
                if (!handledAttributes.contains(possibleProperty.getKey())) {
                    handledAttributes.add(possibleProperty.getKey());
                    this.attributeProcessor.process(entity, possibleProperty.getKey(), null,
                            possibleProperty.getValue());
                }
            }
        } else {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
                    new StringBuilder("The processed entity \"").append(element.getSimpleName())
                            .append("\" is no top level class."), element);
        }
    }

}
