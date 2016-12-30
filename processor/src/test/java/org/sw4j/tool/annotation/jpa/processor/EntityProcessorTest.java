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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.persistence.Entity;
import javax.tools.Diagnostic;
import org.sw4j.tool.annotation.jpa.generator.model.Model;
import org.sw4j.tool.annotation.jpa.test.mock.annotation.processing.MessagerMock;
import org.sw4j.tool.annotation.jpa.test.mock.annotation.processing.ProcessingEnvironmentMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.PackageElementBuilder;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.TypeElementBuilder;
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

    private PackageElementBuilder packageElementBuilder;

    private TypeElementBuilder typeElementBuilder;

    @BeforeMethod
    public void setUp() {
        this.typeElementBuilder = new TypeElementBuilder();
        this.packageElementBuilder = new PackageElementBuilder();
        this.options = new HashMap<>();
        this.messager = new MessagerMock();

        this.unitUnderTest = new EntityProcessor();
        this.processingEnv = new ProcessingEnvironmentMock(this.options, this.messager);
        this.unitUnderTest.init(this.processingEnv);
    }

    @Test
    public void testProcessNonEntity() {
        Set<Element> testElements = new HashSet<>();
        Model testModel =  new Model();

        this.typeElementBuilder.setSimpleName("Test");
        this.typeElementBuilder.setQualifiedName("org.sw4j.test.Test");
        this.typeElementBuilder.setKind(ElementKind.CLASS);
        Element testElement = this.typeElementBuilder.createElement();
        testElements.add(testElement);

        this.unitUnderTest.process(testElements, testModel);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.WARNING,
                "Expected a message with level WARNING to be created.");
    }

    @Test
    public void testProcessEntityNoClass() {
        Set<Element> testElements = new HashSet<>();
        Model testModel =  new Model();

        this.typeElementBuilder.setSimpleName("Test");
        this.typeElementBuilder.setQualifiedName("org.sw4j.test.Test");
        this.typeElementBuilder.addAnnotation(Entity.class, new EntityMock(""));
        this.typeElementBuilder.setKind(ElementKind.ENUM);
        Element testElement = this.typeElementBuilder.createElement();
        testElements.add(testElement);

        this.unitUnderTest.process(testElements, testModel);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.WARNING,
                "Expected a message with level WARNING to be created.");
    }

    @Test
    public void testProcessEntityNoTopLevelClass() {
        Set<Element> testElements = new HashSet<>();
        Model testModel = new Model();

        this.typeElementBuilder.setSimpleName("Test");
        this.typeElementBuilder.setQualifiedName("org.sw4j.test.Test");
        this.typeElementBuilder.setKind(ElementKind.CLASS);
        Element enclosingElement = this.typeElementBuilder.createElement();

        this.typeElementBuilder.setSimpleName("Test");
        this.typeElementBuilder.setQualifiedName("org.sw4j.test.Test.Test");
        this.typeElementBuilder.addAnnotation(Entity.class, new EntityMock(""));
        this.typeElementBuilder.setKind(ElementKind.CLASS);
        this.typeElementBuilder.setEnclosingElement(enclosingElement);
        Element testElement = this.typeElementBuilder.createElement();
        testElements.add(testElement);

        this.unitUnderTest.process(testElements, testModel);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.WARNING,
                "Expected a message with level WARNING to be created.");
    }

    @Test
    public void testProcessEntityNoName() {
        Set<Element> testElements = new HashSet<>();
        Model testModel = new Model();

        this.packageElementBuilder.setSimpleName("org.sw4j.test");
        this.packageElementBuilder.setQualifiedName("org.sw4j.test");
        Element enclosingElement = this.packageElementBuilder.createElement();

        this.typeElementBuilder.setSimpleName("Test");
        this.typeElementBuilder.setQualifiedName("org.sw4j.test.Test");
        this.typeElementBuilder.addAnnotation(Entity.class, new EntityMock(""));
        this.typeElementBuilder.setKind(ElementKind.CLASS);
        this.typeElementBuilder.setEnclosingElement(enclosingElement);
        Element testElement = this.typeElementBuilder.createElement();
        testElements.add(testElement);

        this.unitUnderTest.process(testElements, testModel);

        Assert.assertEquals(testModel.getEntities().size(), 1, "Expected a model with a single entity.");
        Assert.assertEquals(testModel.getEntities().get(0).getName(), "Test");
    }

    @Test
    public void testProcessEntityWithExplicitName() {
        Set<Element> testElements = new HashSet<>();
        Model testModel = new Model();

        this.packageElementBuilder.setSimpleName("org.sw4j.test");
        this.packageElementBuilder.setQualifiedName("org.sw4j.test");
        Element enclosingElement = this.packageElementBuilder.createElement();

        this.typeElementBuilder.setSimpleName("Test");
        this.typeElementBuilder.setQualifiedName("org.sw4j.test.Test");
        this.typeElementBuilder.addAnnotation(Entity.class, new EntityMock("EntityName"));
        this.typeElementBuilder.setKind(ElementKind.CLASS);
        this.typeElementBuilder.setEnclosingElement(enclosingElement);
        Element testElement = this.typeElementBuilder.createElement();
        testElements.add(testElement);

        this.unitUnderTest.process(testElements, testModel);

        Assert.assertEquals(testModel.getEntities().size(), 1, "Expected a model with a single entity.");
        Assert.assertEquals(testModel.getEntities().get(0).getName(), "EntityName");
    }

}
