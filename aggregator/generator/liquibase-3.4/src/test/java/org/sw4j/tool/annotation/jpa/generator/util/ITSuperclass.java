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
package org.sw4j.tool.annotation.jpa.generator.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.xml.xpath.XPathExpressionException;
import org.sw4j.tool.annotation.jpa.test.util.ITUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This class is used for all integration test classes. This class compiles the entities and checks that all model
 * elements are tested. This means that either a sub class test fails because there is no node or this class throws an
 * exception because a model element is not tested.
 *
 * @author Uwe Plonus
 */
public abstract class ITSuperclass {

    /** The folder that contains all JPA classes to process. */
    private static final String ENTITY_PACKAGE = "src/test/java/org/sw4j/tool/annotation/jpa/generator/entity/";

    /** The file to write the result to. */
    private static final String TEST_PROPERTIES = "src/test/resources/integrationtest.properties";

    /** The option prefix for the generator output. */
    private static final String ANNOTATION_PROCESSOR_OPTION = "-Atool.jpa.properties=";

    /** The utility class of the testResultFile. */
    private static ITUtil testUtil;

    /** Default constructor. */
    public ITSuperclass() {
    }

    /**
     * Set up the test suite.
     *
     * @throws Exception when any exception occurs during set up.
     */
    @BeforeSuite
    public static void setUpSuite() throws Exception {
        testUtil = new ITUtil();
        Properties properties = new Properties();
        File propertiesFile = new File(TEST_PROPERTIES);
        if (propertiesFile.exists()) {
            properties.load(new FileInputStream(TEST_PROPERTIES));
        }
        testUtil.compileClasses(ENTITY_PACKAGE, properties.getProperty("lb34.fullChangelogFile"),
                new String[]{
                    new StringBuilder(ANNOTATION_PROCESSOR_OPTION).append(TEST_PROPERTIES).toString(),
                });
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

    /**
     * Returns the root element of the model.
     *
     * @return the root element.
     */
    public Element getRootElement() {
        return testUtil.getRootElement();
    }

    /**
     * Returns the node for the given XPath from the model.
     *
     * @param path the XPath expression.
     * @return the node with the given path.
     * @throws XPathExpressionException if the XPath expression is invalid.
     */
    public Node getNode(String path) throws XPathExpressionException {
        return testUtil.getNode(path);
    }

    /**
     * Returns the given attribute of the given element.
     *
     * @param name the name of the attribute to get.
     * @param node the element to get the attribute from.
     * @return the named attribute.
     */
    public Node getAttribute(String name, Node node) {
        return testUtil.getAttribute(name, node);
    }

}
