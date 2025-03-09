package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class LoggerHelper {
    private static final Logger logger = LogManager.getLogger(LoggerHelper.class);
    private static final String LOG_DIRECTORY = "logs";

    static {
        createLogsDirectory();
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    private static void createLogsDirectory() {
        File logsDir = new File(LOG_DIRECTORY);
        if (!logsDir.exists()) {
            boolean dirCreated = logsDir.mkdirs();
            if (dirCreated) {
                logger.info("Logs directory created: " + logsDir.getAbsolutePath());
            } else {
                logger.error("Failed to create logs directory!");
            }
        }
    }
}