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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.sw4j.tool.annotation.jpa.processor.mock.lang.model.type.TypeMirrorMock;

/**
 * This is an element builder that supports creating elements for tests.
 *
 * @param <E> the type of element the builder creates.
 * @author Uwe Plonus
 */
@NotThreadSafe
public abstract class ElementBuilder<E extends Element> {

    private Name simpleName;

    private TypeMirror type;

    private ElementKind kind;

    private Element enclosingElement;

    private final Map<Class<?>, Annotation> annotations;

    private final List<Element> enclosedElements;

    public ElementBuilder() {
        this.annotations = new HashMap<>();
        this.enclosedElements = new LinkedList<>();
    }

    @OverridingMethodsMustInvokeSuper
    protected void clearBuilder() {
        this.simpleName = null;
        this.type = null;
        this.kind = null;
        this.enclosingElement = null;
        this.annotations.clear();
        this.enclosedElements.clear();
    }

    public void setSimpleName(@Nonnull final String simpleName) {
        this.simpleName = new NameMock(simpleName);
    }

    protected Name getSimpleName() {
        return this.simpleName;
    }

    public void setTypeKind(@Nonnull final TypeKind typeKind) {
        this.type = new TypeMirrorMock(typeKind);
    }

    protected TypeMirror getType() {
        return this.type;
    }

    public void setKind(@Nonnull final ElementKind kind) {
        this.kind = kind;
    }

    protected ElementKind getKind() {
        return this.kind;
    }

    public void setEnclosingElement(@Nonnull final Element enclosing) {
        this.enclosingElement = enclosing;
    }

    public Element getEnclosingElement() {
        return this.enclosingElement;
    }

    public void addAnnotation(Class<?> clazz, Annotation annotation) {
        this.annotations.put(clazz, annotation);
    }

    protected Map<Class<?>, ? extends Annotation> getAnnotations() {
        return new HashMap<>(this.annotations);
    }

    public void addEnclosedElement(Element enclosed) {
        this.enclosedElements.add(enclosed);
    }

    @Nullable
    protected List<? extends Element> getEnclosedElements() {
        return new LinkedList<>(this.enclosedElements);
    }

    public abstract E createElement();


    protected static abstract class ElementMock implements Element {

        private final Name simpleName;

        private final TypeMirror type;

        private final Map<Class<?>, ? extends Annotation> annotations;

        private final ElementKind kind;

        private Element enclosingElement;

        private final List<? extends Element> enclosedElements;

        public ElementMock(final Name simpleName, final TypeMirror type,
                @Nonnull final Map<Class<?>, ? extends Annotation> annotations, final ElementKind kind,
                final Element enclosingElement, @Nonnull final List<? extends Element> enclosedElements) {
            this.simpleName = simpleName;
            this.type = type;
            this.annotations = Collections.unmodifiableMap(annotations);
            this.kind = kind;
            this.enclosingElement = enclosingElement;
            this.enclosedElements = Collections.unmodifiableList(enclosedElements);
        }

        @Override
        public final TypeMirror asType() {
            return type;
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

        protected final void setEnclosingElement(final Element enclosingElement) {
            this.enclosingElement = enclosingElement;
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

}
