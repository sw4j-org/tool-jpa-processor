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

import org.sw4j.tool.annotation.jpa.processor.mock.persistence.EntityMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.NameMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.TypeElementMock;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.persistence.Entity;
import javax.tools.Diagnostic;
import org.sw4j.tool.annotation.jpa.generator.model.Model;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.MessagerMock;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.ProcessingEnvironmentMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.VariableElementMock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class EntityProcessorTest {

    private EntityProcessor unitUnderTest;

    private ProcessingEnvironment processingEnv;

    private Map<String, String> options;

    private MessagerMock messager;

    @BeforeMethod
    public void setUp() {
        this.options = new HashMap<>();
        this.messager = new MessagerMock();

        this.unitUnderTest = new EntityProcessor();
        this.processingEnv = new ProcessingEnvironmentMock(this.options, this.messager);
        this.unitUnderTest.init(this.processingEnv);
    }

    @Test
    public void testProcessNonEntity() {
        final Model testModel =  new Model();
        Name testName = new NameMock("Test");
        final Element testElement = new TypeElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.CLASS, null, new LinkedList<Element>());

        this.unitUnderTest.process(testElement, testModel);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.WARNING,
                "Expected a message with level WARNING to be created.");
    }

    @Test
    public void testProcessEntityNoClass() {
        final Model testModel =  new Model();
        Name testName = new NameMock("");
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Entity entityAnnotation = new EntityMock("");
        annotations.put(Entity.class, entityAnnotation);
        final Element testElement = new TypeElementMock(testName, annotations, ElementKind.ENUM, null,
                new LinkedList<Element>());

        this.unitUnderTest.process(testElement, testModel);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.WARNING,
                "Expected a message with level WARNING to be created.");
    }

    @Test
    public void testProcessEntityNoTopLevelClass() {
        final Model testModel = new Model();
        Name testName = new NameMock("");
        Element enclosingElement = new TypeElementMock(testName, new HashMap<Class<?>, Annotation>(), ElementKind.CLASS,
                null, new LinkedList<Element>());
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Entity EntityAnnotation = new EntityMock("");
        annotations.put(Entity.class, EntityAnnotation);
        final Element testElement = new TypeElementMock(testName, annotations, ElementKind.CLASS, enclosingElement,
                new LinkedList<Element>());

        this.unitUnderTest.process(testElement, testModel);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.WARNING,
                "Expected a message with level WARNING to be created.");
    }

    @Test
    public void testProcessEntityNoName() {
        final Model testModel = new Model();
        Name testName = new NameMock("Test");
        Element enclosingElement = new TypeElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.PACKAGE, null, new LinkedList<Element>());
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Entity EntityAnnotation = new EntityMock("");
        annotations.put(Entity.class, EntityAnnotation);
        final Element testElement = new TypeElementMock(testName, annotations, ElementKind.CLASS, enclosingElement,
                new LinkedList<Element>());

        this.unitUnderTest.process(testElement, testModel);

        Assert.assertEquals(testModel.getEntities().size(), 1, "Expected a model with a single entity.");
        Assert.assertEquals(testModel.getEntities().get(0).getName(), "Test");
    }

    @Test
    public void testProcessEntityWithExplicitName() {
        final Model testModel = new Model();
        Name testName = new NameMock("Test");
        Element enclosingElement = new TypeElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.PACKAGE, null, new LinkedList<Element>());
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Entity EntityAnnotation = new EntityMock("EntityName");
        annotations.put(Entity.class, EntityAnnotation);
        final Element testElement = new TypeElementMock(testName, annotations, ElementKind.CLASS, enclosingElement,
                new LinkedList<Element>());

        this.unitUnderTest.process(testElement, testModel);

        Assert.assertEquals(testModel.getEntities().size(), 1, "Expected a model with a single entity.");
        Assert.assertEquals(testModel.getEntities().get(0).getName(), "EntityName");
    }

    @Test
    public void testProcessEntityWithSingleField() {
        final Model testModel = new Model();
        Name testName = new NameMock("Test");
        Element enclosingElement = new TypeElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.PACKAGE, null, new LinkedList<Element>());
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Entity EntityAnnotation = new EntityMock("");
        annotations.put(Entity.class, EntityAnnotation);

        List<Element> enclosedElements = new LinkedList<>();

        final Element testElement = new TypeElementMock(testName, annotations, ElementKind.CLASS, enclosingElement,
                enclosedElements);

        Name fieldName = new NameMock("field");
        Element field = new VariableElementMock(fieldName, new HashMap<Class<?>, Annotation>(), ElementKind.FIELD,
                testElement, null);

        enclosedElements.add(field);

        this.unitUnderTest.process(testElement, testModel);

        Assert.assertEquals(testModel.getEntities().size(), 1, "Expected a model with a single entity.");
        Assert.assertEquals(testModel.getEntities().get(0).getName(), "Test",
                "Expected the entity to have the name \"Test\".");
    }

    @Test
    public void testProcessEntityWithSingleGetter() {
        final Model testModel = new Model();
        Name testName = new NameMock("Test");
        Element enclosingElement = new TypeElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.PACKAGE, null, new LinkedList<Element>());
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Entity EntityAnnotation = new EntityMock("");
        annotations.put(Entity.class, EntityAnnotation);

        List<Element> enclosedElements = new LinkedList<>();

        final Element testElement = new TypeElementMock(testName, annotations, ElementKind.CLASS, enclosingElement,
                enclosedElements);

        Name propertyName = new NameMock("getProperty");
        Element field = new VariableElementMock(propertyName, new HashMap<Class<?>, Annotation>(), ElementKind.METHOD,
                testElement, null);

        enclosedElements.add(field);

        this.unitUnderTest.process(testElement, testModel);

        Assert.assertEquals(testModel.getEntities().size(), 1, "Expected a model with a single entity.");
        Assert.assertEquals(testModel.getEntities().get(0).getName(), "Test");
    }

    @Test
    public void testProcessEntityWithSingleSetter() {
        final Model testModel = new Model();
        Name testName = new NameMock("Test");
        Element enclosingElement = new TypeElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.PACKAGE, null, new LinkedList<Element>());
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Entity EntityAnnotation = new EntityMock("");
        annotations.put(Entity.class, EntityAnnotation);

        List<Element> enclosedElements = new LinkedList<>();

        final Element testElement = new TypeElementMock(testName, annotations, ElementKind.CLASS, enclosingElement,
                enclosedElements);

        Name propertyName = new NameMock("setProperty");
        Element field = new VariableElementMock(propertyName, new HashMap<Class<?>, Annotation>(), ElementKind.METHOD,
                testElement, null);

        enclosedElements.add(field);

        this.unitUnderTest.process(testElement, testModel);

        Assert.assertEquals(testModel.getEntities().size(), 1, "Expected a model with a single entity.");
        Assert.assertEquals(testModel.getEntities().get(0).getName(), "Test");
    }

    @Test
    public void testProcessEntityWithIndependentMethod() {
        final Model testModel = new Model();
        Name testName = new NameMock("Test");
        Element enclosingElement = new TypeElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.PACKAGE, null, new LinkedList<Element>());
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Entity EntityAnnotation = new EntityMock("");
        annotations.put(Entity.class, EntityAnnotation);

        List<Element> enclosedElements = new LinkedList<>();

        final Element testElement = new TypeElementMock(testName, annotations, ElementKind.CLASS, enclosingElement,
                enclosedElements);

        Name propertyName = new NameMock("foo");
        Element field = new VariableElementMock(propertyName, new HashMap<Class<?>, Annotation>(), ElementKind.METHOD,
                testElement, null);

        enclosedElements.add(field);

        this.unitUnderTest.process(testElement, testModel);

        Assert.assertEquals(testModel.getEntities().size(), 1, "Expected a model with a single entity.");
        Assert.assertEquals(testModel.getEntities().get(0).getName(), "Test");
    }

    @Test
    public void testProcessEntityWithEmbeddedClass() {
        final Model testModel = new Model();
        Name testName = new NameMock("Test");
        Element enclosingElement = new TypeElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.PACKAGE, null, new LinkedList<Element>());
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Entity EntityAnnotation = new EntityMock("");
        annotations.put(Entity.class, EntityAnnotation);

        List<Element> enclosedElements = new LinkedList<>();

        final Element testElement = new TypeElementMock(testName, annotations, ElementKind.CLASS, enclosingElement,
                enclosedElements);

        Name propertyName = new NameMock("Embedded");
        Element field = new VariableElementMock(propertyName, new HashMap<Class<?>, Annotation>(), ElementKind.CLASS,
                testElement, null);

        enclosedElements.add(field);

        this.unitUnderTest.process(testElement, testModel);

        Assert.assertEquals(testModel.getEntities().size(), 1, "Expected a model with a single entity.");
        Assert.assertEquals(testModel.getEntities().get(0).getName(), "Test");
    }

}
