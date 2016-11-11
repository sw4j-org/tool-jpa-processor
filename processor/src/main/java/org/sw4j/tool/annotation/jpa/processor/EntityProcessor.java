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
package org.sw4j.tool.annotation.jpa.processor;

import javax.lang.model.element.Element;
import org.sw4j.tool.annotation.jpa.generator.model.Entity;
import org.sw4j.tool.annotation.jpa.generator.model.Model;

/**
 * This is a processor to handle classes with an @Entity annotation.
 *
 * @author Uwe Plonus
 */
public class EntityProcessor {

    /**
     * Default constructor for the entity processor.
     *
     */
    public EntityProcessor() {
    }

    /**
     * Process a single entity annotated with {@code @Entity}.
     *
     * @param element the element to process (must be an {@code @Entity}.
     * @param model the model where the final entity is added to.
     */
    public void process(final Element element, final Model model) {
        javax.persistence.Entity entityAnnotation = element.getAnnotation(javax.persistence.Entity.class);
        Entity entity = new Entity();
        if ("".equals(entityAnnotation.name())) {
            entity.setName(element.getSimpleName().toString());
        } else {
            entity.setName(entityAnnotation.name());
        }
        model.addEntity(entity);
    }

}
