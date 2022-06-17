package com.techelevator;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SalesReport {
    private Item item;
    private Purchase purchase;

    public SalesReport(Item item, Purchase purchase) {
        this.item = item;
        this.purchase = purchase;
    }

    public void getSalesReport() {
        try {
            String path = "/Users/kanya/Desktop/Cap Stone Project/capstone-1/src/main/java/com/techelevator/SalesReport/";
            DateTimeFormatter DTFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy hh-mm-ss a");
            LocalDateTime current = LocalDateTime.now();
            File file = new File(path + current.format(DTFormat) + "-sales.txt");
            PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));

            for(String slot : item.itemIdList) {
                String name = item.itemInfo.get(slot)[1];
                int sale = item.itemSalesCountMap.get(slot);

                writer.println(name + "|" + sale);
            }
            writer.println("TOTAL SALES: " + purchase.df.format(purchase.returnTotalSales()));

            writer.flush();
        } catch (Exception e) {
            System.out.println("File was not found.");
        }
    }
}
