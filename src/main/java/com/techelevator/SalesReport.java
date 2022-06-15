package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SalesReport {
    public static PrintWriter writer = null;
    public static void log(Map<String, Integer> map) {
        try {
            File file = new File("/Users/kanya/Desktop/Cap Stone Project/capstone-1/src/main/java/com/techelevator/SalesReport" +
                    LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + "-" + "sales.log");
            if (writer == null) {
                writer = new PrintWriter(new FileOutputStream(file, true));
            }
            writer.println();

            writer.flush();
        } catch (Exception e) {
            System.out.println("File was not found.");
        }
    }

}
