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
    String getPrefix();

    /**
     * Sets the output of the generator. This may be a directory or a file.
     *
     * <p>
     * Each generator must be prepared to get a directory instead of a file. In this case the
     * generator should append a default file name.
     * </p>
     *
     * <p>
     * If the generator needs a directory and gets a file then it should remove the file name and
     * use the remaining directory.
     * </p>
     *
     * @param output the directory or file to use for the output.
     */
    void setOutput(String output);

    /**
     * The model to process and to output.
     *
     * @param model the model to process.
     * @throws IOException if the output cannot be written.
     */
    void process(Model model) throws IOException;

}
