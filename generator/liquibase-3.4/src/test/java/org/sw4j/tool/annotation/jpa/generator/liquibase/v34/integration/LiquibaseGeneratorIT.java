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

import javax.xml.xpath.XPathExpressionException;
import org.sw4j.tool.annotation.jpa.generator.util.ITSuperclass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Node;

/**
 *
 * @author uwe
 */
public class LiquibaseGeneratorIT extends ITSuperclass {

    @Test
    public void testRootElement() {
        Assert.assertNotNull(getRootElement(), "Expected a document to be read.");

        Assert.assertEquals(getRootElement().getNodeName(), "databaseChangeLog",
                "Expected the root element to be named \"databaseChangeLog\".");
        Assert.assertEquals(getAttribute("xmlns", getRootElement()).getNodeValue(),
                "http://www.liquibase.org/xml/ns/dbchangelog",
                "Expected the root element to have the namespace \"http://www.liquibase.org/xml/ns/dbchangelog\"");
    }

    @Test
    public void testChangeSetElementForPrimitiveFieldOnlyIdEntity() throws XPathExpressionException {
        Node changeSet = getNode("/databaseChangeLog/changeSet[@id='createTable_PrimitiveFieldOnlyIdEntity']");

        Assert.assertNotNull(changeSet, "Expected a changeSet to be created.");
        Assert.assertNotNull(getAttribute("id", changeSet), "Expected the changeSet to have an id attribute");
        Assert.assertEquals(getAttribute("id", changeSet).getNodeValue(), "createTable_PrimitiveFieldOnlyIdEntity",
                "Expected the id attribute to be \"createTable_PrimitiveFieldOnlyIdEntity\"");
        Assert.assertNotNull(getAttribute("author", changeSet), "Expected the changeSet to have an id attribute");
        Assert.assertEquals(getAttribute("author", changeSet).getNodeValue(), "generated",
                "Expected the author attribute to be \"generated\"");
    }

}
