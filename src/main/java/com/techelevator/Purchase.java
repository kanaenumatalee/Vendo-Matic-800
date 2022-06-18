package com.techelevator;

import com.techelevator.view.Menu;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Purchase extends Menu {
    private static final String PURCHASE_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_OPTION = { PURCHASE_OPTION_FEED_MONEY,
                                                      PURCHASE_OPTION_SELECT_PRODUCT,
                                                      PURCHASE_OPTION_FINISH_TRANSACTION};

    private double balance;
    private Item item;

    Scanner in = new Scanner(System.in);
    public static DecimalFormat df = new DecimalFormat("#,##0.00");

    public Purchase(){};
    public Purchase(Item item) {
        this.item = item;
    }

    public void run() throws FileNotFoundException {
        boolean toPurchase = true;

        while (toPurchase) {
            String choice = (String) getChoiceFromOptions(PURCHASE_OPTION);
            if (choice.equals(PURCHASE_OPTION_FEED_MONEY)) {
                System.out.print("Please insert $1, $2, $5, or $10: ");
                String userChoice = in.nextLine();
                feedMoney(userChoice);

            } else if (choice.equals(PURCHASE_OPTION_SELECT_PRODUCT)) {
                item.displayItems();
                System.out.print("Please select an item: ");
                String orderInput = in.nextLine();
                selectItem(item, orderInput);
            } else if (choice.equals(PURCHASE_OPTION_FINISH_TRANSACTION)) {
                returnChange(getBalance());
                toPurchase = false;
            }
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addToBalance(double dollar) { this.balance += dollar; }

    public void reduceBalance(double item) {
        this.balance -= item;
    }

    public boolean haveEnoughBalance(String item) {
        if(getBalance() < Double.parseDouble(item)) {
            return false;
        }
        return true;
    }

    public void feedMoney(String userChoice) {
        int dollar = 0;
        try {
            dollar = Integer.parseInt(userChoice);
            if (dollar == 1 || dollar == 2 || dollar == 5 || dollar == 10) {
                addToBalance(dollar);
                System.out.println("Current money provided: $" + df.format(getBalance()));
                Log.log("FEED MONEY: $" + df.format(dollar) + " $" + df.format(getBalance()));
            } else {
                System.out.println("Please insert $1, $2, $5, or $10 only.");
            }
        } catch (Exception e) {
            System.out.println(userChoice + " is not a valid integer.");
        }
    }

    public void getTypeSound(String Type) {
        switch (Type) {
            case "Chip": System.out.println("Dispensing...\nCrunch Crunch, Yum!"); break;
            case "Candy": System.out.println("Dispensing...\nMunch Munch, Yum!"); break;
            case "Drink": System.out.println("Dispensing...\nGlug Glug, Yum!"); break;
            case "Gum": System.out.println("Dispensing...\nChew Chew, Yum!"); break;
            default: System.out.println(""); break;
        }
    }

    public void reduceQuantity(Item item, String slot) {
        item.itemQuantity.put(slot, item.itemQuantity.get(slot)-1);
    }

    public void selectItem(Item item, String orderInput) throws FileNotFoundException {
        String order = orderInput.toUpperCase();
        boolean hasOrder = item.itemQuantity.containsKey(order);
        String[] itemIndex = item.itemInfo.get(order);              // 1-Item, 2-Price, 3-Type

        if (!hasOrder) {                                            // product does not exist
            System.out.println("Sorry, the item does not exist. Please enter a valid slot location.");
        } else if (item.itemQuantity.get(order) <= 0) {          // product qty == 0
            System.out.println("Sorry, the item is SOLD OUT.");
        } else if (!haveEnoughBalance(itemIndex[2])) {
            System.out.println("Your current balance $" + df.format(getBalance())
                    + " is not enough to buy this item. "
                    + "Please add more money or select another item.");
        } else if (hasOrder) {                                      // finds product
            System.out.println("Thank you for ordering " + itemIndex[1]
                    + "! That will be $" + itemIndex[2] + "!");
            reduceQuantity(item, order);
            getTypeSound(itemIndex[3]);
            reduceBalance(Double.parseDouble(itemIndex[2]));
            System.out.println("Money remaining: $" + df.format(getBalance()));
            item.itemSalesCount.put(order, item.itemSalesCount.get(order)+1);
            Log.log(item.itemInfo.get(order)[1]
                    + " " + item.itemInfo.get(order)[0]
                    + " $" +  item.itemInfo.get(order)[2]
                    + " $" +  df.format(getBalance()));
        }
    }

    public double returnTotalSales(Item item) {
        double totalSales = 0;
        for(String slot : item.itemIdList) {
            Integer qty = item.itemSalesCount.get(slot);  // sales count
            double price = Double.parseDouble(item.itemInfo.get(slot)[2]);
            totalSales += qty * price;
        }
        return totalSales;
    }

    public void returnChange(double balance) {
        double newBalance = balance;
        int quarter = 0;
        int dime = 0;
        int nickle = 0;

        while(newBalance >= 0.25) {
            reduceBalance(0.25);
            newBalance -= 0.25;
            quarter++;
        }

        while(newBalance >= 0.1) {
            reduceBalance(0.1);
            newBalance -= 0.1;
            dime++;
        }

        while(newBalance >= 0.05) {
            reduceBalance(0.05);
            newBalance -= 0.05;
            nickle++;
        }

        System.out.println("Dispensing change..."
                    + "\nQuarter: " + quarter
                    + "\nDime: "    + dime
                    + "\nNickle: "  + nickle
                    + "\nRemaining money: $" + df.format(newBalance));

        Log.log("GIVE CHANGE: $" + df.format(balance) + " $" + df.format(newBalance));
    }
}

