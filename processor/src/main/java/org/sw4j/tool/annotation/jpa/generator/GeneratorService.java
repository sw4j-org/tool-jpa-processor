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
import org.sw4j.tool.annotation.jpa.generator.model.Model;

/**
 * The {@code GeneratorService} is a service interface to output a model into a generic format.
 *
 * @author Uwe Plonus
 */
public interface GeneratorService {

    /**
     * Returns the prefix used by the implementing generator.
     *
     * @return the prefix of the generator.
     */
    @Nonnull
    String getPrefix();

    /**
     * Sets the the properties of the generator. The properties are used to configure the generator. The
     * recognized properties are generator dependent and must be described at the generator.
     *
     * @param properties the properties used to configure the generator.
     */
    void setProperties(@Nullable Properties properties);

    /**
     * Flag to indicate that the generator can process a model. For this the generator should be able to read the
     * properties file and process all needed properties.
     *
     * @return {@code true} if the properties file can be read and the generator can process a model.
     */
    boolean canProcess();

    /**
     * The model to process and to output.
     *
     * @param model the model to process.
     * @throws IOException if the output cannot be written.
     */
    void process(@Nonnull Model model) throws IOException;

}
