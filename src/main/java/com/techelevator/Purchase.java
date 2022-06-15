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

    Item item = new Item();
    DecimalFormat df = new DecimalFormat("0.00");

    public Purchase(Menu menu) {
        this.menu = menu;
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

    public boolean haveEnoughBalance(String item) {
        if(getBalance() <= Double.parseDouble(item)) {
            return false;
        }
        return true;
    }

    public void feedMoney() {
        out.print("Please insert $1, $2, $5, or $10: ");
        int dollar = Integer.parseInt(in.nextLine());
        if (dollar == 1 || dollar == 2 || dollar == 5 || dollar == 10) {
            addToBalance(dollar);
            out.println("Current money provided: $" + df.format(getBalance()));
            Log.log("FEED MONEY $" + dollar + " $" + df.format(getBalance()));
        } else {
            out.println("Please insert $1, $2, $5, or $10.");
        }
    }

    private void getTypeSound(String Type) {
        switch (Type) {
            case "Chip": out.println("Dispensing...\nCrunch Crunch, Yum!"); break;
            case "Candy": out.println("Dispensing...\nMunch Munch, Yum!"); break;
            case "Drink": out.println("Dispensing...\nGlug Glug, Yum!"); break;
            case "Gum": out.println("Dispensing...\nChew Chew, Yum!"); break;
            default: out.println(""); break;
        }
    }

    private int reduceQuantity(String slot, int qty) {
        return item.itemQuantityMap.put(slot, qty-1);
    }

    public void selectItem() throws FileNotFoundException {
        item.displayItems();
        out.print("Please select an item: ");
        String orderInput = in.nextLine();
        String order = orderInput.toUpperCase();
        boolean hasOrder = item.getItemQuantity().containsKey(order);
        int qty = item.getItemQuantity().get(order);
        String[] itemIndex = item.itemInfo().get(order);

        if (!hasOrder) {            // product does not exist
            out.println("Sorry, the item does not exist. Please enter a valid slot location.");
        } else if (qty <= 0) {                // product qty == 0
            out.println("Sorry, the item is SOLD OUT.");
        } else if (!haveEnoughBalance(itemIndex[2])) {
            out.println("You current balance $" + df.format(getBalance())
                    + " is not enough to buy this item. "
                    + "Please add more money or select another item.");
        } else if (hasOrder) {            // finds product
            out.println("Thank you for ordering " + itemIndex[1]
                    + "! That will be $" + itemIndex[2] + "!");
            reduceQuantity(order, qty);
            getTypeSound(itemIndex[3]);
            reduceBalance(Double.parseDouble(item.itemInfo().get(order)[2]));
            out.println("Money remaining: $" + df.format(getBalance()));
            //item.getItemSales().put(order, item.itemSalesCountMap.get(order)+1);
            Log.log(item.itemInfo().get(order)[1]
                    + " $" + item.itemInfo().get(order)[2]
                    + " $" +  df.format(getBalance()));
        }
    }

    public void returnChange(double balance) {
        double newBalance = 0;
        double quarter = 25;
        double dime = 10;
        double nickle = 5;
    }


}

