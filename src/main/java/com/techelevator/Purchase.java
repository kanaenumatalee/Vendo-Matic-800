package com.techelevator;

import com.techelevator.view.Menu;

import java.io.*;
import java.text.DecimalFormat;

import static com.techelevator.view.Menu.in;
import static java.lang.System.out;

public class Purchase {
    private static final String PURCHASE_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_OPTION = { PURCHASE_OPTION_FEED_MONEY,
                                                      PURCHASE_OPTION_SELECT_PRODUCT,
                                                      PURCHASE_OPTION_FINISH_TRANSACTION};

    private double balance;
    private String choice;
    private Menu menu;

    public Purchase(Menu menu) {
        this.menu = menu;
    }

    Item item = new Item();
    DecimalFormat df = new DecimalFormat("0.00");

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addToBalance(double dollar) {
        this.balance += dollar;
    }

    public void reduceBalance(double item) {
        this.balance -= item;
    }

    public void run() throws FileNotFoundException {
        while (true) {
            choice = (String) menu.getChoiceFromOptions(PURCHASE_OPTION);
            if (choice.equals(PURCHASE_OPTION_FEED_MONEY)) {
                feedMoney();
            } else if (choice.equals(PURCHASE_OPTION_SELECT_PRODUCT)) {
                selectItem();
            }
        }
    }

    public void feedMoney() {
        out.print("Please enter whole dollar amounts: ");
        int dollar = Integer.parseInt(in.nextLine());
        addToBalance(dollar);
        System.out.println("Current money provided: $" + df.format(getBalance()));
    }

    public boolean haveEnoughBalance(double item) {
        if(getBalance() <= item) {
            return false;
        }
        return true;
    }

    public void selectItem() throws FileNotFoundException {
        item.displayItems();
        out.print("Please select an item: ");
        String order = in.nextLine();
        if (!item.getItemQuantity().containsKey(order)) {            // product does not exist
            out.println("Sorry, the item does not exist. Please enter a valid slot location.");
        }
        if (item.getItemQuantity().get(order) == 0) {                // product qty == 0
            out.println("Sorry, the item is SOLD OUT.");
        }
        if (item.getItemQuantity().containsKey(order)) {            // finds product
            if (order.contains("A")) {
                if(!haveEnoughBalance(Double.parseDouble(item.itemInfo().get(order)[2]))){
                    out.println("You current balance $" + df.format(getBalance()) + " is not enough to buy this item. Please add more money or select another item.");
                } else {
                    out.println("Thank you for ordering " + item.itemInfo().get(order)[1] + "! That will be $" + item.itemInfo().get(order)[2] + "!");
                    out.println("Dispensing...");
                    out.println("Crunch Crunch, Yum!");
                    item.itemQuantityMap.put(order, item.itemQuantityMap.get(order)-1); //reduce quantity
                    if(item.itemInfo().containsKey(order)){
                        reduceBalance(Double.parseDouble(item.itemInfo().get(order)[2]));
                    }
                    out.println("Money remaining: $" + df.format(getBalance()));
                    item.itemSalesCountMap.put(order, item.itemSalesCountMap.get(order)+1);
                }
            } else if (order.contains("B")) {
                out.println("Thank you for ordering " + item.itemInfo().get(order)[1] + "! That will be $" + item.itemInfo().get(order)[2] + "!");
                out.println("Dispensing...");
                out.println("That will be $" + item.itemInfo().get(order)[2] + "!");
                out.println("Dispensing...");
                out.println("Munch Munch, Yum!");
                item.getItemQuantity().put(order, item.getItemQuantity().get(order)-1);
                if(item.itemInfo().containsKey(order)){
                    reduceBalance(Double.parseDouble(item.itemInfo().get(order)[2]));
                }
                out.println("Money remaining: $" + df.format(getBalance()));
                item.itemSalesCountMap.put(order, item.itemSalesCountMap.get(order)+1);
            } else if (order.contains("C")) {
                out.println("Thank you for ordering " + item.itemInfo().get(order)[1] + "! That will be $" + item.itemInfo().get(order)[2] + "!");
                out.println("Dispensing...");
                out.println("Glug Glug, Yum!");
                item.getItemQuantity().put(order, item.getItemQuantity().get(order)-1);
                if(item.itemInfo().containsKey(order)){
                    reduceBalance(Double.parseDouble(item.itemInfo().get(order)[2]));
                }
                out.println("Money remaining: $" + df.format(getBalance()));
                item.itemSalesCountMap.put(order, item.itemSalesCountMap.get(order)+1);
            } else if (order.contains("D")) {
                out.println("Thank you for ordering " + item.itemInfo().get(order)[1] + "! That will be $" + item.itemInfo().get(order)[2] + "!");
                out.println("Dispensing...");
                out.println("Chew Chew, Yum!");
                item.getItemQuantity().put(order, item.getItemQuantity().get(order)-1);
                if(item.itemInfo().containsKey(order)){
                    reduceBalance(Double.parseDouble(item.itemInfo().get(order)[2]));
                }
                out.println("Money remaining: $" + df.format(getBalance()));
                item.getItemSales().put(order, item.itemSalesCountMap.get(order)+1);
            }
        }
    }


}

