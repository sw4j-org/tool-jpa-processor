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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.persistence.Entity;
import javax.tools.Diagnostic;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Verifications;
import org.sw4j.tool.annotation.jpa.generator.GeneratorService;
import org.sw4j.tool.annotation.jpa.generator.model.Model;
import org.sw4j.tool.annotation.jpa.processor.exceptions.AnnotationProcessorException;
import org.sw4j.tool.annotation.jpa.processor.exceptions.EntityNotTopLevelClassException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class AnnotationProcessorTest {

    @Mocked
    private GeneratorService generatorService;

    @Mocked
    private ServiceLoader<GeneratorService> generatorServiceLoader;

    @Mocked
    private Iterator<GeneratorService> generators;

    @Mocked
    private Set<? extends TypeElement> annotations;

    @Mocked
    private RoundEnvironment roundEnv;

    @Mocked
    private TypeElement nonEntity;

    @Mocked
    private TypeElement entity1;

    @Mocked
    private Entity entityAnnotation1;

    @Mocked
    private ProcessingEnvironment processingEnv;

    @Injectable
    private Model model;

    @Injectable
    private EntityProcessor entityProcessor;

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testProcessEmptyElementSet() {
        final Set<Element> elements = new HashSet<>();

        new Expectations() {{
            roundEnv.processingOver(); result = false;
            roundEnv.getRootElements(); result = elements;
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect no message
            processingEnv.getMessager(); times = 0;
        }};
    }

    @Test
    public void testProcessNonEntityElementSet() {
        final Set<Element> elements = new HashSet<>();
        elements.add(nonEntity);

        new Expectations() { {
            roundEnv.processingOver(); result = false;
            roundEnv.getRootElements(); result = elements;

            nonEntity.getAnnotation(Entity.class); result = null;
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect no message
            processingEnv.getMessager(); times = 0;
        }};
    }

    @Test
    public void testProcessSingleEntityElementSet() throws Exception {
        final Set<Element> elements = new HashSet<>();
        elements.add(entity1);

        new Expectations() {{
            roundEnv.processingOver(); result = false;
            roundEnv.getRootElements(); result = elements;

            entity1.getAnnotation(Entity.class); result = entityAnnotation1;
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor(model, entityProcessor);
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            entityProcessor.process(entity1, model); times = 1;
            // Expect no message
            processingEnv.getMessager(); times = 0;
        }};
    }

    @Test
    public void testProcessAnnotationProcessorException() throws Exception {
        final Set<Element> elements = new HashSet<>();
        elements.add(entity1);

        new Expectations() {{
            roundEnv.processingOver(); result = false;
            roundEnv.getRootElements(); result = elements;

            entity1.getAnnotation(Entity.class); result = entityAnnotation1;

            entityProcessor.process(entity1, model); times = 1;
            result = new AnnotationProcessorException("TestEntity");
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor(model, entityProcessor);
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect a message with level error
            processingEnv.getMessager(); times = 1;
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, anyString, entity1); times = 1;
        }};
    }

    @Test
    public void testProcessSingleEntityNotTopLevel() throws Exception {
        final Set<Element> elements = new HashSet<>();
        elements.add(entity1);

        new Expectations() {{
            roundEnv.processingOver(); result = false;
            roundEnv.getRootElements(); result = elements;

            entity1.getAnnotation(Entity.class); result = entityAnnotation1;

            entityProcessor.process(entity1, model); times = 1;
            result = new EntityNotTopLevelClassException("TestEntity");
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor(model, entityProcessor);
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect a message with level warning
            processingEnv.getMessager(); times = 1;
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, anyString, entity1); times = 1;
        }};
    }

    @Test
    public void testGeneratorServiceLoading() {
        final Map<String, String> options = new HashMap<>();

        new Expectations() {{
            roundEnv.processingOver(); result = true;
            processingEnv.getOptions(); result = options;
            ServiceLoader.load(GeneratorService.class); result = generatorServiceLoader;
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect no message
            processingEnv.getMessager(); times = 0;
        }};
    }

    @Test
    public void testGeneratorServiceLoadingWithOptionsOnlyPrefix() {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test");

        new Expectations() {{
            roundEnv.processingOver(); result = true;
            processingEnv.getOptions(); result = options;
            ServiceLoader.load(GeneratorService.class); result = generatorServiceLoader;
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect no message
            processingEnv.getMessager(); times = 0;
        }};
    }

    @Test
    public void testGeneratorServiceLoadingWithOptionsAndInputStreamException() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        new Expectations() {{
            roundEnv.processingOver(); result = true;
            processingEnv.getOptions(); result = options;
            new MockUp<FileInputStream>() {
                @Mock void $init(String file) throws IOException {
                    throw new IOException();
                }
                @Mock void close(){}
            };
            ServiceLoader.load(GeneratorService.class); result = generatorServiceLoader;
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect a message with level error
            processingEnv.getMessager(); times = 1;
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, anyString); times = 1;
        }};
    }

    @Test
    public void testGeneratorServiceLoadingWithLoadProperties() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        new Expectations() {{
            roundEnv.processingOver(); result = true;
            processingEnv.getOptions(); result = options;
            new MockUp<Properties>() {
                @Mock void $init() {}
                @Mock void load(InputStream is) {}
            };
            new MockUp<FileInputStream>() {
                @Mock void $init(String file) {}
                @Mock void close(){}
            };
            ServiceLoader.load(GeneratorService.class); result = generatorServiceLoader;
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect no message
            processingEnv.getMessager(); times = 0;
        }};
    }

    @Test
    public void testGeneratorServiceLoadingWithLoadPropertiesException() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        new Expectations() {{
            roundEnv.processingOver(); result = true;
            processingEnv.getOptions(); result = options;
            new MockUp<Properties>() {
                @Mock void $init() {}
                @Mock void load(InputStream is) throws IOException {
                    throw new IOException();
                }
            };
            new MockUp<FileInputStream>() {
                @Mock void $init(String file) {}
                @Mock void close() {}
            };
            ServiceLoader.load(GeneratorService.class); result = generatorServiceLoader;
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect a message with level error
            processingEnv.getMessager(); times = 1;
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, anyString); times = 1;
        }};
    }

    @Test
    public void testGeneratorServiceLoadingWithInputStreamCloseException() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        new Expectations() {{
            roundEnv.processingOver(); result = true;
            processingEnv.getOptions(); result = options;
            new MockUp<Properties>() {
                @Mock void $init() {}
                @Mock void load(InputStream is) {}
            };
            new MockUp<FileInputStream>() {
                @Mock void $init(String file) {}
                @Mock void close() throws IOException {
                    throw new IOException();
                }
            };
            ServiceLoader.load(GeneratorService.class); result = generatorServiceLoader;
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect a message with level error
            processingEnv.getMessager(); times = 1;
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, anyString); times = 1;
        }};
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorNoProperties() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        new Expectations() {{
            roundEnv.processingOver(); result = true;
            processingEnv.getOptions(); result = options;
            new MockUp<Properties>() {
                @Mock void $init() {}
                @Mock void load(InputStream is) {}
            };
            new MockUp<FileInputStream>() {
                @Mock void $init(String file) {}
                @Mock void close(){}
            };
            ServiceLoader.load(GeneratorService.class); result = generatorServiceLoader;
            generatorServiceLoader.iterator(); result = generators;
            generators.hasNext(); returns(true, false);
            generators.next(); result = generatorService;
            generatorService.getPrefix(); result = "";
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect no message
            processingEnv.getMessager(); times = 0;
        }};
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorWithProperties() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        new Expectations() {{
            roundEnv.processingOver(); result = true;
            processingEnv.getOptions(); result = options;
            new MockUp<Properties>() {
                @Mock void $init() {}
                @Mock void load(InputStream is) {}
            };
            new MockUp<FileInputStream>() {
                @Mock void $init(String file) {}
                @Mock void close(){}
            };
            ServiceLoader.load(GeneratorService.class); result = generatorServiceLoader;
            generatorServiceLoader.iterator(); result = generators;
            generators.hasNext(); returns(true, false);
            generators.next(); result = generatorService;
            generatorService.getPrefix(); result = "test";
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect no message
            processingEnv.getMessager(); times = 0;
        }};
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorCall() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        new Expectations() {{
            roundEnv.processingOver(); result = true;
            processingEnv.getOptions(); result = options;
            new MockUp<Properties>() {
                @Mock void $init() {}
                @Mock void load(InputStream is) {}
            };
            new MockUp<FileInputStream>() {
                @Mock void $init(String file) {}
                @Mock void close(){}
            };
            ServiceLoader.load(GeneratorService.class); result = generatorServiceLoader;
            generatorServiceLoader.iterator(); result = generators;
            generators.hasNext(); returns(true, false, true, false);
            generators.next(); result = generatorService;
            generatorService.getPrefix(); result = "test";
            generatorService.process((Model)any);
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect no message
            processingEnv.getMessager(); times = 0;
        }};
    }

    @Test
    public void testGeneratorServiceLoadingWithGeneratorCallIOException() throws Exception {
        final Map<String, String> options = new HashMap<>();
        options.put("tool.jpa.properties", "test=test.properties");

        new Expectations() {{
            roundEnv.processingOver(); result = true;
            processingEnv.getOptions(); result = options;
            new MockUp<Properties>() {
                @Mock void $init() {}
                @Mock void load(InputStream is) {}
            };
            new MockUp<FileInputStream>() {
                @Mock void $init(String file) {}
                @Mock void close(){}
            };
            ServiceLoader.load(GeneratorService.class); result = generatorServiceLoader;
            generatorServiceLoader.iterator(); result = generators;
            generators.hasNext(); returns(true, false, true, false);
            generators.next(); result = generatorService;
            generatorService.getPrefix(); result = "test";
            generatorService.process((Model)any); result = new IOException();
        }};
        AnnotationProcessor unitUnderTest = new AnnotationProcessor();
        unitUnderTest.init(processingEnv);

        unitUnderTest.process(annotations, roundEnv);

        new Verifications() {{
            // Expect a message with level error
            processingEnv.getMessager(); times = 1;
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, anyString); times = 1;
        }};
    }

}
