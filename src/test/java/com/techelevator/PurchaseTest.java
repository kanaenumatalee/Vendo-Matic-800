package com.techelevator;

import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;


public class PurchaseTest {
    Purchase purchase;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        purchase = new Purchase();
    }

    @After
    public void restore() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    @Test
    public void returnTrue_for_enoughBalance() {
        purchase.setBalance(50);

        Assert.assertEquals("haveEnoughBalance(\"50\") should return true: "
                            + "\ngetBalance(): "  + purchase.getBalance(),
                            true, purchase.haveEnoughBalance("50"));

        Assert.assertEquals("haveEnoughBalance(\"60\") should return false: "
                            + "\ngetBalance(): "  + purchase.getBalance(),
                false, purchase.haveEnoughBalance("60"));
    }

    @Test
    public void feedMoney_should_affect_balance() {
        // if feedMoney() increase Balance
        // if feedMoney() takes in positive as value
        String input = "10";
        purchase.feedMoney(input);
        Assert.assertEquals(10, purchase.getBalance(),0);
        // if feedMoney() takes in 0 as value
        input = "0";
        String expected = "Current money provided: $10.00\nPlease insert $1, $2, $5, or $10 only.\n";
        purchase.feedMoney(input);
        Assert.assertEquals(expected, outContent.toString());
    // if feedMoney() takes in "" return error
        input = "";
        expected = "Current money provided: $10.00\nPlease insert $1, $2, $5, or $10 only.\n is not a valid integer.\n";
        purchase.feedMoney(input);
        Assert.assertEquals(expected, outContent.toString());
    // if feedMoney() takes in -number as value
        input = "-23";
        purchase.feedMoney(input);
        Assert.assertEquals(expected, outContent.toString());
    // if feedMoney() takes in other values not listed as accepted
        input = "four";
        purchase.feedMoney(input);
        Assert.assertEquals(expected, outContent.toString());
    }



    @Test
    public void getSound_for_Chips() {
        System.out.print("Dispensing...\nCrunch Crunch, Yum!");

    }

    @Test
    public void err() {
        System.err.print("hello again");
        Assert.assertEquals("hello", outContent.toString());
    }

    @Test
    public void reduce_Quantity() {

    }

    @Test
    public void return_TotalSalesAmount() {

    }

    @Test
    public void return_change() {

    }
}
