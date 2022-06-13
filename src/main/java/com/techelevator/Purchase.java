package com.techelevator;

import com.techelevator.view.Item;
import com.techelevator.view.Menu;

import java.io.*;

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void addToBalance(int dollar) {
        this.balance += dollar;
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
        System.out.println("Current money provided: $" + getBalance());
    }

    public void selectItem() throws FileNotFoundException {
        item.displayItems();
        out.print("Please select an item: ");
        String order = in.nextLine();
        if (!item.getItemQuantityMap().containsKey(order)) {
            out.println("Sorry, the item does not exist.");
        }
        if (item.getItemQuantityMap().get(order) == 0) {
            out.println("Sorry, the item is SOLD OUT.");
        }
        if (item.getItemQuantityMap().containsKey(order)) {
            if (order.contains("A")) {
                out.println("Crunch Crunch, Yum!");
                item.getItemQuantityMap().put(order, item.getItemQuantityMap().get(order)-1);
            } else if (order.contains("B")) {
                out.println("Munch Munch, Yum!");
            } else if (order.contains("C")) {
                out.println("Glug Glug, Yum!");
            } else if (order.contains("D")) {
                out.println("Chew Chew, Yum!");
            }
        }
    }


}

