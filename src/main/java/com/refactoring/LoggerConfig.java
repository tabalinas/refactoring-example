package com.refactoring;

public class LoggerConfig {
    private final String path;
    private final LoggerInterface currentLoggingLevel;
    private final boolean includeStacktrace;
    private final boolean isVerbose;

    public LoggerConfig(String path, LoggerInterface currentLoggingLevel, boolean includeStacktrace, boolean isVerbose) {
        this.path = path;
        this.currentLoggingLevel = currentLoggingLevel;
        this.includeStacktrace = includeStacktrace;
        this.isVerbose = isVerbose;
    }

    public String getPath() {
        return path;
    }

    public LoggerInterface getCurrentLoggingLevel() {
        return currentLoggingLevel;
    }

    public boolean isIncludeStacktrace() {
        return includeStacktrace;
    }

    public boolean isVerbose() {
        return isVerbose;
    }
}
