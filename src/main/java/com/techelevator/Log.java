package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    public static PrintWriter writer = null;
    public static void log(String message) {
        try {
            File file = new File("/Users/kanya/Desktop/Cap Stone Project/capstone-1/src/main/java/com/techelevator/Log" +
                    LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + "-" + "log.txt");
            if (writer == null) {
                writer = new PrintWriter(new FileOutputStream(file));
            }
            writer.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " " + message);
            writer.flush();
        } catch (Exception e) {
            System.out.println("File was not found.");
        }
    }

}
