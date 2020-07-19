package ibia.core.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Test public void testFlagPath() {
        Path path = Country.flagPath("aq");
        Path actual = Paths.get("world-countries", "flags", "64x64", "aq.png");
        assertTrue(path.equals(actual));
    }
}
