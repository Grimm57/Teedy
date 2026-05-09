package com.sismics.util.jpa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Test of {@link DialectUtil}.
 */
public class TestDialectUtil {
    private Properties previousProperties;

    @Before
    public void setUp() throws Exception {
        previousProperties = getProperties();
    }

    @After
    public void tearDown() throws Exception {
        setProperties(previousProperties);
    }

    @Test
    public void isObjectNotFoundH2Test() throws Exception {
        setDriverClass("org.h2.Driver");
        Assert.assertTrue(DialectUtil.isObjectNotFound("table not found"));
        Assert.assertFalse(DialectUtil.isObjectNotFound("does not exist"));
    }

    @Test
    public void isObjectNotFoundPostgresqlTest() throws Exception {
        setDriverClass("org.postgresql.Driver");
        Assert.assertTrue(DialectUtil.isObjectNotFound("relation does not exist"));
        Assert.assertFalse(DialectUtil.isObjectNotFound("table not found"));
    }

    @Test
    public void transformPostgresqlTest() throws Exception {
        setDriverClass("org.postgresql.Driver");

        Assert.assertEquals("select table_name, timestamp, text from table where flag = bool default true and ok = bool not null",
                DialectUtil.transform("select cached table_name, datetime, longvarchar from memory table where flag = bit default 1 and ok = bit not null"));
        Assert.assertEquals("select 1", DialectUtil.transform("!PGSQL!select 1"));
        Assert.assertNull(DialectUtil.transform("!H2!select 1"));
    }

    @Test
    public void transformH2Test() throws Exception {
        setDriverClass("org.h2.Driver");

        Assert.assertEquals("select cached table_name, datetime, longvarchar from memory table where flag = bit default 1 and ok = bit not null",
                DialectUtil.transform("select cached table_name, datetime, longvarchar from memory table where flag = bit default 1 and ok = bit not null"));
        Assert.assertNull(DialectUtil.transform("!PGSQL!select 1"));
        Assert.assertEquals("select 1", DialectUtil.transform("!H2!select 1"));
    }

    private void setDriverClass(String driverClass) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver_class", driverClass);
        setProperties(properties);
    }

    private Properties getProperties() throws Exception {
        Field field = EMF.class.getDeclaredField("properties");
        field.setAccessible(true);
        return (Properties) field.get(null);
    }

    private void setProperties(Properties properties) throws Exception {
        Field field = EMF.class.getDeclaredField("properties");
        field.setAccessible(true);
        field.set(null, properties);
    }
}