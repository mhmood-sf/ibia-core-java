package ibia.core.db;

import org.junit.Test;

import ibia.core.entities.Committee;

public class DbClientTest {
    protected void setUp() throws Exception {
        System.out.println("this was executed");
    }

    @Test public void testInsert() throws Exception {
        DbClient.insert(new Committee("it's all objects?", 10, "always has been"));
    }
}
