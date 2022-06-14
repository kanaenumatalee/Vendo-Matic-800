package com.techelevator.view;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Item {

    public int quantity = 5;
    String csvLocation = "/Users/kanya/Desktop/Cap Stone Project/capstone-1/vendingmachine.csv";
    Map<String, String[]> itemInfo = new HashMap<>();
    public Map<String, Integer> itemQuantityMap = new HashMap<>();
    Scanner sc = null;

    public  Map<String, String[]> itemInfo() throws FileNotFoundException {
        sc = new Scanner(new File(csvLocation));
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArray = line.split("\\|");  // [A1, Potato Crisps, 3.05, Chip]
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
                    System.out.println(lineArray[i] + " | Quantity: " + quantity);
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
            itemQuantityMap.put(lineArray[0], quantity);
        }
        return itemQuantityMap;
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
