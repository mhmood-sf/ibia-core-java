package ibia.core.db;

import org.junit.Test;

import ibia.core.entities.Committee;

public class DbClientTest {
    protected Committee com = new Committee("it's all objects?", 10, "always has been");

    public DbClientTest() throws Exception {}

    protected void setUp() throws Exception {
        System.out.println("this was executed");
    }

    @Test public void testInsertAndFind() throws Exception {
        DbClient.insertOne(com);
        Committee found = DbClient.findOne(Committee.class, com.getId());
        System.out.println(found);
    }

}
