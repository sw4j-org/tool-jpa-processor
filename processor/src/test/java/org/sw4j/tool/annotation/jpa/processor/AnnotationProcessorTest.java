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
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.persistence.Entity;
import javax.tools.Diagnostic;
import org.sw4j.tool.annotation.jpa.generator.TestGenerator;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.MessagerMock;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.ProcessingEnvironmentMock;
import org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing.RoundEnvironmentMock;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element.EntityMock;
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

    private Set<TypeElement> handledAnnotations;

    @BeforeMethod
    public void setUp() {
        handledAnnotations = new HashSet<>();
        handledAnnotations.add(new TypeElementMock(new NameMock(""), null, ElementKind.ANNOTATION_TYPE, null, null));
    }

    @Test
    public void testProcessEmptyElementSet() {
        final Map<String, String> options = new HashMap<>();

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>());

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testProcessNonEntityElementSet() {
        final Map<String, String> options = new HashMap<>();
        final Map<Class<?>, ? extends Annotation> annotations = new HashMap<>();
        final Set<TypeElement> elements = new HashSet<>();
        TypeElementMock nonEntity = new TypeElementMock(new NameMock(""), annotations, ElementKind.CLASS, null, null);

        elements.add(nonEntity);

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(elements);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testProcessSingleEntityElementSet() throws Exception {
        final Map<String, String> options = new HashMap<>();
        final Map<Class<?>, Annotation> annotations = new HashMap<>();
        annotations.put(Entity.class, new EntityMock(""));
        final Set<TypeElement> elements = new HashSet<>();
        TypeElementMock enclosingElement = new TypeElementMock(new NameMock(""), new HashMap<Class<?>, Annotation>(),
                ElementKind.PACKAGE, null, null);
        TypeElementMock entity1 = new TypeElementMock(new NameMock("Entity"), annotations, ElementKind.CLASS,
                enclosingElement, new LinkedList<Element>());
        elements.add(entity1);

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(elements);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test(enabled = false)
    public void testProcessAnnotationProcessorException() throws Exception {
        final Map<String, String> options = new HashMap<>();
        final Map<Class<?>, Annotation> annotations = new HashMap<>();
        annotations.put(Entity.class, new EntityMock(""));
        final Set<TypeElement> elements = new HashSet<>();
        final TypeElementMock entity1 = new TypeElementMock(new NameMock("Entity"), annotations, ElementKind.CLASS,
                null, null);
        elements.add(entity1);

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(elements);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(messager.getMessages().get(0).getKind(), Diagnostic.Kind.ERROR,
                "Expected a message with level ERROR to be created.");
    }

    @Test
    public void testProcessSingleEntityNotTopLevel() throws Exception {
        final Map<String, String> options = new HashMap<>();
        final Map<Class<?>, Annotation> annotations = new HashMap<>();
        annotations.put(Entity.class, new EntityMock(""));
        final Set<TypeElement> elements = new HashSet<>();
        final TypeElementMock entity1 = new TypeElementMock(new NameMock("Entity"), annotations, ElementKind.INTERFACE,
                null, null);
        elements.add(entity1);

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(elements);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(messager.getMessages().get(0).getKind(), Diagnostic.Kind.WARNING,
                "Expected a message with level WARNING to be created.");
    }

    @Test
    public void testGeneratorServiceLoading() {
        final Map<String, String> options = new HashMap<>();

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>());
        roundEnv.processingOver(true);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithOptionsOnlyPrefix() {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test");

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>());
        roundEnv.processingOver(true);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

    }

    @Test
    public void testGeneratorServiceLoadingWithLoadProperties() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(false);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(false);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithLoadPropertiesException() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(false);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(true);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(messager.getMessages().get(0).getKind(), Diagnostic.Kind.ERROR,
                "Expected a message with level ERROR to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorNoProperties() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(false);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(false);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorWithProperties() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(false);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(false);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorCall() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(false);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(false);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 0, "Expected no message to be created.");
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorCallIOException() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        MessagerMock messager = new MessagerMock();
        ProcessingEnvironmentMock processingEnv = new ProcessingEnvironmentMock(options, messager);
        RoundEnvironmentMock roundEnv = new RoundEnvironmentMock(new HashSet<Element>());
        roundEnv.processingOver(true);

        TestGenerator.TestGeneratorConfiguration.getInstance().processThrowsIOException(true);
        TestGenerator.TestGeneratorConfiguration.getInstance().setPropertiesThrowsIOException(false);

        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(handledAnnotations, roundEnv);

        Assert.assertEquals(messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(messager.getMessages().get(0).getKind(), Diagnostic.Kind.ERROR,
                "Expected a message with level ERROR to be created.");
    }

}
