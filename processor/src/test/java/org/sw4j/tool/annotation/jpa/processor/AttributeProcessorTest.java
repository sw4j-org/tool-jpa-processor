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

import java.util.LinkedList;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.TypeKind;
import javax.persistence.Id;
import javax.tools.Diagnostic;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.MessagerMock;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.ProcessingEnvironmentMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.ExecutableElementBuilder;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.TypeElementBuilder;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.VariableElementBuilder;
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

    private ExecutableElementBuilder executableElementBuilder;

    private TypeElementBuilder typeElementBuilder;

    private VariableElementBuilder variableElementBuilder;

    @BeforeMethod
    public void setUp() {
        this.executableElementBuilder = new ExecutableElementBuilder();
        this.typeElementBuilder = new TypeElementBuilder();
        this.variableElementBuilder = new VariableElementBuilder();

        this.messager = new MessagerMock();
        this.types = new TypesMock();

        this.unitUnderTest = new AttributeProcessor();
        this.processingEnv = new ProcessingEnvironmentMock(this.messager, this.types);
        this.unitUnderTest.init(this.processingEnv);
    }

    /**
     * Test that an empty entity has no attributes but a collection.
     */
    @Test
    public void testProcessNoAttribute() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        Assert.assertNotNull(testEntity.getAttributes(), "Expected the entity to have an attributes collection.");
        Assert.assertTrue(testEntity.getAttributes().isEmpty(), "Expected the entity to have no attributes.");
    }

    /**
     * Test that an empty attributes list has no attributes in the model.
     */
    @Test
    public void testProcessEmptyAttributesList() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        this.unitUnderTest.process(testEntity, new LinkedList<Element>());

        Assert.assertTrue(testEntity.getAttributes().isEmpty(), "Expected entity with empty attributes.");
    }

    /**
     * Test that an entity with a single field that is not annotated with @Id emits a warning.
     */
    @Test
    public void testProcessFieldNoId() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.variableElementBuilder.setSimpleName("id");
        this.variableElementBuilder.setKind(ElementKind.FIELD);
        this.variableElementBuilder.setTypeKind(TypeKind.LONG);
        Element testElement = this.variableElementBuilder.createElement();
        enclosedElements.add(testElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.WARNING,
                "Expected a message with level WARNING to be created.");
    }

    /**
     * Test that an entity with a single property that is not annotated with @Id emits a warning.
     */
    @Test
    public void testProcessPropertyNoId() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.executableElementBuilder.setSimpleName("getId");
        this.executableElementBuilder.setReturnTypeKind(TypeKind.LONG);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element testElement = this.executableElementBuilder.createElement();
        enclosedElements.add(testElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.WARNING,
                "Expected a message with level WARNING to be created.");
    }

    /**
     * Test that an entity with a single field that is annotated with @Id is added to the model.
     */
    @Test
    public void testProcessFieldId() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.variableElementBuilder.setSimpleName("id");
        this.variableElementBuilder.addAnnotation(Id.class, new IdMock());
        this.variableElementBuilder.setKind(ElementKind.FIELD);
        this.variableElementBuilder.setTypeKind(TypeKind.LONG);
        Element testElement = this.variableElementBuilder.createElement();
        enclosedElements.add(testElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    /**
     * Test that an entity with a single property that is annotated with @Id is added to the model.
     */
    @Test
    public void testProcessPropertyId() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.executableElementBuilder.setSimpleName("getId");
        this.executableElementBuilder.addAnnotation(Id.class, new IdMock());
        this.executableElementBuilder.setReturnTypeKind(TypeKind.LONG);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element testElement = this.executableElementBuilder.createElement();
        enclosedElements.add(testElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    /**
     * Test that an entity with a single field and a single property that is not annotated with @Id emits a warning.
     */
    @Test
    public void testProcessFieldPropertyNoId() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.variableElementBuilder.setSimpleName("id");
        this.variableElementBuilder.setKind(ElementKind.FIELD);
        this.variableElementBuilder.setTypeKind(TypeKind.LONG);
        Element fieldElement = this.variableElementBuilder.createElement();
        enclosedElements.add(fieldElement);

        this.executableElementBuilder.setSimpleName("getId");
        this.executableElementBuilder.setReturnTypeKind(TypeKind.LONG);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element propertyElement = this.executableElementBuilder.createElement();
        enclosedElements.add(propertyElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.WARNING,
                "Expected a message with level WARNING to be created.");
    }

    /**
     * Test that an entity with a single field and a single property where the field is annotated with @Id adds the
     * field to the model.
     */
    @Test
    public void testProcessFieldPropertyFieldId() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.variableElementBuilder.setSimpleName("id1");
        this.variableElementBuilder.addAnnotation(Id.class, new IdMock());
        this.variableElementBuilder.setKind(ElementKind.FIELD);
        this.variableElementBuilder.setTypeKind(TypeKind.LONG);
        Element fieldElement = this.variableElementBuilder.createElement();
        enclosedElements.add(fieldElement);

        this.executableElementBuilder.setSimpleName("getId2");
        this.executableElementBuilder.setReturnTypeKind(TypeKind.LONG);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element propertyElement = this.executableElementBuilder.createElement();
        enclosedElements.add(propertyElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id1",
                "Expected entity with attribute named \"id1\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    /**
     * Test that an entity with a single field and a single property where the property is annotated with @Id adds the
     * property to the model.
     */
    @Test
    public void testProcessFieldPropertyPropertyId() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.variableElementBuilder.setSimpleName("id1");
        this.variableElementBuilder.setKind(ElementKind.FIELD);
        this.variableElementBuilder.setTypeKind(TypeKind.LONG);
        Element fieldElement = this.variableElementBuilder.createElement();
        enclosedElements.add(fieldElement);

        this.executableElementBuilder.setSimpleName("getId2");
        this.executableElementBuilder.addAnnotation(Id.class, new IdMock());
        this.executableElementBuilder.setReturnTypeKind(TypeKind.LONG);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element propertyElement = this.executableElementBuilder.createElement();
        enclosedElements.add(propertyElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with one attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id2",
                "Expected entity with attribute named \"id2\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    /**
     * Test that an entity with two attributes (with the same name) both annotated with @Id emits an error.
     */
    @Test
    public void testProcessFieldPropertyBothWithId() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.variableElementBuilder.setSimpleName("id");
        this.variableElementBuilder.addAnnotation(Id.class, new IdMock());
        this.variableElementBuilder.setKind(ElementKind.FIELD);
        this.variableElementBuilder.setTypeKind(TypeKind.LONG);
        Element fieldElement = this.variableElementBuilder.createElement();
        enclosedElements.add(fieldElement);

        this.executableElementBuilder.setSimpleName("getId");
        this.executableElementBuilder.addAnnotation(Id.class, new IdMock());
        this.executableElementBuilder.setReturnTypeKind(TypeKind.LONG);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element propertyElement = this.executableElementBuilder.createElement();
        enclosedElements.add(propertyElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.ERROR,
                "Expected a message with level ERROR to be created.");
    }

    /**
     * Tests that a primitive boolean property is also recognised when the getter starts with "is".
     */
    @Test
    public void testProcessPropertyWithPrimitiveBoolean() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.executableElementBuilder.setSimpleName("isFlag");
        this.executableElementBuilder.setReturnTypeKind(TypeKind.BOOLEAN);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element propertyElement = this.executableElementBuilder.createElement();
        enclosedElements.add(propertyElement);

        this.executableElementBuilder.setSimpleName("getId");
        this.executableElementBuilder.addAnnotation(Id.class, new IdMock());
        this.executableElementBuilder.setReturnTypeKind(TypeKind.LONG);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element idElement = this.executableElementBuilder.createElement();
        enclosedElements.add(idElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 2, "Expected entity with two attributes.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "flag",
                "Expected entity with attribute named \"flag\".");
        Assert.assertFalse(testEntity.getAttributes().get(0).isId(), "Expected attribute is noId.");
        Assert.assertEquals(testEntity.getAttributes().get(1).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(1).isId(), "Expected attribute is Id.");
    }

    /**
     * Tests that a boolean object (java.lang.Boolean) property is also recognised when the getter starts with "is".
     */
    @Test
    public void testProcessPropertyWithObjectBoolean() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.executableElementBuilder.setSimpleName("isFlag");
        this.executableElementBuilder.setReturnTypeKind(TypeKind.DECLARED);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element propertyElement = this.executableElementBuilder.createElement();
        enclosedElements.add(propertyElement);

        this.typeElementBuilder.setSimpleName("Boolean");
        this.typeElementBuilder.setQualifiedName("java.lang.Boolean");
        this.typeElementBuilder.setKind(ElementKind.CLASS);
        Element returnElement = this.typeElementBuilder.createElement();
        this.types.asElement(returnElement);

        this.executableElementBuilder.setSimpleName("getId");
        this.executableElementBuilder.addAnnotation(Id.class, new IdMock());
        this.executableElementBuilder.setReturnTypeKind(TypeKind.LONG);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element idElement = this.executableElementBuilder.createElement();
        enclosedElements.add(idElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 2, "Expected entity with two attributes.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "flag",
                "Expected entity with attribute named \"flag\".");
        Assert.assertFalse(testEntity.getAttributes().get(0).isId(), "Expected attribute is no Id.");
        Assert.assertEquals(testEntity.getAttributes().get(1).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(1).isId(), "Expected attribute is Id.");
    }

    /**
     * Tests that a method starting with "is" that has not return type boolean is not recognised as property.
     */
    @Test
    public void testProcessPropertyWithoutBoolean() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.executableElementBuilder.setSimpleName("isFlag");
        this.executableElementBuilder.setReturnTypeKind(TypeKind.DECLARED);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element propertyElement = this.executableElementBuilder.createElement();
        enclosedElements.add(propertyElement);

        this.typeElementBuilder.setSimpleName("Integer");
        this.typeElementBuilder.setQualifiedName("java.lang.Integer");
        this.typeElementBuilder.setKind(ElementKind.CLASS);
        Element returnElement = this.typeElementBuilder.createElement();
        this.types.asElement(returnElement);

        this.executableElementBuilder.setSimpleName("getId");
        this.executableElementBuilder.addAnnotation(Id.class, new IdMock());
        this.executableElementBuilder.setReturnTypeKind(TypeKind.LONG);
        this.executableElementBuilder.setKind(ElementKind.METHOD);
        Element idElement = this.executableElementBuilder.createElement();
        enclosedElements.add(idElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with two attributes.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertTrue(testEntity.getAttributes().get(0).isId(), "Expected attribute is Id.");
    }

    /**
     * Test that the primitive data type of a field is correctly added to the model.
     */
    @Test
    public void testProcessFieldPrimitiveDataType() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.variableElementBuilder.setSimpleName("id");
        this.variableElementBuilder.addAnnotation(Id.class, new IdMock());
        this.variableElementBuilder.setTypeKind(TypeKind.LONG);
        this.variableElementBuilder.setKind(ElementKind.FIELD);
        Element idElement = this.variableElementBuilder.createElement();
        enclosedElements.add(idElement);

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with a single @Id attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertEquals(testEntity.getAttributes().get(0).getDataType(), "long",
                "Expected attribute is data type long.");
    }

    /**
     * Test that the non primitive data type of a field is correctly added to the model.
     */
    @Test
    public void testProcessFieldObjectDataType() {
        Entity testEntity =  new Entity("Test", "org.sw4j.test.Test");

        List<Element> enclosedElements = new LinkedList<>();

        this.variableElementBuilder.setSimpleName("id");
        this.variableElementBuilder.addAnnotation(Id.class, new IdMock());
        this.variableElementBuilder.setTypeKind(TypeKind.DECLARED);
        this.variableElementBuilder.setKind(ElementKind.FIELD);
        Element idElement = this.variableElementBuilder.createElement();
        enclosedElements.add(idElement);

        this.typeElementBuilder.setSimpleName("String");
        this.typeElementBuilder.setQualifiedName("java.lang.String");
        this.typeElementBuilder.setKind(ElementKind.CLASS);
        this.types.asElement(this.typeElementBuilder.createElement());

        this.unitUnderTest.process(testEntity, enclosedElements);

        Assert.assertEquals(testEntity.getAttributes().size(), 1, "Expected entity with a single @Id attribute.");
        Assert.assertEquals(testEntity.getAttributes().get(0).getName(), "id",
                "Expected entity with attribute named \"id\".");
        Assert.assertEquals(testEntity.getAttributes().get(0).getDataType(), "java.lang.String",
                "Expected attribute is data type java.lang.String.");
    }

}
