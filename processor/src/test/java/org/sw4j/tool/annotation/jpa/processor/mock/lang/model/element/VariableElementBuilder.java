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
package org.sw4j.tool.annotation.jpa.processor.mock.lang.model.element;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Map;
import javax.annotation.concurrent.NotThreadSafe;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * This is an executable element builder that supports creating elements for tests.
 *
 * @author Uwe Plonus
 */
@NotThreadSafe
public class VariableElementBuilder extends ElementBuilder<VariableElement> {

    public VariableElementBuilder() {
    }

    @Override
    public VariableElement createElement() {
        if (getSimpleName() == null) {
            throw new IllegalStateException("Need a simple name to create an element.");
        }
        if (getType() == null) {
            throw new IllegalStateException("A VariableElement needs a type.");
        }
        if (getKind() == null) {
            throw new IllegalStateException("A VariableElement needs a kind.");
        }
        if (!getEnclosedElements().isEmpty()) {
            throw new IllegalStateException("A VariableElement may not have any enclosed element.");
        }
        VariableElement result = new VariableElementMock(getSimpleName(), getType(), getAnnotations(), getKind(),
                getEnclosingElement());
        clearBuilder();
        return result;
    }


    protected static class VariableElementMock extends ElementBuilder.ElementMock implements VariableElement {

        public VariableElementMock(final Name simpleName, final TypeMirror type,
                final Map<Class<?>, ? extends Annotation> annotations, final ElementKind kind,
                final Element enclosingElement) {
            super(simpleName, type, annotations, kind, enclosingElement, Collections.EMPTY_LIST);
        }

        @Override
        public Object getConstantValue() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
