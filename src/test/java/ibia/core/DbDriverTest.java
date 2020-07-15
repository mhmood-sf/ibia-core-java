package ibia.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ibia.core.entities.Committee;

public class DbDriverTest {
    protected Committee com = new Committee("it's all objects?", 10, "always has been");

    @Test public void testInsertAndFind() throws Exception {
        DbDriver.insertOne(com);
        Committee found = DbDriver.findOne(Committee.class, com.getId());
        assertNotNull(found);
    }
}
