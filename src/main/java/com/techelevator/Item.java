package com.techelevator;

import java.io.*;
import java.util.*;

public class Item {

    public int salesCount = 0;
    String csvLocation = "/Users/kanya/Desktop/Cap Stone Project/capstone-1/vendingmachine.csv";
    Map<String, String[]> itemInfo = new HashMap<>();                // <slot, [0-Slot, 1-Item, 2-Price,3-Type]>
    public Map<String, Integer> itemQuantityMap = new HashMap<>();   // [Key: Slot, Value: Quantity]
    public Map<String, Integer> itemSalesCountMap = new HashMap<>();
    Scanner sc = null;

    public void itemInfo() throws FileNotFoundException {
        int quantity = 5;
        sc = new Scanner(new File(csvLocation));
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArray = line.split("\\|");   // [A1, Potato Crisps, 3.05, Chip]
            itemInfo.put(lineArray[0], lineArray);          // <A1, [A1, Potato Crisps, 3.05, Chip]>
            itemQuantityMap.put(lineArray[0], quantity);
            itemSalesCountMap.put(lineArray[0], salesCount);
        }
    }

    private void checkItemSetup() throws FileNotFoundException {
        if (itemInfo.isEmpty() || itemInfo.equals(null)) {
            itemInfo();
        }
    }

    public void displayItems() throws FileNotFoundException {
        checkItemSetup();
        try (Scanner scanSlot = new Scanner(new File(csvLocation))) {
            System.out.println("SLOT | ITEM | PRICE | TYPE | QTY");
            while (scanSlot.hasNextLine()) {
                String line = scanSlot.nextLine();           // A1|Potato Crisps|3.05|Chip
                int delimiter = line.indexOf("|");           // | = 2
                String slot = line.substring(0, delimiter);  // A1, A2, A3...
                String item = itemInfo.get(slot)[1];         // Potato Crisps
                String price = itemInfo.get(slot)[2];        // 3.05
                String type = itemInfo.get(slot)[3];         // Chip
                int qty = itemQuantityMap.get(slot);         // 5
                
                System.out.print(
                          slot  + " | "
                        + item  + " | $"
                        + price + " | "
                        + type  + " | "
                );
                if(qty == 0) {
                    System.out.println("SOLD OUT");
                } else {
                    System.out.println(qty);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }
}
