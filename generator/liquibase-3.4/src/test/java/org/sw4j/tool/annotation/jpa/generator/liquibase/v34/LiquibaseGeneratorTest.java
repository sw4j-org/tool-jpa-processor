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
package org.sw4j.tool.annotation.jpa.generator.liquibase.v34;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import org.sw4j.tool.annotation.jpa.generator.model.Model;
import org.sw4j.tool.annotation.jpa.test.mock.annotation.processing.MessagerMock;
import org.sw4j.tool.annotation.jpa.test.mock.annotation.processing.ProcessingEnvironmentMock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class LiquibaseGeneratorTest {

    private LiquibaseGenerator unitUnderTest;

    private Properties properties;

    private Map<String, String> options;

    private ProcessingEnvironment processingEnv;

    private MessagerMock messager;

    @BeforeMethod
    public void setUp() {
        this.properties = new Properties();
        this.options = new HashMap<>();
        this.messager = new MessagerMock();

        this.processingEnv = new ProcessingEnvironmentMock(this.options, this.messager);
        this.unitUnderTest = new LiquibaseGenerator();
    }

    @Test
    public void testPrefix() {
        Assert.assertEquals(this.unitUnderTest.getPrefix(), "lb34", "Expected the prefix \"lb34\".");
    }

    @Test
    public void testSetPropertiesNoFile() {
        this.properties.setProperty("dummy", "dummy");

        this.unitUnderTest.setProperties(this.properties);

        Assert.assertFalse(this.unitUnderTest.canProcess(), "Expected the processor to not be able to process models.");
    }

    @Test
    public void testSetProperties() {
        this.properties.setProperty("fullChangelogFile", "target/generated-resources/fullChangelog.xml");

        this.unitUnderTest.setProperties(this.properties);

        Assert.assertTrue(this.unitUnderTest.canProcess(), "Expected the processor to be able to process models.");
    }

    @Test
    public void testProcessNoSetup() throws IOException {
        Model model = new Model();

        this.unitUnderTest.process(model, processingEnv);

        Assert.assertTrue(this.messager.getMessages().isEmpty(), "Expected no message to be created.");
    }

    @Test
    public void testProcessEmptyModel() throws IOException {
        this.properties.setProperty("fullChangelogFile", "target/generated-resources/fullChangelog.xml");
        Model model = new Model();

        this.unitUnderTest.setProperties(this.properties);

        this.unitUnderTest.process(model, processingEnv);

        Assert.assertEquals(this.messager.getMessages().size(), 1, "Expected one message to be created.");
        Assert.assertEquals(this.messager.getMessages().get(0).getKind(), Diagnostic.Kind.NOTE,
                "Expected a message of kind NOTE.");
    }

}
