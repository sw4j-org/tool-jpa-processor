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
package org.sw4j.tool.annotation.jpa.processor.mock.persistence;

import java.lang.annotation.Annotation;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Uwe Plonus
 */
public class TableMock implements Table {

    private final String name;

    private final String catalog;

    private final String schema;

    public TableMock(final String name, final String catalog, final String schema) {
        this.name = name;
        this.catalog = catalog;
        this.schema = schema;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String catalog() {
        return this.name;
    }

    @Override
    public String schema() {
        return this.name;
    }

    @Override
    public UniqueConstraint[] uniqueConstraints() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Index[] indexes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Table.class;
    }

}
