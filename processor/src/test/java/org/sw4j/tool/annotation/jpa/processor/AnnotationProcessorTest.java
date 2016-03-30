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

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

/**
 * Class to testResultFile the {@link AnnotationProcessor}.
 *
 * @author Uwe Plonus
 */
public class AnnotationProcessorTest {

    /** The folder that contains all JPA classes to process. */
    private static final String ENTITY_PACKAGE =
            "src/test/java/org/sw4j/tool/annotation/jpa/entity/";

    /** The file to write the result to. */
    private static final String TEST_XML = "target/result/tool-jpa/test.xml";

    /** The option prefix for the generator output. */
    private static final String ANNOTATION_PROCESSOR_OPTION = "-Atool.jpa.output=test=" + TEST_XML;

    /** The utility class of the testResultFile. */
    private static AnnotationProcessorTestUtil testUtil;

    /** The document that is used to testResultFile the result. */
    private static Document resultDocument;

    /** Default constructor. */
    public AnnotationProcessorTest() {
    }

    /**
     * Set up the test suite.
     *
     * @throws Exception if any exception occurs during set up.
     */
    @BeforeSuite
    public static void setUpSuite() throws Exception {
        testUtil = new AnnotationProcessorTestUtil();
        testUtil.compileClasses(ENTITY_PACKAGE,
                new String[]{
                    ANNOTATION_PROCESSOR_OPTION
                });

        resultDocument = testUtil.readXMLResult(TEST_XML);
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
    public void testResultFile() {
        Assert.assertNotNull(resultDocument.getDocumentElement(), "Expected a document to be read.");
        Assert.assertEquals(resultDocument.getDocumentElement().getNodeName(), "model",
                "Expected the root element to be named \"model\".");
    }

}
