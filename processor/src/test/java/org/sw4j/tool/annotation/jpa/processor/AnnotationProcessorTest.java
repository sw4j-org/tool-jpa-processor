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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.persistence.Entity;
import javax.tools.Diagnostic;
import org.sw4j.tool.annotation.jpa.generator.TestGenerator;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.MessagerMock;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.ProcessingEnvironmentMock;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.RoundEnvironmentMock;
import org.sw4j.tool.annotation.jpa.processor.mock.persistence.EntityMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.NameMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.TypeElementMock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class AnnotationProcessorTest {

    private AnnotationProcessor unitUnderTest;

    private ProcessingEnvironment processingEnv;

    private Set<TypeElement> handledAnnotations;

    private Map<String, String> options;

    private MessagerMock messager;

    @BeforeMethod
    public void setUp() {
        this.options = new HashMap<>();
        this.messager = new MessagerMock();

        this.unitUnderTest = new AnnotationProcessor();
        this.processingEnv = new ProcessingEnvironmentMock(this.options, this.messager);
        this.unitUnderTest.init(this.processingEnv);

        this.handledAnnotations = new HashSet<>();
        this.handledAnnotations.add(new TypeElementMock(new NameMock(""), null, ElementKind.ANNOTATION_TYPE, null,
                null));
    }

    @Test
    public void testProcessEmptyElementSet() {
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>(), new HashSet<Element>());

        this.unitUnderTest.process(this.handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testProcessNonEntityElementSet() {
        final Map<Class<?>, ? extends Annotation> annotations = new HashMap<>();
        final Set<TypeElement> elements = new HashSet<>();
        TypeElementMock nonEntity = new TypeElementMock(new NameMock(""), annotations, ElementKind.CLASS, null, null);

        elements.add(nonEntity);

        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(elements, new HashSet<Element>());

        this.unitUnderTest.process(this.handledAnnotations, roundEnv);

        Assert.assertEquals(this.messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testProcessSingleEntityElementSet() throws Exception {
        final Map<Class<?>, Annotation> annotations = new HashMap<>();
        annotations.put(Entity.class, new EntityMock(""));
        final Set<TypeElement> elements = new HashSet<>();
        TypeElementMock enclosingElement = new TypeElementMock(new NameMock(""), new HashMap<Class<?>, Annotation>(),
                ElementKind.PACKAGE, null, null);
        TypeElementMock entity1 = new TypeElementMock(new NameMock("Entity"), annotations, ElementKind.CLASS,
                enclosingElement, new LinkedList<Element>());
        elements.add(entity1);

        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(elements, elements);

        this.unitUnderTest.process(this.handledAnnotations, roundEnv);

        Assert.assertEquals(this.messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testGeneratorServiceLoading() {
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>(), new HashSet<Element>());
        roundEnv.processingOver(true);

        this.unitUnderTest.process(this.handledAnnotations, roundEnv);

        Assert.assertEquals(this.messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithOptionsOnlyPrefix() {
        this.options.put("tool.jpa.properties", "test");

        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>(), new HashSet<Element>());
        roundEnv.processingOver(true);

        this.unitUnderTest.process(this.handledAnnotations, roundEnv);
    }

    @Test
    public void testGeneratorServiceLoadingWithLoadProperties() throws Exception {
        this.options.put("tool.jpa.properties", "test=test.properties");

        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>(), new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(false);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(false);

        this.unitUnderTest.process(this.handledAnnotations, roundEnv);

        Assert.assertEquals(this.messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithLoadPropertiesException() throws Exception {
        this.options.put("tool.jpa.properties", "test=test.properties");

        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>(), new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(false);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(true);

        this.unitUnderTest.process(this.handledAnnotations, roundEnv);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.ERROR,
                "Expected a message with level ERROR to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorNoProperties() throws Exception {
        this.options.put("tool.jpa.properties", "test=test.properties");

        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>(), new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(false);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(false);

        this.unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(this.messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorWithProperties() throws Exception {
        this.options.put("tool.jpa.properties", "test=test.properties");

        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>(), new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(false);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(false);

        this.unitUnderTest.process(this.handledAnnotations, roundEnv);

        Assert.assertEquals(this.messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorCall() throws Exception {
        this.options.put("tool.jpa.properties", "test=test.properties");

        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>(), new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(false);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(false);

        this.unitUnderTest.process(this.handledAnnotations, roundEnv);

        Assert.assertEquals(this.messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorCallIOException() throws Exception {
        this.options.put("tool.jpa.properties", "test=test.properties");

        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>(), new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(true);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(false);

        this.unitUnderTest.process(this.handledAnnotations, roundEnv);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.ERROR,
                "Expected a message with level ERROR to be created.");
    }

}
