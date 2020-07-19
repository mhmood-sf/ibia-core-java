package ibia.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;

public class DbDriverTest {
    public static Conference conf = new Conference("AICMUN");

    @BeforeClass
    public static void setup() throws Exception {
        ArrayList<Committee> coms = new ArrayList<>();
        ArrayList<Delegate> dels = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Committee c = new Committee(i + "", conf.getId());
            coms.add(c);
            for (int j = 0; j < 5; j++) {
                dels.add(new Delegate(j + "", j + "", c.getId()));
                Thread.sleep(10); // Needed to ensure IDs are unique
            }
        }

        // Test: insert
        DbDriver.insertOne(conf);
        DbDriver.insertAll(coms);
        DbDriver.insertAll(dels);
    }

    @AfterClass
    public static void finish() {
        DbDriver.shutdown();
    }

    // All tests need to be under the same method
    // since they all share the same state/db connection
    // and can't be run in asynchronously under 
    // different methods
    @Test public void test() {
        // Test: fetch
        Conference c = DbDriver.fetchOne(Conference.class, conf.getId());
        assertNotNull(c);
        assertTrue(c.getName().equals("AICMUN"));

        ArrayList<Committee> coms = DbDriver.fetchAll(Committee.class);
        assertNotNull(coms);
        assertTrue(coms.size() == 10);
        
        ArrayList<Delegate> dels = DbDriver.fetchAll(Delegate.class);
        assertNotNull(dels);
        assertTrue(dels.size() == 50);

        // Test: update
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

        // Test: delete
        DbDriver.deleteOne(dels.get(0));
        DbDriver.deleteAll(Arrays.asList(coms.get(0), coms.get(1), coms.get(2)));

        dels = DbDriver.fetchAll(Delegate.class);
        coms = DbDriver.fetchAll(Committee.class);

        assertTrue(dels.size() == 49);
        assertTrue(coms.size() == 7);

        // Test: find
        Committee z = DbDriver.findOne(Committee.class, com -> com.getName().equals("X"));
        assertNotNull(z);

        ArrayList<Delegate> zs = DbDriver.findAll(Delegate.class, d -> d.getCommitteeId().equals(z.getId()));
        assertNotNull(zs);

        assertTrue(z.getName().equals("X"));
        for (Delegate zed : zs) {
            assertTrue(zed.getCommitteeId().equals(z.getId()));
        }
    }
}
