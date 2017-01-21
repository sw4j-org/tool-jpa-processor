/*
 * Copyright (C) 2017 uwe
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

import java.util.Hashtable;
import java.util.Map;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/**
 *
 * @author uwe
 */
public class MockInitialContext implements Context {

    private final Map<String, Object> boundObjects;

    public MockInitialContext(final Map<String, Object> boundObjects) {
        this.boundObjects = boundObjects;
    }

    @Override
    public Object lookup(Name name) throws NamingException {
        return this.boundObjects.get(name.toString());
    }

    @Override
    public Object lookup(String name) throws NamingException {
        throw new UnsupportedOperationException("lookup(String) not supported yet.");
    }

    @Override
    public void bind(Name name, Object obj) throws NamingException {
        throw new UnsupportedOperationException("bind(Name, Object) not supported yet.");
    }

    @Override
    public void bind(String name, Object obj) throws NamingException {
        throw new UnsupportedOperationException("bind(String, Object) not supported yet.");
    }

    @Override
    public void rebind(Name name, Object obj) throws NamingException {
        throw new UnsupportedOperationException("rebind(Name, Object) not supported yet.");
    }

    @Override
    public void rebind(String name, Object obj) throws NamingException {
        throw new UnsupportedOperationException("rebind(String, Object) not supported yet.");
    }

    @Override
    public void unbind(Name name) throws NamingException {
        throw new UnsupportedOperationException("unbind(Name) not supported yet.");
    }

    @Override
    public void unbind(String name) throws NamingException {
        throw new UnsupportedOperationException("unbind(String) not supported yet.");
    }

    @Override
    public void rename(Name oldName, Name newName) throws NamingException {
        throw new UnsupportedOperationException("rename(Name, Name) not supported yet.");
    }

    @Override
    public void rename(String oldName, String newName) throws NamingException {
        throw new UnsupportedOperationException("rename(String, String) not supported yet.");
    }

    @Override
    public NamingEnumeration<NameClassPair> list(Name name) throws NamingException {
        throw new UnsupportedOperationException("NamingEnumeration<NameClassPair> list(Name) not supported yet.");
    }

    @Override
    public NamingEnumeration<NameClassPair> list(String name) throws NamingException {
        throw new UnsupportedOperationException("NamingEnumeration<NameClassPair> list(String) not supported yet.");
    }

    @Override
    public NamingEnumeration<Binding> listBindings(Name name) throws NamingException {
        throw new UnsupportedOperationException("NamingEnumeration<Binding> listBindings(Name) not supported yet.");
    }

    @Override
    public NamingEnumeration<Binding> listBindings(String name) throws NamingException {
        throw new UnsupportedOperationException("NamingEnumeration<Binding> listBindings(String) not supported yet.");
    }

    @Override
    public void destroySubcontext(Name name) throws NamingException {
        throw new UnsupportedOperationException("destroySubcontext(Name) not supported yet.");
    }

    @Override
    public void destroySubcontext(String name) throws NamingException {
        throw new UnsupportedOperationException("destroySubcontext(String) not supported yet.");
    }

    @Override
    public Context createSubcontext(Name name) throws NamingException {
        throw new UnsupportedOperationException("createSubcontext(Name) not supported yet.");
    }

    @Override
    public Context createSubcontext(String name) throws NamingException {
        throw new UnsupportedOperationException("createSubcontext(String) not supported yet.");
    }

    @Override
    public Object lookupLink(Name name) throws NamingException {
        throw new UnsupportedOperationException("lookupLink(Name) not supported yet.");
    }

    @Override
    public Object lookupLink(String name) throws NamingException {
        throw new UnsupportedOperationException("lookupLink(String) not supported yet.");
    }

    @Override
    public NameParser getNameParser(Name name) throws NamingException {
        throw new UnsupportedOperationException("getNameParser(Name) not supported yet.");
    }

    @Override
    public NameParser getNameParser(String name) throws NamingException {
        throw new UnsupportedOperationException("getNameParser(String) not supported yet.");
    }

    @Override
    public Name composeName(Name name, Name prefix) throws NamingException {
        throw new UnsupportedOperationException("composeName(Name, Name) not supported yet.");
    }

    @Override
    public String composeName(String name, String prefix) throws NamingException {
        throw new UnsupportedOperationException("composeName(String, String) not supported yet.");
    }

    @Override
    public Object addToEnvironment(String propName, Object propVal) throws NamingException {
        throw new UnsupportedOperationException("addToEnvironment(String, Object) not supported yet.");
    }

    @Override
    public Object removeFromEnvironment(String propName) throws NamingException {
        throw new UnsupportedOperationException("removeFromEnvironment(String) not supported yet.");
    }

    @Override
    public Hashtable<?, ?> getEnvironment() throws NamingException {
        throw new UnsupportedOperationException("getEnvironment() not supported yet.");
    }

    @Override
    public void close() throws NamingException {
        throw new UnsupportedOperationException("close() not supported yet.");
    }

    @Override
    public String getNameInNamespace() throws NamingException {
        throw new UnsupportedOperationException("getNameInNamespace() not supported yet.");
    }

}
