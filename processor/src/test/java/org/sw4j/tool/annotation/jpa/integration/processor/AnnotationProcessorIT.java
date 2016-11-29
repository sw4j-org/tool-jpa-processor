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
package org.sw4j.tool.annotation.jpa.integration.processor;

import org.sw4j.tool.annotation.jpa.integration.util.TestSuperclass;
import org.sw4j.tool.annotation.jpa.processor.AnnotationProcessor;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class to test the {@link AnnotationProcessor}.
 *
 * @author Uwe Plonus
 */
public class AnnotationProcessorIT extends TestSuperclass {

    /** Default constructor. */
    public AnnotationProcessorIT() {
    }

    @Test
    public void testRootElement() {
        Assert.assertNotNull(getRootElement(), "Expected a document to be read.");

        Assert.assertEquals(getRootElement().getNodeName(), "model",
                "Expected the root element to be named \"model\".");
    }

}
