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
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.type.TypeMirrorMock;

/**
 * This is an executable element builder that supports creating elements for tests.
 *
 * @author Uwe Plonus
 */
@NotThreadSafe
public class ExecutableElementBuilder extends ElementBuilder<ExecutableElement> {

    private TypeMirror returnType;

    public ExecutableElementBuilder() {
    }

    @Override
    protected void clearBuilder() {
        super.clearBuilder();
        this.returnType = null;
    }

    public void setReturnTypeKind(TypeKind returnType) {
        this.returnType = new TypeMirrorMock(returnType);
    }

    protected TypeMirror getReturnType() {
        return this.returnType;
    }

    @Override
    public ExecutableElement createElement() {
        if (getSimpleName() == null) {
            throw new IllegalStateException("Need a simple name to create an element.");
        }
        setTypeKind(TypeKind.EXECUTABLE);
        if (getReturnType() == null) {
            throw new IllegalStateException("An ExecutableElement needs a return type.");
        }
        if (getKind() == null) {
            throw new IllegalStateException("An ExecutableElement needs a kind.");
        }
        switch (getKind()) {
            case METHOD:
            case CONSTRUCTOR:
            case STATIC_INIT:
            case INSTANCE_INIT:
                break;
            default:
                throw new IllegalStateException(
                        "An ExecutableElement needs a kind of type METHOD, CONSTRUCTOR, STATIC_INIT or INSTANCE_INIT.");
        }
        if (!getEnclosedElements().isEmpty()) {
            throw new IllegalStateException("An ExecutableElement may not have any enclosed element.");
        }
        ExecutableElement result = new ExecutableElementMock(getSimpleName(), this.returnType, getAnnotations(),
                getKind(), getEnclosingElement());
        clearBuilder();
        return result;
    }


    protected static class ExecutableElementMock extends ElementBuilder.ElementMock implements ExecutableElement {

        private final TypeMirror returnType;

        public ExecutableElementMock(final Name simpleName, final TypeMirror returnType,
                @Nullable final Map<Class<?>, ? extends Annotation> annotations, final ElementKind kind,
                final Element enclosingElement) {
            super(simpleName, new TypeMirrorMock(TypeKind.EXECUTABLE), annotations, kind, enclosingElement,
                    Collections.EMPTY_LIST);
            this.returnType = returnType;
        }

        @Override
        public List<? extends TypeParameterElement> getTypeParameters() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public TypeMirror getReturnType() {
            return this.returnType;
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

}
