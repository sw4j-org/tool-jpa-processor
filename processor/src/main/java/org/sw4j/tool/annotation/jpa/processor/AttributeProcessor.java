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

import java.beans.Introspector;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.persistence.AccessType;
import javax.persistence.Id;
import javax.tools.Diagnostic;
import org.sw4j.tool.annotation.jpa.generator.model.Attribute;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;

/**
 * This is a processor to handle attributes of classes with an @Entity annotation.
 *
 * @author Uwe Plonus
 */
public class AttributeProcessor {

    /** The prefix of a generic property. */
    private static final String PROPERTY_PREFIX = "get";

    /** The length of the prefix of a generic property. */
    private static final int PROPERTY_PREFIX_LENGTH = "get".length();

    /** The prefix of a boolean property. */
    private static final String BOOLEAN_PROPERTY_PREFIX = "is";

    /** The length of the prefix of a boolean property. */
    private static final int BOOLEAN_PROPERTY_PREFIX_LENGTH = "is".length();

    /** The processing environment used to access the tool facilities. */
    private ProcessingEnvironment processingEnv;

    /**
     * Default constructor for the attribute processor.
     *
     */
    public AttributeProcessor() {
    }

    /**
     * Initializes the processor with the processing environment.
     *
     * @param processingEnv environment to access facilities the tool framework provides to the processor.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void init(@Nonnull final ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    /**
     * Process all possible attributes of an {@code Entity} class.
     *
     * @param entity the entity this attributes belongs to.
     * @param possibleAttributes all enclosed elements of the element that denotes the {@code Entity}.
     */
    public void process(@Nonnull final Entity entity, @Nonnull final List<? extends Element> possibleAttributes) {
        AccessType accessType = null;
        Map<String, Element> possibleFields = new LinkedHashMap<>();
        Map<String, Element> possibleProperties = new LinkedHashMap<>();
        Map<String, Element> possibleIds = new LinkedHashMap<>();
        for (Element possibleAttribute: possibleAttributes) {
            String attributeName = null;
            if (isField(possibleAttribute)) {
                attributeName = possibleAttribute.getSimpleName().toString();
                possibleFields.put(attributeName, possibleAttribute);
            } else if (isProperty(possibleAttribute)) {
                attributeName = getAttributeNameFromProperty(possibleAttribute);
                possibleProperties.put(attributeName, possibleAttribute);
            }
            if (attributeName != null && isPossibleIdAttribute(possibleAttribute)) {
                possibleIds.put(attributeName, possibleAttribute);
            }
        }

        if (possibleIds.size() == 1) {
            Map.Entry<String, Element> id = possibleIds.entrySet().iterator().next();
            if (isField(id.getValue())) {
                accessType = AccessType.FIELD;
            } else if (isProperty(id.getValue())) {
                accessType = AccessType.PROPERTY;
            }
            if (accessType == AccessType.FIELD) {
                for (Map.Entry<String, Element> possibleField: possibleFields.entrySet()) {
                    processField(entity, possibleField.getValue());
                }
            } else {
                for (Map.Entry<String, Element> possibleProperty: possibleProperties.entrySet()) {
                    processProperty(entity, possibleProperty.getValue());
                }
            }
        } else {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, new StringBuilder(
                    "This annotation processor does not support entities with multiple @Id annotations. The entity \"")
                    .append(entity.getName())
                    .append("\" (with class name \"")
                    .append(entity.getClassName())
                    .append("\") has more than 1 @Id attribute."));
        }
    }

    /**
     * Process a single field attribute.
     *
     * @param entity the entity this attribute belongs to.
     * @param fieldElement the field of the attribute.
     */
    private void processField(@Nonnull final Entity entity, @Nonnull final Element fieldElement) {
        Attribute attribute = new Attribute(fieldElement.getSimpleName().toString(),
                isPossibleIdAttribute(fieldElement));
        entity.addAttribute(attribute);
    }

    /**
     * Process a single property attribute.
     *
     * @param entity the entity this attribute belongs to.
     * @param propertyElement the property of the attribute.
     */
    private void processProperty(@Nonnull final Entity entity, @Nonnull final Element propertyElement) {
        Attribute attribute = new Attribute(getAttributeNameFromProperty(propertyElement),
                isPossibleIdAttribute(propertyElement));
        entity.addAttribute(attribute);
    }

    /**
     * Test if the given field or property is a possible {@code @Id}.
     *
     * @param element the element to check.
     * @return {@code true} if either the fieldElement or the propertyElement denote an {@code @Id}.
     */
    private boolean isPossibleIdAttribute(@Nonnull final Element element) {
        Id idAnnotation = element.getAnnotation(Id.class);
        return idAnnotation != null;
    }

    /**
     * Checks if the given element is a field.
     *
     * @param element the element to check.
     * @return {@code true} if the element is a field.
     */
    public boolean isField(@Nonnull final Element element) {
        return ElementKind.FIELD.equals(element.getKind());
    }

    /**
     * Checks if the given element is a property. This method only checks for the getter methods.
     *
     * @param element the element to check.
     * @return {@code true} if the element is the getter of a property.
     */
    private boolean isProperty(@Nonnull final Element element) {
        boolean isProperty = false;
        if (ElementKind.METHOD.equals(element.getKind())) {
            isProperty = !"".equals(getAttributeNameFromProperty(element));
        }
        return isProperty;
    }

    /**
     * Returns the attribute name from the method name of the element. This method assumes that the given element is a
     * method. If the method is not a valid property (by either starting with "get" or by starting with "is" and being
     * a boolean property) then an empty string is returned.
     *
     * @param element the element to check.
     * @return either the property name or an empty string.
     */
    private String getAttributeNameFromProperty(@Nonnull final Element element) {
        StringBuilder result = new StringBuilder();
        String elementName = element.getSimpleName().toString();
        if (elementName.startsWith(PROPERTY_PREFIX)) {
            result.append(Introspector.decapitalize(elementName.substring(PROPERTY_PREFIX_LENGTH)));
        } else if (elementName.startsWith(BOOLEAN_PROPERTY_PREFIX)) {
            TypeMirror returnType = ((ExecutableElement)element).getReturnType();
            if (TypeKind.BOOLEAN.equals(returnType.getKind())) {
                result.append(Introspector.decapitalize(elementName.substring(BOOLEAN_PROPERTY_PREFIX_LENGTH)));
            } else {
                Element returnElement = this.processingEnv.getTypeUtils().asElement(returnType);
                if (returnElement != null && ElementKind.CLASS.equals(returnElement.getKind())) {
                    if (Boolean.class.getName().equals(((TypeElement)returnElement).getQualifiedName().toString())) {
                        result.append(Introspector.decapitalize(elementName.substring(2)));
                    }
                }
            }
        }
        return result.toString();
    }

}
