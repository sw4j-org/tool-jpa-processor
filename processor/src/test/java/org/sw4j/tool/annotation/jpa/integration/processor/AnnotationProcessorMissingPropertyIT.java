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
package org.sw4j.tool.annotation.jpa.integration.processor;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test the calling of the annotation processor without any properties.
 *
 * @author Uwe Plonus
 */
public class AnnotationProcessorMissingPropertyIT {

    /** The folder that contains all JPA classes to process. */
    private static final String ENTITY_PACKAGE = "src/test/java/org/sw4j/tool/annotation/jpa/entity/";

    /** The target folder for the compiled classes. */
    private static final String TARGET_FOLDER = "target/jpa2-classes/";

    /** The option prefix for the generator output. */
    private static final String ANNOTATION_PROCESSOR_OPTION = "-Atool.jpa.properties=missing.properties";

    @Test
    public void test() {
        File entityFolder = new File(ENTITY_PACKAGE);
        List<File> files = new LinkedList<>();
        getFiles(entityFolder, files);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        Assert.assertNotNull(compiler, "Need a java compiler for executing the tests.");
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, Charset.forName("UTF-8"));

        File classesFolder = new File(TARGET_FOLDER);
        classesFolder.mkdirs();

        Iterable<? extends JavaFileObject> entities =
                fileManager.getJavaFileObjectsFromFiles(files);
        List<String> opts = new LinkedList<>();
        opts.add("-d");
        opts.add(TARGET_FOLDER);
        opts.addAll(Arrays.asList(new String[]{ANNOTATION_PROCESSOR_OPTION}));
        Writer writer = new StringWriter();
        compiler.getTask(writer, fileManager, null, opts, null, entities).call();
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

}
