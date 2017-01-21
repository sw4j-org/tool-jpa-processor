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
package org.sw4j.tool.annotation.jpa.generator.liquibase.v34.integration;

import java.util.Properties;
import org.sw4j.tool.annotation.jpa.test.util.ITUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class LiquibaseGeneratorNoPropertiesIT {

    /** The folder that contains all JPA classes to process. */
    private static final String ENTITY_PACKAGE = "src/test/java/org/sw4j/tool/annotation/jpa/generator/entity/";

    /** The file to write the result to. */
    private static final String TEST_PROPERTIES = "missing.properties";

    /** The option prefix for the generator output. */
    private static final String ANNOTATION_PROCESSOR_OPTION = "-Atool.jpa.properties=" + TEST_PROPERTIES;

    /** The utility class of the testResultFile. */
    private static ITUtil testUtil;

    /** Default constructor. */
    public LiquibaseGeneratorNoPropertiesIT() {
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
        testUtil.compileClasses(ENTITY_PACKAGE, properties.getProperty("lb34.fullChangelogFile"),
                new String[]{
                    ANNOTATION_PROCESSOR_OPTION
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

    @Test
    public void testRootElement() {
    }

}
