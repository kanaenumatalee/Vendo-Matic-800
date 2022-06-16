package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static java.lang.System.out;

public class SalesReport {
    Item item = new Item();
    public static PrintWriter writer = null;
    public void log(String message) {
        try {
            File file = new File("/Users/kanya/Desktop/Cap Stone Project/capstone-1/src/main/java/com/techelevator/SalesReport/" +
                    LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + "-" + "sales.log");
            if (writer == null) {
                writer = new PrintWriter(new FileOutputStream(file, true));
            }
            writer.println(message);
            writer.flush();
        } catch (Exception e) {
            System.out.println("File was not found.");
        }
    }

    public String getSalesReport() throws FileNotFoundException {
        for (Map.Entry<String, String[]> itemId : item.itemInfo.entrySet()) {
            for (Map.Entry<String, Integer> quantity : item.itemSalesCountMap.entrySet()) {
                out.println(itemId.getValue()[1] + "|" + quantity.getValue());
            }
        }
        return "";
    }


}
