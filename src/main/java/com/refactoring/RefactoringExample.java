package com.refactoring;

import java.io.File;
import java.util.Optional;
import java.util.prefs.Preferences;


class RefactoringExample {

    public static final int SUPPRESS_LOGGING_MODE = 2;
    public static final int DEFAULT_LOGGING_MODE = 1;
    private Optional<SimpleLogger> logger;
    private Preferences preferences;

    public void init() {
        ensureRunningOnMac();
        initPreferences();
        renderUI();
        initLogger();
    }

    private void ensureRunningOnMac() {
        boolean noMajorVersion = System.getProperty("mrj.version") == null;
        boolean isMacOS = System.getProperty("os.name").startsWith("Mac OS");

        if (noMajorVersion || !isMacOS)
        {
            System.err.println("Not running on a Mac OS X system.");
            System.exit(1);
        }
    }

    private void initPreferences() {
        preferences = Preferences.userNodeForPackage(this.getClass());
    }

    private void renderUI() {
        User user = IdentityProvider.getCurrentUser();

        if(isSalesViewAvailable(user)) {
            renderSalesUI();
        } else {
            renderReadonlyUI();
        }
    }

    private boolean isSalesViewAvailable(User user) {
        return user.role == UserRole.Sales && System.getProperty("maintenance") == null;
    }

    private void renderSalesUI() {
        MainMenu.create(UserRole.Sales);
        Toolbar.create(true, preferences.getBoolean("FLAT_BUTTONS", true));
    }

    private void renderReadonlyUI() {
        User user = IdentityProvider.getCurrentUser();
        Toolbar.create(false);
        Warning.render(user.role == UserRole.Sales ? "Maintenance, sorry for inconvenience" : "Access forbidden");
    }

    private void initLogger() {
        int loggingMode = preferences.getInt("LOGGING_MODE", DEFAULT_LOGGING_MODE);

        if (loggingMode == SUPPRESS_LOGGING_MODE) {
            logger = Optional.empty();
            return;
        }

        // do all the logfile setup stuff
        LoggerInterface currentLoggingLevel = LoggerInterface.LOG_ERROR;

        File errorFile = new File("errors.txt");
        File warningFile = new File("warnings.txt");
        File debugFile = new File("debug.txt");

        // order of checks is important; want to go with more granular if multiple files exist
        if (errorFile.exists()) currentLoggingLevel = LoggerInterface.LOG_ERROR;
        if (warningFile.exists()) currentLoggingLevel = LoggerInterface.LOG_WARNING;
        if (debugFile.exists()) currentLoggingLevel = LoggerInterface.LOG_DEBUG;

        LoggerConfig loggerConfig = new LoggerConfigBuilder()
                .setPath("log.txt")
                .setCurrentLoggingLevel(currentLoggingLevel)
                .setIncludeStacktrace(true)
                .setIsVerbose(true)
                .createLoggerConfig();

        SimpleLogger logger = new SimpleLogger(loggerConfig);

        this.logger = Optional.of(logger);
    }

    public void logMessage(String message) {
        logger.ifPresent(l -> l.log(message));
    }

}