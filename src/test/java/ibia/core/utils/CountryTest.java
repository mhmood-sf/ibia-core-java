package ibia.core.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class CountryTest {
    @Test public void testListOfNames() {
        ArrayList<String> names = Country.listOfNames();
        assertNotNull(names);
        assertTrue(names.contains("Antarctica"));
        assertTrue(names.size() == 249);
    }

    @Test public void testListOfCodes() {
        ArrayList<String> codes = Country.listOfCodes();
        assertNotNull(codes);
        assertTrue(codes.contains("aq"));
        assertTrue(codes.size() == 249);
    }

    @Test public void testCodeFromName() {
        String code = Country.codeFromName("Antarctica");
        assertNotNull(code);
        assertTrue(code.equals("aq"));
    }

    @Test public void testNameFromCode() {
        String name = Country.nameFromCode("aq");
        assertNotNull(name);
        assertTrue(name.equals("Antarctica"));
    }

    @Test public void testGetFlag() {
        Country.getFlag("aq");
    }
}
