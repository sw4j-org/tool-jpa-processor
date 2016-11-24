/*
 * Copyright (C) 2016 Uwe Plonus
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.persistence.Entity;
import javax.tools.Diagnostic;
import org.sw4j.tool.annotation.jpa.generator.GeneratorService;
import org.sw4j.tool.annotation.jpa.generator.model.Model;
import org.sw4j.tool.annotation.jpa.processor.exceptions.AnnotationProcessorException;
import org.sw4j.tool.annotation.jpa.processor.exceptions.EntityNotTopLevelClassException;
import org.sw4j.tool.annotation.jpa.processor.exceptions.MissingEntityAnnotationException;

/**
 * An annotation processor to handle JPA annotations.
 *
 * @author Uwe Plonus
 */
@SupportedAnnotationTypes("javax.persistence.Entity")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedOptions(AnnotationProcessor.PROPERTIES_OPTION)
public class AnnotationProcessor extends AbstractProcessor {

    /** The option of the annotation processor to set output directory. */
    public static final String PROPERTIES_OPTION = "tool.jpa.properties";

    /** The generated entity model. */
    private final Model model;

    /** The processor to handle entities. */
    private final EntityProcessor entityProcessor;

    /**
     * The default constructor.
     */
    public AnnotationProcessor() {
        this.model = new Model();
        this.entityProcessor = new EntityProcessor();
    }

    /**
     * Initializes the processor with the processing environment. An {@code IllegalStateException} will be thrown if
     * this method is called more than once on the same object.
     *
     * @param processingEnv environment to access facilities the tool framework provides to the processor.
     * @throws IllegalStateException if this method is called more than once.
     */
    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.entityProcessor.init(this.processingEnv);
    }

    /**
     * Processes the annotations given in the {@code annotations} variable.
     *
     * @param annotations the annotations that are handled.
     * @param roundEnv the round environment to handle annotations.
     * @return always {@code false} because this processor never claims an annotation.
     */
    @Override
    public boolean process(@Nonnull final Set<? extends TypeElement> annotations,
            @Nonnull final RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getRootElements();
        for (Element element: elements) {
            Entity entity = element.getAnnotation(Entity.class);
            if (entity != null) {
                try {
                    this.entityProcessor.process(element, model);
                } catch (EntityNotTopLevelClassException | MissingEntityAnnotationException ex) {
                    this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, ex.getMessage(), element);
                } catch (AnnotationProcessorException ex) {
                    this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, ex.getMessage(), element);
                }
            }
        }

        if (roundEnv.processingOver()) {
            String outputOption = this.processingEnv.getOptions().get(PROPERTIES_OPTION);
            ServiceLoader<GeneratorService> generatorServiceLoader = setupGenerators(outputOption);
            Iterator<GeneratorService> generators = generatorServiceLoader.iterator();
            while (generators.hasNext()) {
                GeneratorService generator = generators.next();
                if (generator.canProcess()) {
                    try {
                        generator.process(model);
                    } catch (IOException ioex) {
                        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                                ioex.toString());
                    }
                }
            }
        }

        return false;
    }

    /**
     * Load all available generators and set them up with their properties.
     *
     * @param outputOption the configuration string provided to the annotation processor.
     * @return the configured generators.
     */
    @Nonnull
    private ServiceLoader<GeneratorService> setupGenerators(@Nullable final String outputOption) {
        Map<String, String> outputParts = new HashMap<>();
        if (outputOption != null) {
            for (String o: outputOption.split(",")) {
                String[] singleOption = o.split("=");
                if (singleOption.length == 2) {
                    outputParts.put(singleOption[0], singleOption[1]);
                }
            }
        }
        ServiceLoader<GeneratorService> generatorServiceLoader = ServiceLoader.load(GeneratorService.class);
        Iterator<GeneratorService> generators = generatorServiceLoader.iterator();
        while (generators.hasNext()) {
            GeneratorService generator = generators.next();
            if (outputParts.containsKey(generator.getPrefix())) {
                try {
                    generator.setPropertiesFileName(outputParts.get(generator.getPrefix()));
                } catch (IOException ioex) {
                    this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, ioex.toString());
                }
            }
        }
        return generatorServiceLoader;
    }

}
