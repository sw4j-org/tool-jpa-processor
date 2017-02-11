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
package org.sw4j.tool.annotation.jpa.generator;

import java.io.IOException;
import java.util.Properties;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.ProcessingEnvironment;
import org.sw4j.tool.annotation.jpa.generator.model.Model;

/**
 * An implementation of the {@link GeneratorService} used for testing. This generator does nothing and only throws
 * exceptions if configured so.
 *
 * @author Uwe Plonus
 */
public class TestGenerator implements GeneratorService {

    /** The prefix of the generator. */
    private static final String PREFIX = "test";

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
    public void setProperties(@Nullable final Properties properties) {
    }

    /**
     * Returns always {@code true} because it only throws exceptions when configured so.
     *
     * @return {@code true} if the generator can process models.
     */
    @Override
    public boolean canProcess() {
        return true;
    }

    /**
     * Processes the model and writes it to the output.
     *
     * @param model the model to process.
     * @param processingEnv the processing environment used to emit messages.
     * @throws IOException when an error occurs during the output.
     */
    @Override
    public void process(Model model, @Nonnull final ProcessingEnvironment processingEnv) throws IOException {
        if (TestGeneratorConfiguration.getInstance().processThrowsIOException()) {
            throw new IOException();
        }
    }


    /**
     * A singleton class to configure the {@link TestGenerator}.
     */
    public static class TestGeneratorConfiguration {

        private static final TestGeneratorConfiguration INSTANCE = new TestGeneratorConfiguration();

        private boolean processThrowsIOException;

        public static TestGeneratorConfiguration getInstance() {
            return INSTANCE;
        }

        public void processThrowsIOException(boolean processThrowsIOException) {
            this.processThrowsIOException = processThrowsIOException;
        }

        public boolean processThrowsIOException() {
            return this.processThrowsIOException;
        }

    }

}
