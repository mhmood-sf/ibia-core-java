package ibia.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;

public class DbDriverTest {
    public static Conference conf;
    
    @BeforeClass
    public static void setup() {
        conf = Client.beginNewConference("AICMUN", 10, 10);
    }

    @Test public void testFetchAndUpdate() {
        // Tests for fetching
        assertNotNull(DbDriver.fetchOne(Conference.class, conf.getId()));
        
        ArrayList<Committee> coms = DbDriver.fetchAll(Committee.class);
        assertNotNull(coms);
        assertTrue(coms.size() == 10);
        
        ArrayList<Delegate> dels = DbDriver.fetchAll(Delegate.class);
        assertNotNull(dels);
        assertTrue(dels.size() == 100);

        // Tests for updating
        conf.setName("AIMUN");
        for (Committee com : coms) com.setName("X");
        DbDriver.updateOne(conf);
        DbDriver.updateAll(coms);
        
        Conference x = DbDriver.fetchOne(Conference.class, conf.getId());
        Committee y = DbDriver.fetchOne(Committee.class, coms.get(5).getId());

        assertNotNull(x);
        assertNotNull(y);

        assertTrue(x.getName().equals("AIMUN"));
        assertTrue(y.getName().equals("X"));
    }

}
