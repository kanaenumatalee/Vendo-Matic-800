package com.techelevator;

import java.io.*;
import java.util.*;

public class Item {

    public int itemQuantity = 5;
    public int salesCount = 0;
    String csvLocation = "/Users/kanya/Desktop/Cap Stone Project/capstone-1/vendingmachine.csv";
    Map<String, String[]> itemInfo = new HashMap<>();                // <slot, [0-Slot, 1-Item, 2-Price,3-Type]>
    public Map<String, Integer> itemQuantityMap = new HashMap<>();   // [Key: Slot, Value: Quantity]
    public Map<String, Integer> itemSalesCountMap = new HashMap<>();
    Scanner sc = null;

    public  Map<String, String[]> itemInfo() throws FileNotFoundException {
        sc = new Scanner(new File(csvLocation));
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArray = line.split("\\|");   // [A1, Potato Crisps, 3.05, Chip]
            itemInfo.put(lineArray[0], lineArray); // <A1, [A1, Potato Crisps, 3.05, Chip]>
        }
        return itemInfo;
    }

    private void checkItemSetup() throws FileNotFoundException {
        if (itemInfo().isEmpty() || itemInfo().equals(null)) {
            itemInfo();
        } else if (itemQuantityMap.isEmpty() || itemQuantityMap.equals(null)) {
            getItemQuantity();
        }
    }

    public void displayItems() throws FileNotFoundException {
        checkItemSetup();
        try (Scanner scanSlot = new Scanner(new File(csvLocation))) {
            System.out.println("SLOT | ITEM | PRICE | TYPE | QTY");
            while (scanSlot.hasNextLine()) {
                String line = scanSlot.nextLine();   // A1|Potato Crisps|3.05|Chip
                int delimeter = line.indexOf("|");// | = 2
                String slot = line.substring(0, delimeter); // A1, A2, A3...
                String item = itemInfo.get(slot)[1]; // Potato Crisps
                String price = itemInfo.get(slot)[2]; // 3.05
                String type = itemInfo.get(slot)[3]; // Chip
                int qty = itemQuantityMap.get(slot); // 5

                System.out.println(
                          slot + " | "
                        + item + " | $"
                        + price + " | "
                        + type + " | "
                        + qty
                );
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    public Map<String, Integer> getItemQuantity() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(csvLocation));
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArray = line.split("\\|");  // [A1, Potato Crisps, 3.05, Chip]
            itemQuantityMap.put(lineArray[0], itemQuantity); // <A1, 5><A2, 5>...
        }
        return itemQuantityMap;
    }

    public Map<String, Integer> getItemSales() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(csvLocation));
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArray = line.split("\\|");  // [A1, Potato Crisps, 3.05, Chip]
            itemSalesCountMap.put(lineArray[0], salesCount);
        }
        return itemSalesCountMap;
    }


/*
    public Map<String, Integer> getItemPriceMap() throws FileNotFoundException {
        Map<String, Integer> itemPriceMap = new HashMap<>();
        Scanner sc = new Scanner(new File(csvLocation));
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArray = line.split("\\|");  // [A1, Potato Crisps, 3.05, Chip]
            itemPriceMap.put(lineArray[0], Integer.valueOf(lineArray[2]));
        }
        return itemPriceMap;
    }
    */

}
