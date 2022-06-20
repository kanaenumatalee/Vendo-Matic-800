package com.techelevator;

import java.io.*;
import java.util.*;

public class Item {
    public int salesCount = 0;
    public String csvLocation = "capstone-1/vendingmachine.csv";
    public List<String> itemIdList = new ArrayList<>();                     // [Slot1, Slot2, ...] = Keys
    public Map<String, String[]> itemInfo = new HashMap<>();                // {Key: slot, Value: [0-Slot, 1-Item, 2-Price,3-Type]}
    public Map<String, Integer> itemQuantity = new HashMap<>();             // {Key: Slot, Value: Quantity}
    public Map<String, Integer> itemSalesCount = new HashMap<>();           // {Key: Slot, Value: Purchase Count}
    Scanner scanner = null;

    public void setItemInfo() throws FileNotFoundException {
        scanner = new Scanner(new File(csvLocation));
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] lineArray = line.split("\\|");                   // [A1, Potato Crisps, 3.05, Chip]
            itemIdList.add(lineArray[0]);                                   // [A1, A2...]
            itemInfo.put(lineArray[0], lineArray);                          // {A1=[A1, Potato Crisps, 3.05, Chip]}
            itemQuantity.put(lineArray[0], 5);                              // {A1=5}
            itemSalesCount.put(lineArray[0], salesCount);                   // {A1=0}
        }
    }

    private void checkItemSetup() throws FileNotFoundException {
        if (itemInfo.isEmpty() || itemInfo.equals(null)) {
            setItemInfo();
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
                int qty = itemQuantity.get(slot);            // 5
                
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
