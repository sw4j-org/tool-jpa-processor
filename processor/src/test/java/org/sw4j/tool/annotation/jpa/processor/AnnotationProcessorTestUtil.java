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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * An utility class to support testing of the annotation processor.
 *
 * @author Uwe Plonus
 */
public class AnnotationProcessorTestUtil {

    /** The target folder for the compiled classes. */
    private static final String TARGET_FOLDER = "target/jpa-classes/";

    /**
     * Method to compile all classes in the given folder. The generated classes are placed in the
     * folder {@code target/jpa-classes/}.
     *
     * @param folder the folder that contains the JPA classes to process.
     * @param options the options for the compiling (primary the options for the annotation
     *  processor).
     */
    public void compileClasses(String folder, String[] options) {
        File entityFolder = new File(folder);
        List<File> files = new LinkedList<>();
        getFiles(entityFolder, files);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        Assert.assertNotNull(compiler, "Need a java compiler for executing the tests.");
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null,
                Charset.forName("UTF-8"));

        File classesFolder = new File(TARGET_FOLDER);
        classesFolder.mkdirs();

        Iterable<? extends JavaFileObject> entities =
                fileManager.getJavaFileObjectsFromFiles(files);
        List<String> opts = new LinkedList<>();
        opts.add("-d");
        opts.add(TARGET_FOLDER);
        opts.addAll(Arrays.asList(options));
        compiler.getTask(null, fileManager, null, opts, null, entities).call();
    }

    /**
     * Helper method to collect all java files (with suffix {@code .java}) inside a folder. If the
     * folder contains subfolders this are also scanned for java classes.
     *
     * @param folder the folder to scan for java classes.
     * @param javaFiles the resulting list that contains all java files.
     */
    private void getFiles(File folder, List<File> javaFiles) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file: files) {
                getFiles(file, javaFiles);
            }
        } else if (folder.getName().endsWith(".java")) {
            javaFiles.add(folder);
        }
    }

    /**
     * This method reads in the given XML file and returns the DOM of it.
     *
     * @param xmlFile the XML file to read in.
     * @return the DOM of the read file.
     * @throws ParserConfigurationException when the parser cannot be created.
     * @throws SAXException when the parsing fails.
     * @throws IOException when the IO fails.
     */
    public Document readXMLResult(String xmlFile) throws ParserConfigurationException,
            SAXException, IOException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return builder.parse(new File(xmlFile));
    }

}
