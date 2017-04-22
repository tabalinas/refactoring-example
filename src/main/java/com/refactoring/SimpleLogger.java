package com.refactoring;


public class SimpleLogger {

    private final String path;
    private LoggerInterface currentLoggingLevel;
    private boolean includeStacktrace;
    private boolean isVerbose;

    public SimpleLogger(String path, LoggerInterface currentLoggingLevel, boolean includeStacktrace, boolean isVerbose) {
        this.path = path;
        this.currentLoggingLevel = currentLoggingLevel;
        this.includeStacktrace = includeStacktrace;
        this.isVerbose = isVerbose;
    }

    public void log(String message) {
        // NOTE: logging message
    }
    
}
