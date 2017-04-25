package com.refactoring;


public class SimpleLogger {

    private final String path;
    private LoggerInterface currentLoggingLevel;
    private boolean includeStacktrace;
    private boolean isVerbose;

    public SimpleLogger(LoggerConfig loggerConfig) {
        this.path = loggerConfig.getPath();
        this.currentLoggingLevel = loggerConfig.getCurrentLoggingLevel();
        this.includeStacktrace = loggerConfig.isIncludeStacktrace();
        this.isVerbose = loggerConfig.isVerbose();
    }

    public void log(String message) {
        // NOTE: logging message
    }
    
}
