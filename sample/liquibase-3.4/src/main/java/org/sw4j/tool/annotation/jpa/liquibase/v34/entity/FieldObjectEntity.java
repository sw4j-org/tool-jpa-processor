/*
 * Copyright (C) 2017 uwe
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
package org.sw4j.tool.annotation.jpa.liquibase.v34.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This entity is used to test the data type mapping on object fields.
 *
 * @author Uwe Plonus
 */
@Entity
public class FieldObjectEntity implements Serializable {

    @Id
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private Long longObject;

    public Long getLongObject() {
        return longObject;
    }

    public void setLongObject(Long longObject) {
        this.longObject = longObject;
    }

    private Integer intObject;

    public Integer getIntObject() {
        return intObject;
    }

    public void setIntObject(Integer intObject) {
        this.intObject = intObject;
    }

    private Short shortObject;

    public Short getShortObject() {
        return shortObject;
    }

    public void setShortObject(Short shortObject) {
        this.shortObject = shortObject;
    }

    private Byte byteObject;

    public Byte getByteObject() {
        return byteObject;
    }

    public void setByteObject(Byte byteObject) {
        this.byteObject = byteObject;
    }

    private Boolean booleanObject;

    public Boolean getBooleanObject() {
        return booleanObject;
    }

    public void setBooleanObject(Boolean booleanObject) {
        this.booleanObject = booleanObject;
    }

}
