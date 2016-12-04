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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.sw4j.tool.annotation.jpa.generator.model.Model;
import org.sw4j.tool.annotation.jpa.test.mock.annotation.processing.MessagerMock;
import org.sw4j.tool.annotation.jpa.test.mock.annotation.processing.ProcessingEnvironmentMock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Uwe Plonus
 */
public class ChangesetGeneratorTest extends AbstractUnitTest {

    private static final String FULL_CHANGELOG_FILE_NAME = "target/generated-test-resources/fullChangelog.xml";

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
    public void testProcessOnlyEntityModel() throws Exception {
        this.properties.setProperty("fullChangelogFile", FULL_CHANGELOG_FILE_NAME);
        Model model = new Model();
        Entity entity = new Entity("SimpleEntity", "org.sw4j.tool.annotation.jpa.entity.SimpleEntity");
        model.addEntity(entity);

        this.unitUnderTest.setProperties(this.properties);

        this.unitUnderTest.process(model, processingEnv);

        Document result = parseResult(FULL_CHANGELOG_FILE_NAME);
        NodeList children = result.getDocumentElement().getElementsByTagName("changeSet");
        Node changeSet = children.item(0);
        NamedNodeMap changeSetAttributes = changeSet.getAttributes();
        Assert.assertEquals(changeSetAttributes.getLength(), 2, "Expected the changeSet to have two attributes");
        Assert.assertNotNull(changeSetAttributes.getNamedItem("id"), "Expected the changeSet to have an id attribute.");
        Assert.assertEquals(changeSetAttributes.getNamedItem("id").getNodeValue(), "createTable_SimpleEntity",
                "Expected the attribute id to be \"createTable_SimpleEntity\"");
        Assert.assertNotNull(changeSetAttributes.getNamedItem("author"),
                "Expected the changeSet to have an author attribute.");
        Assert.assertEquals(changeSetAttributes.getNamedItem("author").getNodeValue(), "generated",
                "Expected the attribute author to contain with \"generated\"");
    }

}
