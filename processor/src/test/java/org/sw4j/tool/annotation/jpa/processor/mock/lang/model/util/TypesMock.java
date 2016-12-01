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
package org.sw4j.tool.annotation.jpa.processor.mock.lang.model.util;

import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.Types;

/**
 *
 * @author Uwe Plonus
 */
public class TypesMock implements Types {

    private Element asElement;

    public TypesMock() {
    }

    public void asElement(Element asElement) {
        this.asElement = asElement;
    }

    @Override
    public Element asElement(TypeMirror t) {
        return this.asElement;
    }

    @Override
    public boolean isSameType(TypeMirror t1, TypeMirror t2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isSubtype(TypeMirror t1, TypeMirror t2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAssignable(TypeMirror t1, TypeMirror t2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(TypeMirror t1, TypeMirror t2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isSubsignature(ExecutableType m1, ExecutableType m2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<? extends TypeMirror> directSupertypes(TypeMirror t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TypeMirror erasure(TypeMirror t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TypeElement boxedClass(PrimitiveType p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PrimitiveType unboxedType(TypeMirror t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TypeMirror capture(TypeMirror t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PrimitiveType getPrimitiveType(TypeKind kind) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NullType getNullType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NoType getNoType(TypeKind kind) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayType getArrayType(TypeMirror componentType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WildcardType getWildcardType(TypeMirror extendsBound, TypeMirror superBound) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DeclaredType getDeclaredType(TypeElement typeElem, TypeMirror... typeArgs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DeclaredType getDeclaredType(DeclaredType containing, TypeElement typeElem, TypeMirror... typeArgs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TypeMirror asMemberOf(DeclaredType containing, Element element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
