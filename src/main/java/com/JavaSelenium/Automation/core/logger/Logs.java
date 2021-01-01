package com.JavaSelenium.Automation.core.logger;

public class Logs {

    private Logs(){}

    public static org.apache.log4j.Logger getLogger() {
        return org.apache.log4j.Logger.getRootLogger();
    }

    public static final void info(String message, Throwable t) {
        getLogger().info(message, t);
    }

    public static final void info(String message) {
        getLogger().info(message);
    }

    public static final void error(String message) {
        getLogger().error(message);
    }

    public static final void error(String message, Throwable t) {
        getLogger().error(message, t);
    }

    public static final void trace(String message) {
        getLogger().trace(message);
    }

    public static final void fatal(String message) {
        getLogger().fatal(message);
    }

    public static final void fatal(String message, Throwable t) {
        getLogger().fatal(message, t);
    }

    public static final void debug(String message, Throwable t) {
        getLogger().debug(message, t);
    }

    public static final void warn(String message) {
        getLogger().warn(message);
    }

    public static final void warn(String message, Throwable t) {
        getLogger().warn(message, t);
    }

    public static final void trace(String message, Throwable t) {
        getLogger().trace(message, t);
    }

    public static final void debug(String message) {
        getLogger().debug(message);
    }
}
