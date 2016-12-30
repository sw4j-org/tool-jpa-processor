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
package org.sw4j.tool.annotation.jpa.generator.liquibase.v34;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.Nonnull;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import javax.xml.bind.JAXB;
import org.sw4j.tool.annotation.jpa.generator.GeneratorService;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.DatabaseChangeLog;
import org.sw4j.tool.annotation.jpa.generator.liquibase.v34.jaxb.ObjectFactory;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.sw4j.tool.annotation.jpa.generator.model.Model;

/**
 * A generator to create changelogs for Liquibase 3.4.
 *
 * @author uwe
 */
public class LiquibaseGenerator implements GeneratorService {

    /** The prefix of the generator. */
    private static final String PREFIX = "lb34";

    /** The changeset generator used by this generator. */
    private final ChangeSetGenerator changesetGenerator = new ChangeSetGenerator();

    /** The file to write the model to. */
    private File fullChangelogFile;

    /** Flag to indicate that a model can be processed. */
    private boolean canProcess;

    /**
     * Default constructor which is needed to use the service infrastructure of Java.
     */
    public LiquibaseGenerator() {
    }

    /**
     * Returns the prefix of this Generator.
     *
     * @return the prefix.
     */
    @Override
    public String getPrefix() {
        return PREFIX;
    }

    /**
     * Sets the properties of the generator.
     *
     * @param properties the properties.
     */
    @Override
    public void setProperties(@Nonnull final Properties properties) {
        String fullChangeLogFileName = properties.getProperty("fullChangelogFile");
        if (fullChangeLogFileName != null) {
            this.fullChangelogFile = new File(fullChangeLogFileName);
            this.canProcess = true;
        }
    }

    /**
     * Returns {@code true} if this generator can process a model. The prerequisite for processing a model is that the
     * processor is configured with a properties file.
     *
     * @return {@code true} if this generator can process a model.
     */
    @Override
    public boolean canProcess() {
        return this.canProcess;
    }

    /**
     * Processes the model and writes it to the output.
     *
     * @param model the model to process.
     * @param processingEnv the processing environment used to emit messages.
     * @throws IOException when an error occurs during the output.
     */
    @Override
    public void process(@Nonnull final Model model, @Nonnull final ProcessingEnvironment processingEnv)
            throws IOException {
        if (canProcess()) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Started Liquibase 3.4 generator");
            ObjectFactory of = new ObjectFactory();
            DatabaseChangeLog changeLog = of.createDatabaseChangeLog();

            for (Entity entity: model.getEntities()) {
                this.changesetGenerator.handleEntity(changeLog, entity);
            }

            fullChangelogFile.getParentFile().mkdirs();
            JAXB.marshal(changeLog, fullChangelogFile);
        }
    }

}
