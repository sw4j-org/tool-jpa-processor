/*
 * Copyright (C) 2016 Uwe Plonus
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
package org.sw4j.tool.annotation.jpa.processor.exceptions;

import javax.annotation.Nonnull;

/**
 * This is an exception that indicates a problem in processing an element.
 *
 * @author Uwe Plonus
 */
public class AnnotationProcessorException extends Exception {

    /**
     * A constructor with a detailed message.
     *
     * @param message the detailed message.
     */
    public AnnotationProcessorException(@Nonnull final String message) {
        super(message);
    }

    /**
     * A constructor with a detailed message and a causing throwable.
     *
     * @param message the detailed message.
     * @param cause the cause of this exception.
     */
    public AnnotationProcessorException(@Nonnull final String message, @Nonnull final Throwable cause) {
        super(message, cause);
    }

}
