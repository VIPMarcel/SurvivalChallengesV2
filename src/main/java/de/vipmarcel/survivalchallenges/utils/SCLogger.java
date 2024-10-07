package de.vipmarcel.survivalchallenges.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SCLogger {

    public static Logger logger = Logger.getLogger("SurvivalChallenges");

    private static boolean verbose = false;

    public static void setVerbose(boolean verbose) {
        SCLogger.verbose = verbose;
    }

    public static void debug(String message) {
        if(SCLogger.verbose) {
            SCLogger.logger.log(Level.FINE, "[Debug] " + message);
        }
    }

    public static void info(String message) {
        SCLogger.logger.log(Level.INFO, message);
    }

    public static void warn(String message) {
        SCLogger.logger.log(Level.WARNING, message);
    }

    public static void error(String message, Throwable throwable) {
        SCLogger.logger.log(Level.SEVERE, message, throwable);
    }

}
