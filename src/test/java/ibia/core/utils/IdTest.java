package ibia.core.utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import java.util.Date;

import ibia.core.utils.Id;

public class IdTest {
    String comId;
    String conId;
    String delId;
    String entId;

    @Test public void testGenerateId() throws Exception {
        comId = Id.generate("com");
        conId = Id.generate("con");
        delId = Id.generate("del");
        entId = Id.generate("ent");
    }

    @Test(expected = Exception.class)
    public void testGenerateIdThrows() throws Exception {
        Id.generate("this should fail");
    }

    @Test public void testGenerateIdFormat() {
        assertTrue("comId starts with COM", comId.startsWith("COM"));
        assertTrue("conId starts with CON", conId.startsWith("CON"));
        assertTrue("delId starts with DEL", delId.startsWith("DEL"));
        assertTrue("entId starts with ENT", entId.startsWith("ENT"));

        assertNotNull(Long.parseLong(comId.substring(3)));
        assertNotNull(Long.parseLong(conId.substring(3)));
        assertNotNull(Long.parseLong(delId.substring(3)));
        assertNotNull(Long.parseLong(entId.substring(3)));
    }

    @Test public void testCreatedAt() throws Exception {
        assertTrue(Id.createdAt(comId) instanceof Date);
        assertTrue(Id.createdAt(conId) instanceof Date);
        assertTrue(Id.createdAt(delId) instanceof Date);
        assertTrue(Id.createdAt(entId) instanceof Date);

        assertNotNull(Id.createdAt(comId));
        assertNotNull(Id.createdAt(conId));
        assertNotNull(Id.createdAt(delId));
        assertNotNull(Id.createdAt(entId));
    }

    @Test(expected = Exception.class)
    public void testCreatedAtThrows() throws Exception {
        Id.createdAt("this should fail");
    }

    @Test public void testVerifyId() {
        assertTrue(Id.verify(comId));
        assertTrue(Id.verify(conId));
        assertTrue(Id.verify(delId));
        assertTrue(Id.verify(entId));
        assertTrue(Id.verify("COM1590520368739"));

        assertFalse(Id.verify("com123456789"));
        assertFalse(Id.verify("this should fail"));
    }
}
