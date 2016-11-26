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
import java.util.List;
import java.util.Map;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeKind;
import javax.persistence.Id;
import javax.tools.Diagnostic;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.MessagerMock;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.ProcessingEnvironmentMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.ElementMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.ExecutableElementMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.NameMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.TypeElementMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.VariableElementMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.type.TypeMirrorMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.util.TypesMock;
import org.sw4j.tool.annotation.jpa.processor.mock.persistence.IdMock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class AttributeProcessorTest {

    private AttributeProcessor unitUnderTest;

    private ProcessingEnvironment processingEnv;

    private MessagerMock messager;

    private TypesMock types;

    @BeforeMethod
    public void setUp() {
        this.messager = new MessagerMock();
        this.types = new TypesMock();

        this.unitUnderTest = new AttributeProcessor();
        this.processingEnv = new ProcessingEnvironmentMock(this.messager, this.types);
        this.unitUnderTest.init(this.processingEnv);
    }

    @Test
    public void testProcessNoAttribute() {
        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        Assert.assertTrue(testEntity.getAttributes().isEmpty(), "Expected the entity to have no attributes.");
    }

    @Test
    public void testProcessEmpty() {
        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        this.unitUnderTest.process(testEntity, new LinkedList<Element>());

        Assert.assertTrue(testEntity.getAttributes().isEmpty(), "Expected entity with empty attributes.");
    }

    @Test
    public void testProcessOnlyFieldNoId() {
        List<Element> enclosedElements = new LinkedList<>();

        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");
        Name idName = new NameMock("id");
        Element testElement = new VariableElementMock(idName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());
        enclosedElements.add(testElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.ERROR,
                "Expected a message with level ERROR to be created.");
    }

    @Test
    public void testProcessOnlyFieldId() {
        List<Element> enclosedElements = new LinkedList<>();

        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Name idName = new NameMock("id");
        Id id = new IdMock();
        annotations.put(Id.class, id);
        Element testElement = new VariableElementMock(idName, annotations, ElementKind.FIELD, null,
                new LinkedList<Element>());
        enclosedElements.add(testElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    @Test
    public void testProcessOnlyPropertyNoId() {
        List<Element> enclosedElements = new LinkedList<>();

        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");
        Name idName = new NameMock("getId");
        Element testElement = new ExecutableElementMock(idName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, null, new LinkedList<Element>(), null);
        enclosedElements.add(testElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.ERROR,
                "Expected a message with level ERROR to be created.");
    }

    @Test
    public void testProcessOnlyPropertyId() {
        List<Element> enclosedElements = new LinkedList<>();

        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Name idName = new NameMock("getId");
        Id id = new IdMock();
        annotations.put(Id.class, id);
        Element testElement = new ExecutableElementMock(idName, annotations, ElementKind.METHOD, null,
                new LinkedList<Element>(), null);
        enclosedElements.add(testElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    @Test
    public void testProcessFieldPropertyNoId() {
        List<Element> enclosedElements = new LinkedList<>();

        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");
        Name fieldName = new NameMock("id");
        Name propertyName = new NameMock("getId");
        Element fieldElement = new VariableElementMock(fieldName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());
        enclosedElements.add(fieldElement);
        Element propertyElement = new ExecutableElementMock(propertyName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, null, new LinkedList<Element>(), null);
        enclosedElements.add(propertyElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.ERROR,
                "Expected a message with level ERROR to be created.");
    }

    @Test
    public void testProcessFieldPropertyFieldWithId() {
        List<Element> enclosedElements = new LinkedList<>();

        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Name fieldName = new NameMock("id");
        Name propertyName = new NameMock("getId");
        Id id = new IdMock();
        annotations.put(Id.class, id);
        Element fieldElement = new VariableElementMock(fieldName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());
        enclosedElements.add(fieldElement);
        Element propertyElement = new ExecutableElementMock(propertyName, annotations, ElementKind.METHOD, null,
                new LinkedList<Element>(), null);
        enclosedElements.add(propertyElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    @Test
    public void testProcessFieldPropertyPropertyWithId() {
        List<Element> enclosedElements = new LinkedList<>();

        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Name fieldName = new NameMock("id");
        Name propertyName = new NameMock("getId");
        Id id = new IdMock();
        annotations.put(Id.class, id);
        Element fieldElement = new VariableElementMock(fieldName, annotations, ElementKind.FIELD, null,
                new LinkedList<Element>());
        enclosedElements.add(fieldElement);
        Element propertyElement = new ExecutableElementMock(propertyName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, null, new LinkedList<Element>(), null);
        enclosedElements.add(propertyElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    @Test
    public void testProcessFieldPropertyBothWithId() {
        List<Element> enclosedElements = new LinkedList<>();

        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");
        Map<Class<?>, Annotation> annotations = new HashMap<>();
        Name fieldName = new NameMock("id");
        Name propertyName = new NameMock("getId");
        Id id = new IdMock();
        annotations.put(Id.class, id);
        Element fieldElement = new VariableElementMock(fieldName, annotations, ElementKind.FIELD, null,
                new LinkedList<Element>());
        enclosedElements.add(fieldElement);
        Element propertyElement = new ExecutableElementMock(propertyName, annotations, ElementKind.METHOD, null,
                new LinkedList<Element>(), null);
        enclosedElements.add(propertyElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    @Test
    public void testProcessSingleAttribute() {
        List<Element> enclosedElements = new LinkedList<>();

        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");
        Name testName = new NameMock("test");
        Element testElement = new VariableElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());
        enclosedElements.add(testElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.ERROR,
                "Expected a message with level ERROR to be created.");
    }

    @Test
    public void testProcessTwoAttributes() {
        List<Element> enclosedElements = new LinkedList<>();

        final Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");
        Name testName = new NameMock("test");
        Element testElement = new VariableElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());
        enclosedElements.add(testElement);

        testName = new NameMock("test2");
        testElement = new VariableElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());
        enclosedElements.add(testElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.ERROR,
                "Expected a message with level ERROR to be created.");
    }

    @Test
    public void testIsFieldWithField() {
        Name testName = new NameMock("test");
        Element testElement = new VariableElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());

        Assert.assertTrue(this.unitUnderTest.isField(testElement), "Expected the field to be a field.");
    }

    @Test
    public void testIsFieldWithMethod() {
        Name testName = new NameMock("test");
        Element testElement = new ExecutableElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, null, new LinkedList<Element>(), null);

        Assert.assertFalse(this.unitUnderTest.isField(testElement), "Expected the method not to be a field.");
    }

    @Test
    public void testIsPropertyWithField() {
        Name testName = new NameMock("test");
        Element testElement = new VariableElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.FIELD, null, new LinkedList<Element>());

        Assert.assertFalse(this.unitUnderTest.isProperty(testElement),
                "Expected the field not to be a property.");
    }

    @Test
    public void testIsPropertyWithNonGetterMethod() {
        Name testName = new NameMock("test");
        Element testElement = new ExecutableElementMock(testName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, null, new LinkedList<Element>(), null);

        Assert.assertFalse(this.unitUnderTest.isProperty(testElement),
                "Expected the method not to be a property.");
    }

    @Test
    public void testIsPropertyWithGetterMethodPrimitiveResult() {
        Name entityName = new NameMock("Test");
        Name className = new NameMock("org.sw4j.test.Test");
        Element classElement = new TypeElementMock(entityName, className, new HashMap<Class<?>, Annotation>(),
                ElementKind.CLASS, null, null);
        Name methodName = new NameMock("getTest");
        Element methodElement = new ExecutableElementMock(methodName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, classElement, new LinkedList<Element>(), new TypeMirrorMock(TypeKind.INT));

        Assert.assertTrue(this.unitUnderTest.isProperty(methodElement),
                "Expected the method to be a property with primitive return type.");
    }

    @Test
    public void testIsPropertyWithGetterMethodObjectResult() {
        Name entityName = new NameMock("Test");
        Name className = new NameMock("org.sw4j.test.Test");
        Element classElement = new TypeElementMock(entityName, className, new HashMap<Class<?>, Annotation>(),
                ElementKind.CLASS, null, null);
        Name methodName = new NameMock("getTest");
        Element methodElement = new ExecutableElementMock(methodName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, classElement, new LinkedList<Element>(), new TypeMirrorMock(TypeKind.INT));

        Assert.assertTrue(this.unitUnderTest.isProperty(methodElement),
                "Expected the method to be a property with primitive return type.");
    }

    @Test
    public void testIsPropertyWithGetterMethodPrimitiveBooleanResult() {
        Name entityName = new NameMock("Test");
        Name className = new NameMock("org.sw4j.test.Test");
        Element classElement = new TypeElementMock(entityName, className, new HashMap<Class<?>, Annotation>(),
                ElementKind.CLASS, null, null);
        Name methodName = new NameMock("isTest");
        Element methodElement = new ExecutableElementMock(methodName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, classElement, new LinkedList<Element>(), new TypeMirrorMock(TypeKind.BOOLEAN));

        Assert.assertTrue(this.unitUnderTest.isProperty(methodElement),
                "Expected the method to be a property with primitive return type.");
    }

    @Test
    public void testIsPropertyWithGetterMethodObjectBooleanResult() {
        Element asElement = new TypeElementMock(new NameMock("Boolean"), new NameMock("java.lang.Boolean"),
                new HashMap<Class<?>, Annotation>(), ElementKind.CLASS, null, null);
        this.types.asElement(asElement);

        Name entityName = new NameMock("Test");
        Name className = new NameMock("org.sw4j.test.Test");
        Element classElement = new TypeElementMock(entityName, className, new HashMap<Class<?>, Annotation>(),
                ElementKind.CLASS, null, null);
        Name methodName = new NameMock("isTest");
        Element methodElement = new ExecutableElementMock(methodName, new HashMap<Class<?>, Annotation>(),
                ElementKind.METHOD, classElement, new LinkedList<Element>(), new TypeMirrorMock(TypeKind.DECLARED));

        Assert.assertTrue(this.unitUnderTest.isProperty(methodElement),
                "Expected the method to be a property with primitive return type.");
    }

}
