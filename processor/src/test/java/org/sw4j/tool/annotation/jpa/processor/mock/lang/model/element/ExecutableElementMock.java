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
import java.util.List;
import java.util.Map;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 *
 * @author Uwe Plonus
 */
public class ExecutableElementMock extends ElementMock implements ExecutableElement {

    public ExecutableElementMock(final Name simpleName, final Map<Class<?>, ? extends Annotation> annotations,
            final ElementKind kind, final Element enclosingElement, final List<? extends Element> enclosedElements) {
        super(simpleName, annotations, kind, enclosingElement, enclosedElements);
    }

    @Override
    public List<? extends TypeParameterElement> getTypeParameters() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TypeMirror getReturnType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<? extends VariableElement> getParameters() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isVarArgs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<? extends TypeMirror> getThrownTypes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AnnotationValue getDefaultValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
