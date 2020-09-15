package ibia.core;

import org.junit.Test;

public class LogTest {
    @Test public void test() {
        Log.info("info message");
        Log.warn("warn message");
        Log.error("error message");
    }
}
