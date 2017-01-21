/*
 * Copyright (C) 2017 Uwe Plonus
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

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import liquibase.Liquibase;
import liquibase.database.jvm.DerbyConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.sw4j.tool.annotation.jpa.util.MockInitialContextFactoryBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 *
 * @author Uwe Plonus
 */
public class SampleEntityIT {

    private static EntityManager em;

    @BeforeSuite
    public static void setUpSuite() throws Exception {
        EmbeddedDataSource ds = new EmbeddedConnectionPoolDataSource();
        ds.setDatabaseName("memory:sample");
        ds.setCreateDatabase("create");
        MockInitialContextFactoryBuilder builder = new MockInitialContextFactoryBuilder();
        builder.bind("java:comp/env/jdbc/ds", ds);
        builder.activate();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SAMPLEPU");
        em = emf.createEntityManager();

        Liquibase liquibase = new Liquibase("changelog.xml", new ClassLoaderResourceAccessor(),
                new DerbyConnection(ds.getConnection()));
        liquibase.update("");

        SampleEntity entity = new SampleEntity();
        entity.setId(1);
        em.persist(entity);
    }

    @Test
    public void testCreateEntity() {
        SampleEntity entity = new SampleEntity();
        entity.setId(100);
        em.persist(entity);
    }

    @Test
    public void testFindEntity() {
        SampleEntity entity = em.find(SampleEntity.class, 1);
        Assert.assertNotNull(entity, "Expected the entity to be found.");
        Assert.assertEquals(entity.getId(), 1, "Expected the entity to be found.");
    }

}
