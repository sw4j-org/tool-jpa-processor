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

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXB;
import org.sw4j.tool.annotation.jpa.generator.model.Model;

/**
 * An implementation of the {@link GeneratorService} used for testing. It writes the model to a XML
 * file.
 *
 * @author Uwe Plonus
 */
public class TestGenerator implements GeneratorService {

    /** The prefix of the generator. */
    private static final String PREFIX = "test";

    /** The file name for the output if no filename is given. */
    private static final String DEFAULT_NAME = "test.xml";

    /** The file to write the model to. */
    private File outputFile;

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
     * Sets the output of the generator.
     *
     * @param output the output.
     */
    @Override
    public void setOutput(String output) {
        File outFile = new File(output);
        if (outFile.isDirectory()) {
            outFile = new File(outFile, DEFAULT_NAME);
        }
        outputFile = outFile;
    }

    /**
     * Processes the model and writes it to the output.
     *
     * @param model the model to process.
     * @throws IOException when an error occurs during the output.
     */
    @Override
    public void process(Model model) throws IOException {
        outputFile.getParentFile().mkdirs();
        JAXB.marshal(model, outputFile);
    }

}
