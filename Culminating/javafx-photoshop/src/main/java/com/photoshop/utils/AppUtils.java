package com.photoshop.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AppUtils {
    private static final String COUNTER_FILE = "app_open_count.txt";
    private static final String VERSION_NUMBER = "1.0.0";
    
    // cached app open count
    private int appOpenCount = -1;

    public int getAppOpenCount() {
        if (appOpenCount == -1) {
            File file = new File(COUNTER_FILE);
            if (!file.exists()) {
                appOpenCount = 0;  
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line = reader.readLine();
                    if (line != null) {
                        appOpenCount = Integer.parseInt(line);  
                    } else {
                        appOpenCount = 0;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    appOpenCount = 0;
                }
            }
        }
        return appOpenCount;
    }

    public void incrementAppOpenCount() {
        int currentCount = getAppOpenCount();
        int newCount = currentCount + 1;

        try (FileWriter writer = new FileWriter(COUNTER_FILE)) {
            writer.write(String.valueOf(newCount)); 
            appOpenCount = newCount;  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getVersionString() {
        return VERSION_NUMBER;
    }
}
