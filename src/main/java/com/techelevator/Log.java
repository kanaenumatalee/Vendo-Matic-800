package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Log {
    public static PrintWriter writer = null;
    public static void log(String message) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm: aa");
            String dateString = dateFormat.format(new Date()).toString();
            File file = new File("/Users/kanya/Desktop/Cap Stone Project/capstone-1/src/main/java/com/techelevator/Log/" +
                    LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + "-log.txt");
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
