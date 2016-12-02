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
package org.sw4j.tool.annotation.generator;

import org.sw4j.tool.annotation.generator.util.ITSuperclass;
import org.testng.annotations.Test;

/**
 *
 * @author uwe
 */
public class LiquibaseGeneratorEmptyPropertiesIT extends ITSuperclass {

    @Override
    public String getTestProperties() {
        return "src/test/resources/empty.properties";
    }

    @Test
    public void testRootElement() {
    }

}
