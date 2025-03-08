package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class LoggerHelper {
    private static final Logger logger = LogManager.getLogger(LoggerHelper.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }
    static {
        createLogsDirectory();
    }

    private static void createLogsDirectory() {
        File logsDir = new File("src/main/resources/logs");
        if (!logsDir.exists()) {
            logsDir.mkdirs();
        }
    }
}