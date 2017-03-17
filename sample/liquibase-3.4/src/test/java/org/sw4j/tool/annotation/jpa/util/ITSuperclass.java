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
package org.sw4j.tool.annotation.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import liquibase.Liquibase;
import liquibase.database.jvm.DerbyConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.sw4j.tool.annotation.jpa.liquibase.v34.entity.DataTypeEntity;
import org.sw4j.tool.annotation.jpa.liquibase.v34.entity.DataTypeEntityIT;
import org.testng.annotations.BeforeSuite;

/**
 *
 * @author Uwe Plonus
 */
public class ITSuperclass {

    private static EntityManager em;

    @BeforeSuite()
    public static void setUpSuite() throws Exception {
        EmbeddedDataSource ds = new EmbeddedConnectionPoolDataSource();
        ds.setDatabaseName("memory:sample");
        ds.setCreateDatabase("create");
        MockInitialContextFactoryBuilder builder = new MockInitialContextFactoryBuilder();
        builder.bind("java:comp/env/jdbc/ds", ds);
        builder.activate();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SAMPLEPU");
        em = emf.createEntityManager();
        Liquibase liquibase = new Liquibase("changelog.xml", new ClassLoaderResourceAccessor(), new DerbyConnection(ds.getConnection()));
        liquibase.update("");
    }

    public static EntityManager getEm() {
        return em;
    }

    public static void setEm(EntityManager em) {
        ITSuperclass.em = em;
    }

}
