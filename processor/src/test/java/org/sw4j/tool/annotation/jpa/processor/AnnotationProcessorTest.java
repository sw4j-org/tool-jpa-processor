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
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class AnnotationProcessorTest {

    private static final String ENTITY_PACKAGE = "src/test/java/org/sw4j/tool/annotation/jpa/entity/";

    private static final String ANNOTATION_PROCESSOR_OPTION =
            "-Atool.jpa.output=test=target/tool-jpa/example.xml";

    public AnnotationProcessorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        File entityFolder = new File(ENTITY_PACKAGE);
        List<File> files = new LinkedList<>();
        getFiles(entityFolder, files);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        Assert.assertNotNull(compiler, "Need a java compiler for executing the tests.");
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null,
                Charset.forName("UTF-8"));

        Iterable<? extends JavaFileObject> entities =
                fileManager.getJavaFileObjectsFromFiles(files);
        String[] options = new String[]{
            ANNOTATION_PROCESSOR_OPTION
        };
        compiler.getTask(null, fileManager, null, Arrays.asList(options), null, entities).call();
    }

    private static void getFiles(File path, List<File> javaFiles) {
        if (path.isDirectory()) {
            File[] files = path.listFiles();
            for (File file: files) {
                getFiles(file, javaFiles);
            }
        } else if (path.getName().endsWith(".java")) {
            javaFiles.add(path);
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test
    public void test() {
        Assert.assertTrue(true);
    }

}
