package ibia.core.utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import java.util.Date;

public class IdTest {
    public String comId = Id.generate("COM");
    public String conId = Id.generate("CON");
    public String delId = Id.generate("DEL");

    // To handle exception
    public IdTest() throws Exception {}

    @Test public void testGenerateIdFormat() {
        assertTrue("comId starts with COM", comId.startsWith("COM"));
        assertTrue("conId starts with CON", conId.startsWith("CON"));
        assertTrue("delId starts with DEL", delId.startsWith("DEL"));

        assertNotNull(Long.parseLong(comId.substring(3)));
        assertNotNull(Long.parseLong(conId.substring(3)));
        assertNotNull(Long.parseLong(delId.substring(3)));
    }

    @Test public void testCreatedAt() throws Exception {
        assertNotNull(Id.createdAt(comId));
        assertNotNull(Id.createdAt(conId));
        assertNotNull(Id.createdAt(delId));

        assertTrue(Id.createdAt(comId) instanceof Date);
        assertTrue(Id.createdAt(conId) instanceof Date);
        assertTrue(Id.createdAt(delId) instanceof Date);
    }

    @Test(expected = Exception.class)
    public void testCreatedAtThrows() throws Exception {
        Id.createdAt("this should fail");
    }

    @Test public void testVerifyId() {
        assertTrue(Id.verify(comId));
        assertTrue(Id.verify(conId));
        assertTrue(Id.verify(delId));

        assertFalse(Id.verify("com123456789"));
        assertFalse(Id.verify("this should fail"));
        assertFalse(Id.verify("COM1590520368739"));
    }
}
