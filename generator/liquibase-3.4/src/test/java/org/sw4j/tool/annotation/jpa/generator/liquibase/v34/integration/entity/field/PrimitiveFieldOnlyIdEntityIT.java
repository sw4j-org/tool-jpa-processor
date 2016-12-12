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
package org.sw4j.tool.annotation.jpa.generator.liquibase.v34.integration.entity.field;

import javax.xml.xpath.XPathExpressionException;
import org.sw4j.tool.annotation.jpa.generator.util.ITSuperclass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Node;

/**
 * Class to test the mapping of PrimitiveFieldOnlyIdEntity to a databaseChangeLog.
 *
 * @author Uwe Plonus
 */
public class PrimitiveFieldOnlyIdEntityIT extends ITSuperclass {

    @Test
    public void testChangeSetElement() throws XPathExpressionException {
        Node changeSet = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitiveFieldOnlyIdEntity')]");

        Assert.assertNotNull(changeSet, "Expected a changeSet to be created.");
        Assert.assertNotNull(getAttribute("id", changeSet), "Expected the changeSet to have an id attribute");
        Assert.assertTrue(getAttribute("id", changeSet).getNodeValue()
                .startsWith("createTable_PrimitiveFieldOnlyIdEntity"),
                "Expected the id attribute to start with \"createTable_PrimitiveFieldOnlyIdEntity\"");
        Assert.assertNotNull(getAttribute("author", changeSet), "Expected the changeSet to have an id attribute");
        Assert.assertEquals(getAttribute("author", changeSet).getNodeValue(), "generated",
                "Expected the author attribute to be \"generated\"");
    }

    @Test
    public void testCreateTableElement() throws XPathExpressionException {
        Node createTable = getNode("/databaseChangeLog" +
                "/changeSet[starts-with(@id, 'createTable_PrimitiveFieldOnlyIdEntity')]" +
                "/createTable[@tableName='PrimitiveFieldOnlyIdEntity']");

        Assert.assertNotNull(createTable, "Expected a createTable to be created.");
        Assert.assertNotNull(getAttribute("tableName", createTable),
                "Expected the createTable to have a tableName attribute.");
        Assert.assertEquals(getAttribute("tableName", createTable).getNodeValue(), "PrimitiveFieldOnlyIdEntity",
                "Expected the tableName attribute to be \"PrimitiveFieldOnlyIdEntity\"");
    }

}
