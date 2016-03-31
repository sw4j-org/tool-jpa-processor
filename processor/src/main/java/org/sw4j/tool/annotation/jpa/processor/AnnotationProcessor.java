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
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import org.sw4j.tool.annotation.jpa.generator.GeneratorService;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.sw4j.tool.annotation.jpa.generator.model.Model;

/**
 * An annotation processor to handle JPA annotations.
 *
 * @author Uwe Plonus
 */
@SupportedAnnotationTypes("javax.persistence.Entity")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedOptions(AnnotationProcessor.OUTPUT_OPTION)
public class AnnotationProcessor extends AbstractProcessor {

    /** The option of the annotation processor to set output directory. */
    public static final String OUTPUT_OPTION = "tool.jpa.output";

    private final Model model;

    /**
     * The default constructor.
     */
    public AnnotationProcessor() {
        model = new Model();
    }

    /**
     * Processes the annotations given in the {@code annotations} variable.
     *
     * @param annotations the annotations that are handled.
     * @param roundEnv the round environment to handle annotations.
     * @return always {@code false} because this processor never claims an annotation.
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations,
            final RoundEnvironment roundEnv) {
        String outputOption = this.processingEnv.getOptions().get(OUTPUT_OPTION);
        Map<String, String> outputParts = new HashMap<>();
        for (String o: outputOption.split(",")) {
            String[] singleOption = o.split("=");
            if (singleOption.length == 2) {
                outputParts.put(singleOption[0], singleOption[1]);
            }
        }

        ServiceLoader<GeneratorService> generatorService =
                ServiceLoader.load(GeneratorService.class);
        Iterator<GeneratorService> generators = generatorService.iterator();
        while (generators.hasNext()) {
            GeneratorService generator = generators.next();
            if (outputParts.containsKey(generator.getPrefix())) {
                generator.setOutput(outputParts.get(generator.getPrefix()));
            }
        }

        Set<? extends Element> elements = roundEnv.getRootElements();
        for (Element element: elements) {
            javax.persistence.Entity entity = element.getAnnotation(javax.persistence.Entity.class);
            if (entity != null) {
                processEntity(element, model);
            }
        }

        if (roundEnv.processingOver()) {
            generators = generatorService.iterator();
            while (generators.hasNext()) {
                GeneratorService generator = generators.next();
                try {
                    generator.process(model);
                } catch (IOException ioex) {
                    this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            ioex.toString());
                }
            }
        }

        return false;
    }

    /**
     * Process a single entity annotated with {@code @Entity}.
     *
     * @param element the element to process (must be an {@code @Entity}.
     * @param model the model where the final entity is added to.
     */
    private void processEntity(Element element, Model model) {
        javax.persistence.Entity entityAnnotation =
                element.getAnnotation(javax.persistence.Entity.class);
        Entity entity = new Entity();
        if ("".equals(entityAnnotation.name())) {
            entity.setName(element.getSimpleName().toString());
        } else {
            entity.setName(entityAnnotation.name());
        }
        model.addEntity(entity);
    }

}
