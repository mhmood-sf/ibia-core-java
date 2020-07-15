package ibia.core;

import org.junit.Test;

import ibia.core.entities.Committee;

public class DbDriverTest {
    protected Committee com = new Committee("it's all objects?", 10, "always has been");

    protected void setUp() throws Exception {
        System.out.println("this was executed");
    }

    @Test public void testInsertAndFind() throws Exception {
        DbDriver.insertOne(com);
        Committee found = DbDriver.findOne(Committee.class, com.getId());
        System.out.println(found);
    }

}
