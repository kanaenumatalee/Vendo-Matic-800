package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public static PrintWriter writer = null;
    public static void log(String message) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
            String dateString = dateFormat.format(new Date()).toString();
            String path = "/Users/kanya/Desktop/Cap Stone Project/capstone-1/src/main/java/com/techelevator/Log/";
            File file = new File(path + "Log.txt");
            if (writer == null) {
                writer = new PrintWriter(new FileOutputStream(file, true));
            }
            writer.println(dateString + " " + message);
            writer.flush();
        } catch (Exception e) {
            System.out.println("File was not found.");
        }
    }

}
