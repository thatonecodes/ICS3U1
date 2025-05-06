package com.photoshop.utils;

/**
 * This class contains useful application utilities to be used in App.java.
 */
public class AppUtils {
    
    public static String getVersionString() {
        return Constants.VERSION_NUMBER;
    }

    public static void quitApp() {
        System.out.println("User chose to exit app, quitting with exit code 0.");
        System.exit(0);
    }
}
