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
package org.sw4j.tool.annotation.jpa.util;

import java.util.HashSet;
import java.util.Set;
import javax.xml.xpath.XPathExpressionException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Uwe Plonus
 */
public abstract class TestSuperclass {

    /** The folder that contains all JPA classes to process. */
    private static final String ENTITY_PACKAGE =
            "src/test/java/org/sw4j/tool/annotation/jpa/entity/";

    /** The file to write the result to. */
    private static final String TEST_XML = "target/result/tool-jpa/test.xml";

    /** The option prefix for the generator output. */
    private static final String ANNOTATION_PROCESSOR_OPTION = "-Atool.jpa.output=test=" + TEST_XML;

    /** The utility class of the testResultFile. */
    private static TestUtil testUtil;

    private static Set<Node> visitedNodes;

    /** Default constructor. */
    public TestSuperclass() {
    }

    /**
     * Set up the test suite.
     *
     * @throws Exception when any exception occurs during set up.
     */
    @BeforeSuite
    public static void setUpSuite() throws Exception {
        testUtil = new TestUtil();
        testUtil.compileClasses(ENTITY_PACKAGE, TEST_XML,
                new String[]{
                    ANNOTATION_PROCESSOR_OPTION
                });

        visitedNodes = new HashSet<>();
    }

    /**
     * Tear down the test suite.
     *
     * @throws Exception when any exception occurs during tear down.
     */
    @AfterSuite
    public static void tearDownSuite() throws Exception {
        testUtil.checkVisitedNodes();
    }

    public Element getRootElement() {
        return testUtil.getRootElement();
    }

    public Node getNode(String path) throws XPathExpressionException {
        return testUtil.getNode(path);
    }

}
