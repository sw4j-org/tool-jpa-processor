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
package org.sw4j.tool.annotation.jpa.processor.mock;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;

/**
 *
 * @author Uwe Plonus
 */
public abstract class ElementMock implements Element {

    private final Name simpleName;

    private final Map<Class<?>, ? extends Annotation> annotations;

    private final ElementKind kind;

    private final Element enclosingElement;

    private final List<? extends Element> enclosedElements;

    public ElementMock(final Name simpleName, final Map<Class<?>, ? extends Annotation> annotations,
            final ElementKind kind, final Element enclosingElement, final List<? extends Element> enclosedElements) {
        this.simpleName = simpleName;
        this.annotations = annotations;
        this.kind = kind;
        this.enclosingElement = enclosingElement;
        this.enclosedElements = enclosedElements;
    }

    @Override
    public final TypeMirror asType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public final ElementKind getKind() {
        return this.kind;
    }

    @Override
    public final List<? extends AnnotationMirror> getAnnotationMirrors() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public final <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        return annotationType.cast(this.annotations.get(annotationType));
    }

    @Override
    public final Set<Modifier> getModifiers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public final Name getSimpleName() {
        return this.simpleName;
    }

    @Override
    public final Element getEnclosingElement() {
        return this.enclosingElement;
    }

    @Override
    public final List<? extends Element> getEnclosedElements() {
        return Collections.unmodifiableList(enclosedElements);
    }

    @Override
    public final <R, P> R accept(ElementVisitor<R, P> v, P p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
