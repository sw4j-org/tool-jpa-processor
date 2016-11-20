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

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.persistence.Id;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.sw4j.tool.annotation.jpa.processor.exceptions.AnnotationProcessorException;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.ExecutableElementMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.NameMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.VariableElementMock;
import org.sw4j.tool.annotation.jpa.processor.mock.persistence.IdMock;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class AttributeProcessorTest {

    @Test
    public void testProcessNoAttribute() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");

        Assert.assertTrue(testEntity.getAttributes().isEmpty(), "Expected the entity to have no attributes.");
    }

    @Test
    public void testProcessBothNull() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");

        AttributeProcessor unitUnderTest = new AttributeProcessor();

        unitUnderTest.process(testEntity, "id", null, null);

        Assert.assertTrue(testEntity.getAttributes().isEmpty(), "Expected entity with empty attributes.");
    }

    @Test
    public void testProcessOnlyFieldNoId() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");
        Name idName = new NameMock("id");
        Element testElement = new VariableElementMock(idName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());

        AttributeProcessor unitUnderTest = new AttributeProcessor();

        unitUnderTest.process(testEntity, "id", testElement, null);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertFalse(testEntity.getAttributes().get(0).isId(), "Expected attribute is no Id.");
    }

    @Test
    public void testProcessOnlyFieldId() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Name idName = new NameMock("id");
        Id id = new IdMock();
        annotations.put(Id.class, id);
        Element testElement = new VariableElementMock(idName, annotations, ElementKind.FIELD, null,
                new LinkedList<Element>());

        AttributeProcessor unitUnderTest = new AttributeProcessor();

        unitUnderTest.process(testEntity, "id", testElement, null);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    @Test
    public void testProcessOnlyPropertyNoId() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");
        Name idName = new NameMock("getId");
        Element testElement = new ExecutableElementMock(idName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, null, new LinkedList<Element>());

        AttributeProcessor unitUnderTest = new AttributeProcessor();

        unitUnderTest.process(testEntity, "id", null, testElement);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertFalse(testEntity.getAttributes().get(0).isId(), "Expected attribute is no Id.");
    }

    @Test
    public void testProcessOnlyPropertyId() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Name idName = new NameMock("getId");
        Id id = new IdMock();
        annotations.put(Id.class, id);
        Element testElement = new ExecutableElementMock(idName, annotations, ElementKind.FIELD, null,
                new LinkedList<Element>());

        AttributeProcessor unitUnderTest = new AttributeProcessor();

        unitUnderTest.process(testEntity, "id", null, testElement);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    @Test
    public void testProcessFieldPropertyNoId() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");
        Name fieldName = new NameMock("id");
        Name propertyName = new NameMock("getId");
        Element fieldElement = new VariableElementMock(fieldName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());
        Element propertyElement = new ExecutableElementMock(propertyName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, null, new LinkedList<Element>());

        AttributeProcessor unitUnderTest = new AttributeProcessor();

        unitUnderTest.process(testEntity, "id", fieldElement, propertyElement);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertFalse(testEntity.getAttributes().get(0).isId(), "Expected attribute is no Id.");
    }

    @Test
    public void testProcessFieldPropertyFieldWithId() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Name fieldName = new NameMock("id");
        Name propertyName = new NameMock("getId");
        Id id = new IdMock();
        annotations.put(Id.class, id);
        Element fieldElement = new VariableElementMock(fieldName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());
        Element propertyElement = new ExecutableElementMock(propertyName, annotations, ElementKind.METHOD, null,
                new LinkedList<Element>());

        AttributeProcessor unitUnderTest = new AttributeProcessor();

        unitUnderTest.process(testEntity, "id", fieldElement, propertyElement);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    @Test
    public void testProcessFieldPropertyPropertyWithId() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Name fieldName = new NameMock("id");
        Name propertyName = new NameMock("getId");
        Id id = new IdMock();
        annotations.put(Id.class, id);
        Element fieldElement = new VariableElementMock(fieldName, annotations, ElementKind.FIELD, null,
                new LinkedList<Element>());
        Element propertyElement = new ExecutableElementMock(propertyName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, null, new LinkedList<Element>());

        AttributeProcessor unitUnderTest = new AttributeProcessor();

        unitUnderTest.process(testEntity, "id", fieldElement, propertyElement);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    @Test
    public void testProcessFieldPropertyBothWithId() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Name fieldName = new NameMock("id");
        Name propertyName = new NameMock("getId");
        Id id = new IdMock();
        annotations.put(Id.class, id);
        Element fieldElement = new VariableElementMock(fieldName, annotations, ElementKind.FIELD, null,
                new LinkedList<Element>());
        Element propertyElement = new ExecutableElementMock(propertyName, annotations, ElementKind.METHOD, null,
                new LinkedList<Element>());

        AttributeProcessor unitUnderTest = new AttributeProcessor();

        unitUnderTest.process(testEntity, "id", fieldElement, propertyElement);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    @Test
    public void testProcessSingleAttribute() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");
        Name testName = new NameMock("test");
        Element testElement = new VariableElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());

        AttributeProcessor unitUnderTest = new AttributeProcessor();

        unitUnderTest.process(testEntity, "test", testElement, null);

        Assert.assertNotNull(testEntity.getAttributes(), "Expected the entity to have attributes.");
        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected the entity to have one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "test",
                "Expected the attribute to have the name \"test\".");
    }

    @Test
    public void testProcessTwoAttributes() throws AnnotationProcessorException {
        final Entity testEntity =  new Entity("Test");
        Name testName = new NameMock("test");
        Element testElement = new VariableElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());

        final AttributeProcessor unitUnderTest = new AttributeProcessor();

        unitUnderTest.process(testEntity, "test", testElement, null);

        testName = new NameMock("test2");
        testElement = new VariableElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());

        unitUnderTest.process(testEntity, "test2", testElement, null);

        Assert.assertNotNull(testEntity.getAttributes(), "Expected the entity to have attributes.");
        Assert.assertEquals(testEntity.getAttributes().size(), 2, "Expected the entity to have two attributes.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "test",
                "Expected the attribute to have the name \"test\".");
        Assert.assertEquals(testEntity.getAttributes().get(1).getName(), "test2",
                "Expected the attribute to have the name \"test2\".");
    }

}
