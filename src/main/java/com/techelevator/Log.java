package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
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
            String dateString = dateFormat.format(new Date());
            String path = "src/main/java/com/techelevator/Log/Log.txt";
            File file = new File(path);
            if (writer == null) {
                writer = new PrintWriter(new FileOutputStream(file, true));
            }
            writer.println(dateString + " " + message);
            writer.flush();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        }
    }
}
