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
package org.sw4j.tool.annotation.jpa.processor.mock.annotation.processing;

import java.lang.annotation.Annotation;
import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * A mock for the round environment for annotation processors.
 *
 * @author Uwe Plonus
 */
public class RoundEnvironmentMock implements RoundEnvironment {

    private boolean processingOver;

    private final Set<? extends Element> rootElements;

    public RoundEnvironmentMock(final Set<? extends Element> rootElements) {
        this.rootElements = rootElements;
    }

    public void processingOver(final boolean processingOver) {
        this.processingOver = processingOver;
    }

    @Override
    public boolean processingOver() {
        return this.processingOver;
    }

    @Override
    public boolean errorRaised() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<? extends Element> getRootElements() {
        return this.rootElements;
    }

    @Override
    public Set<? extends Element> getElementsAnnotatedWith(TypeElement a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<? extends Element> getElementsAnnotatedWith(Class<? extends Annotation> a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
