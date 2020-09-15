package ibia.core;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logging utility.
 */
public class Log {
    private static Logger logger;

    public static void init() throws IOException {
        Handler handler = new FileHandler("data/ibia.log.xml");
        logger.addHandler(handler);
    }

    private static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("");

            try {
                Handler handler = new FileHandler("data/ibia.log.xml");
                logger.addHandler(handler);
            } catch (Exception e) {
                System.out.println("[ibia | WARN] Failed to access log file (ibia.log.xml).");
                System.out.println("[ibia | WARN] All log messages will be printed to console for this session.");
            }
        }

        return logger;
    }

    public static void info(String msg) {
        msg = "[ibia | INFO] " + msg;
        getLogger().info(msg);
    }

    public static void warn(String msg) {
        msg = "[ibia | WARN] " + msg;
        getLogger().log(Level.WARNING, msg);
    }

    public static void error(String msg) {
        msg = "[ibia | ERROR] " + msg;
        getLogger().log(Level.SEVERE, msg);
    }
}
