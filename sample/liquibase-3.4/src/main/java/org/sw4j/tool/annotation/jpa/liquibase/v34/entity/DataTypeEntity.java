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

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This entity is used to test the data type mapping.
 *
 * @author Uwe Plonus
 */
@Entity
public class DataTypeEntity {

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

    private long primitiveLong;

    public long getPrimitiveLong() {
        return primitiveLong;
    }

    public void setPrimitiveLong(long primitiveLong) {
        this.primitiveLong = primitiveLong;
    }

    private Integer intObject;

    public Integer getIntObject() {
        return intObject;
    }

    public void setIntObject(Integer intObject) {
        this.intObject = intObject;
    }

    private int primitiveInt;

    public int getPrimitiveInt() {
        return primitiveInt;
    }

    public void setPrimitiveInt(int primitiveInt) {
        this.primitiveInt = primitiveInt;
    }

    private Short shortObject;

    public Short getShortObject() {
        return shortObject;
    }

    public void setShortObject(Short shortObject) {
        this.shortObject = shortObject;
    }

    private short primitiveShort;

    public short getPrimitiveShort() {
        return primitiveShort;
    }

    public void setPrimitiveShort(short primitiveShort) {
        this.primitiveShort = primitiveShort;
    }

}
