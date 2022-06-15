package com.techelevator;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Item {

    public int itemQuantity = 5;
    public int salesCount = 0;
    String csvLocation = "/Users/kanya/Desktop/Cap Stone Project/capstone-1/vendingmachine.csv";
    Map<String, String[]> itemInfo = new HashMap<>();              // [0-Slot, 1-Item, 2-Price,3-Type]
    public Map<String, Integer> itemQuantityMap = new HashMap<>(); // [Key: Slot, Value: Quantity]
    public Map<String, Integer> itemSalesCountMap = new HashMap<>();
    Scanner sc = null;

    public  Map<String, String[]> itemInfo() throws FileNotFoundException {
        sc = new Scanner(new File(csvLocation));
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArray = line.split("\\|");   // [A1, Potato Crisps, 3.05, Chip]
            itemInfo.put(lineArray[0], lineArray);
        }
        return itemInfo;
    }

    public void displayItems() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(csvLocation));
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArray = line.split("\\|");  // [A1, Potato Crisps, 3.05, Chip]

            for (int i = 0; i < lineArray.length; i++) {
                if (i == 2) {
                    System.out.print("$" + lineArray[2] + " | ");
                } else if (i == 3) {
                    System.out.println(lineArray[i] + " | Quantity: " + itemQuantityMap.get(lineArray[0]));
                } else {
                    System.out.print(lineArray[i] + " | ");
                }
            }
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
