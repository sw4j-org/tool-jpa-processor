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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * A mock for the processing environment.
 *
 * @author Uwe Plonus
 */
public class ProcessingEnvironmentMock implements ProcessingEnvironment {

    private final Map<String, String> options;

    private final Messager messager;

    public ProcessingEnvironmentMock() {
        this(new HashMap<String, String>(), new MessagerMock());
    }

    public ProcessingEnvironmentMock(final Map<String, String> options, final Messager messager) {
        this.options = options;
        this.messager = messager;
    }

    @Override
    public Map<String, String> getOptions() {
        return this.options;
    }

    @Override
    public Messager getMessager() {
        return messager;
    }

    @Override
    public Filer getFiler() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Elements getElementUtils() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Types getTypeUtils() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SourceVersion getSourceVersion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Locale getLocale() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
