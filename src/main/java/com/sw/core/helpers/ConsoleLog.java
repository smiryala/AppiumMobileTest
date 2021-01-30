package com.sw.core.helpers;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ConsoleLog {

    private static Logger log = Logger.getLogger(ConsoleLog.class);

    /**
     * Prints an debug message, with the thread number printed as well.
     *
     * @param msg String for the message to be printed.
     */
    public static void debug(String msg) {
        String thread = "Thread ID = " + Thread.currentThread().getId();
        if (!StringUtils.isBlank(msg)) {
            msg = String.format("%-14s -- %s", thread, msg);
            log.debug(msg);
        }
    }

    /**
     * Prints an error message, with the thread number printed as well.
     *
     * @param msg String for the message to be printed.
     */
    public static void error(String msg) {
        String thread = "Thread ID = " + Thread.currentThread().getId();
        if (!StringUtils.isBlank(msg)) {
            msg = String.format("%-14s -- %s", thread, msg);
            log.error(msg);
        }
    }

    /**
     * Prints an info message, with the thread number printed as well.
     *
     * @param msg String for the message to be printed.
     */
    public static void info(String msg) {
        String thread = "Thread ID = " + Thread.currentThread().getId();
        if (!StringUtils.isBlank(msg)) {
            msg = String.format("%-14s -- %s", thread, msg);
            log.info(msg);
        }
    }

    /**
     * Prints an error message, with the thread number printed as well.
     * <br>
     * Prints the stack trace of the given throwable as well.
     *
     * @param msg    String for the message to be printed.
     * @param thrown Throwable for the error
     */
    public static void error(String msg, Throwable thrown) {
        String thread = "Thread ID = " + Thread.currentThread().getId();
        if (!StringUtils.isBlank(msg)) {
            msg = String.format("%-14s -- %s", thread, msg);
            log.error(msg, thrown);
        }
    }

    /**
     * Sets the log level based on a string input.
     *
     * @param logLvl input input to be used for the bug level.
     */
    public static void setLogLevel(int logLvl) {
        log.setLevel(Level.toLevel(logLvl));

    }

    /**
     * Sets the log level based on a string input.
     *
     * @param logLvl String input to be used for the bug level.
     */
    public static void setLogLevel(String logLvl) {
        log.setLevel(Level.toLevel(logLvl));
    }

}
