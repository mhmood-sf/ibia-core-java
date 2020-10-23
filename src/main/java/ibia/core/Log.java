package ibia.core;

import java.io.File;
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
            logger.setUseParentHandlers(false); // do not log to parents, for efficiency

            try {
                // Create the file if it doesn't exist
                File file = new File("data/ibia.log");
                if (!file.isFile()) file.createNewFile();

                // Set the formatter and add handler to logger
                SimpleFormatter fmt = new SimpleFormatter();
                handler = new FileHandler("data/ibia.log", true);
                handler.setFormatter(fmt);
                logger.addHandler(handler);

                // Indicate a new log session is starting.
                info("===== SESSION START =====");
            } catch (Exception e) {
                System.out.println("WARN: [ibia] Failed to access log file (ibia.log).");
                System.out.println("WARN: [ibia] All log messages will be printed to console for this session.");
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
