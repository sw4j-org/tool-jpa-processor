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
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.persistence.Id;
import org.sw4j.tool.annotation.jpa.generator.model.Attribute;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;

/**
 * This is a processor to handle attributes of classes with an @Entity annotation.
 *
 * @author Uwe Plonus
 */
public class AttributeProcessor {

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
    public void init(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    /**
     * Process a single entity annotated with {@code @Entity}. An attribute may be defined by either a field or a
     * property or both. Which one is used depends on the access of the entity.
     *
     * @param entity the entity this attribute belongs to.
     * @param attributeName the name of the attribute.
     * @param fieldElement the possible field of the attribute.
     * @param propertyElement the possible property of the attribute.
     */
    public void process(@Nonnull final Entity entity, @Nonnull final String attributeName,
            @Nullable final Element fieldElement, @Nullable final Element propertyElement) {
        if (fieldElement != null || propertyElement != null) {
            Attribute attribute = new Attribute(attributeName, isPossibleIdAttribute(fieldElement, propertyElement));
            entity.addAttribute(attribute);
        }
    }

    /**
     * Test if the given field or property is a possible {@code Id}. The caller is responsible to ensure that the
     * elements denote the same attribute.
     *
     * @param fieldElement the field element to check.
     * @param propertyElement the property element to check.
     * @return {@code true} if either the fieldElement or the propertyElement denote an {@code Id}.
     */
    private boolean isPossibleIdAttribute(@Nullable final Element fieldElement,
            @Nullable final Element propertyElement) {
        Id idAnnotation = null;
        if (fieldElement != null) {
            idAnnotation = fieldElement.getAnnotation(Id.class);
        }
        if (idAnnotation == null && propertyElement != null) {
            idAnnotation = propertyElement.getAnnotation(Id.class);
        }
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
    public boolean isProperty(@Nonnull final Element element) {
        boolean isProperty = false;
        if (ElementKind.METHOD.equals(element.getKind())) {
            isProperty = !"".equals(getPropertyFromMethod(element));
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
    public String getPropertyFromMethod(@Nonnull final Element element) {
        StringBuilder result = new StringBuilder();
        String elementName = element.getSimpleName().toString();
        if (elementName.startsWith("get")) {
            result.append(Introspector.decapitalize(elementName.substring(3)));
        } else if (elementName.startsWith("is")) {
            TypeMirror returnType = ((ExecutableElement)element).getReturnType();
            if (TypeKind.BOOLEAN.equals(returnType.getKind())) {
                result.append(Introspector.decapitalize(elementName.substring(2)));
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
