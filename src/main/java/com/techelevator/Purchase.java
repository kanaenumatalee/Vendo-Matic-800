package com.techelevator;

import com.techelevator.view.Menu;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Purchase extends Menu {
    private static final String PURCHASE_OPTION_FEED_MONEY = "Display Vending Machine Items";
    private static final String PURCHASE_OPTION_SELECT_PRODUCT = "Purchase";
    private static final String PURCHASE_OPTION_FINISH_TRANSACTION = "Exit";
    private static final String[] PURCHASE_OPTION = { PURCHASE_OPTION_FEED_MONEY,
                                                        PURCHASE_OPTION_SELECT_PRODUCT,
                                                        PURCHASE_OPTION_FINISH_TRANSACTION};

    private int balance;

    public Purchase(InputStream input, OutputStream output) {
        super(input, output);
    }

    public Purchase() {
        super();

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void run(){
        while (true) {
            String choice = (String) getChoiceFromOptions(PURCHASE_OPTION);

            if (choice.equals(PURCHASE_OPTION_FEED_MONEY)) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Please enter whole dollar amounts: ");
                int dollar = sc.nextInt();
                balance += dollar;
                System.out.println("Current money provided: " + balance);
            }
        }
    }


}
