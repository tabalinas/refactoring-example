package com.refactoring;

import java.io.File;
import java.util.Optional;
import java.util.prefs.Preferences;


class RefactoringExample {

    private Optional<SimpleLogger> logger;
    private Preferences preferences;

    public void init() {
        // make sure the code only runs on mac os x
        if (System.getProperty("mrj.version") == null || !System.getProperty("os.name").startsWith("Mac OS"))
        {
            System.err.println("Not running on a Mac OS X system.");
            System.exit(1);
        }

        // get preferences and initialize UI
        preferences = Preferences.userNodeForPackage(this.getClass());
        User user = IdentityProvider.getCurrentUser();
        if(user.role == UserRole.Sales && System.getProperty("maintenance") == null) {
            MainMenu.create(UserRole.Sales);
            Toolbar.create(true, preferences.getBoolean("FLAT_BUTTONS", true));
        } else {
            Toolbar.create(false);
            if(user.role == UserRole.Sales) {
                Warning.render("Maintenance, sorry for inconvenience");
            } else {
                Warning.render("Access forbidden");
            }
        }

        // initialize logger
        // logging mode = 2 -> suppressed logging
        if (preferences.getInt("LOGGING_MODE", 1) != 2) {
            // do all the logfile setup stuff
            LoggerInterface currentLoggingLevel = LoggerInterface.LOG_ERROR;

            File errorFile = new File("errors.txt");
            File warningFile = new File("warnings.txt");
            File debugFile = new File("debug.txt");

            // order of checks is important; want to go with more granular if multiple files exist
            if (errorFile.exists()) currentLoggingLevel = LoggerInterface.LOG_ERROR;
            if (warningFile.exists()) currentLoggingLevel = LoggerInterface.LOG_WARNING;
            if (debugFile.exists()) currentLoggingLevel = LoggerInterface.LOG_DEBUG;

            logger = Optional.of(new SimpleLogger("log.txt", currentLoggingLevel, true, true));
        } else {
            logger = Optional.empty();
        }
    }

    public void logMessage(String message) {
        logger.ifPresent(l -> l.log(message));
    }

}