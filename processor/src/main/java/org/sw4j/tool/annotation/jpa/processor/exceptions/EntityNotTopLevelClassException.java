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
 * This is an exception that indicates that the processed entity is not a top level class (as required by the
 * specification).
 *
 * @author Uwe Plonus
 */
public class EntityNotTopLevelClassException extends AnnotationProcessorException {

    /**
     * A constructor with an entity name to refine this exception.
     *
     * @param entityName the name of the entity that causes this exception.
     */
    public EntityNotTopLevelClassException(@Nonnull final String entityName) {
        super(new StringBuilder("The processed entity \"").append(entityName)
                .append("\" is no top level class.").toString());
    }

    /**
     * A constructor with an entity name to refine this exception and a cause of this exception.
     *
     * @param entityName the name of the entity that causes this exception.
     * @param cause the throwable that causes this exception.
     */
    public EntityNotTopLevelClassException(@Nonnull final String entityName, @Nonnull final Throwable cause) {
        super(new StringBuilder("The processed entity \"").append(entityName)
                .append("\" is no top level class.").toString(), cause);
    }

}
