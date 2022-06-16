package com.techelevator;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static java.lang.System.out;

public class SalesReport {
    private Item item;
    public SalesReport(Item item) {
        this.item = item;
    }
    public void getSalesReport() {
        try {
            File file = new File("/Users/kanya/Desktop/Cap Stone Project/capstone-1/src/main/java/com/techelevator/SalesReport/" +
                    LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + "-sales.txt");
            PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));


            writer.flush();
        } catch (Exception e) {
            System.out.println("File was not found.");
        }
    }

}
