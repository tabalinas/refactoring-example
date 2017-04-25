package com.refactoring;

public class LoggerConfigBuilder {
    private String path;
    private LoggerInterface currentLoggingLevel;
    private boolean includeStacktrace;
    private boolean isVerbose;

    public LoggerConfigBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public LoggerConfigBuilder setCurrentLoggingLevel(LoggerInterface currentLoggingLevel) {
        this.currentLoggingLevel = currentLoggingLevel;
        return this;
    }

    public LoggerConfigBuilder setIncludeStacktrace(boolean includeStacktrace) {
        this.includeStacktrace = includeStacktrace;
        return this;
    }

    public LoggerConfigBuilder setIsVerbose(boolean isVerbose) {
        this.isVerbose = isVerbose;
        return this;
    }

    public LoggerConfig createLoggerConfig() {
        return new LoggerConfig(path, currentLoggingLevel, includeStacktrace, isVerbose);
    }
}