/*
 * Copyright (C) 2017 Uwe Plonus
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

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.naming.spi.NamingManager;

/**
 *
 * @author Uwe Plonus
 */
public class MockInitialContextFactoryBuilder implements InitialContextFactoryBuilder {

    public Map<String, Object> boundObjects = new HashMap<>();

    @Override
    public InitialContextFactory createInitialContextFactory(Hashtable<?, ?> environment) throws NamingException {
        return new InitialContextFactory() {
            @Override
            public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
                return new MockInitialContext(MockInitialContextFactoryBuilder.this.boundObjects);
            }
        };
    }

    public void bind(String name, Object object) {
        this.boundObjects.put(name, object);
    }

    public void activate() throws NamingException {
        if (NamingManager.hasInitialContextFactoryBuilder()) {
            throw new IllegalStateException("An InitialContextFactoryBuilder is already set.");
        }
        NamingManager.setInitialContextFactoryBuilder(this);
    }

}
