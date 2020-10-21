package ibia.core;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Logging utility.
 */
public class Log {
    private static Logger logger;
    private static Handler handler;

    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("");
            // for efficiency, do not log to console.
            logger.setUseParentHandlers(false);
            try {
                SimpleFormatter fmt = new SimpleFormatter();
                handler = new FileHandler("data/ibia.log");
                handler.setFormatter(fmt);
                logger.addHandler(handler);
            } catch (Exception e) {
                System.out.println("[ibia | WARN] Failed to access log file (ibia.log).");
                System.out.println("[ibia | WARN] All log messages will be printed to console for this session.");
            }
        }

        return logger;
    }

    public static void info(String msg) {
        msg = "[ibia] " + msg;
        getLogger().info(msg);
    }

    public static void warn(String msg) {
        msg = "[ibia] " + msg;
        getLogger().log(Level.WARNING, msg);
    }

    public static void error(String msg) {
        msg = "[ibia] " + msg;
        getLogger().log(Level.SEVERE, msg);
    }

    public static Handler getHandler() {
        return logger.getHandlers()[0];
    }
}
