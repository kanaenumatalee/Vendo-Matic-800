package com.techelevator;


import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;


public class PurchaseTest {
    Purchase purchase;
    Item item;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @Before
    public void setup() throws FileNotFoundException {
        System.setOut(new PrintStream(outContent));
        purchase = new Purchase();
        item = new Item();
        item.setItemInfo();
    }

    @After
    public void restore() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void returnTrue_for_enoughBalance() {
        purchase.setBalance(50);

        Assert.assertEquals("haveEnoughBalance(\"50\") should return true: "
                            + System.lineSeparator()+ "getBalance(): "  + purchase.getBalance(),
                            true, purchase.haveEnoughBalance("50"));

        Assert.assertEquals("haveEnoughBalance(\"60\") should return false: "
                            + System.lineSeparator()+"getBalance(): "  + purchase.getBalance(),
                false, purchase.haveEnoughBalance("60"));

    }

    @Test
    public void feedMoney_should_affect_balance_or_throw_error_messages() {
    // if feedMoney() increase Balance
    // if feedMoney() takes in positive as value
        String input = "10";
        purchase.feedMoney(input);
        Assert.assertEquals("feedMoney(10) should return 10.", 10, purchase.getBalance(), 0);
        outContent.reset();

   // if feedMoney() takes in 0 as value
        input = "0";
        String expected = "Please insert $1, $2, $5, or $10 only."+System.lineSeparator();
        purchase.feedMoney(input);
        Assert.assertEquals("feedMoney(0) should return " + expected,
                expected, outContent.toString());
        outContent.reset();

    // if feedMoney() takes in "" return error
        input = "";
        expected = " is not a valid integer."+System.lineSeparator();
        purchase.feedMoney(input);
        Assert.assertEquals("feedMoney(\"-23\") should return " + expected,
                            expected, outContent.toString());
        outContent.reset();

    // if feedMoney() takes in -number as value
        input = "-23";
        expected = "Please insert $1, $2, $5, or $10 only."+ System.lineSeparator();
        purchase.feedMoney(input);
        Assert.assertEquals("feedMoney(\"-23\") should return " + expected,
                            expected, outContent.toString());
        outContent.reset();

    // if feedMoney() takes in other values not listed as accepted
            input = "four";
            expected = "four is not a valid integer."+System.lineSeparator();
            purchase.feedMoney(input);
            Assert.assertEquals("feedMoney(four) should return " + expected,
                                expected, outContent.toString());
    }

    @Test
    public void getSound_should_return_sound_based_on_foodCategory() {
    //returning sound for Chip
        String input = "Chip";
        String expected = "Dispensing..."+System.lineSeparator()+
                            "Crunch Crunch, Yum!"+System.lineSeparator();
        purchase.getTypeSound(input);
        Assert.assertEquals("getTypeSound(\"Chip\") should return " + expected,
                expected, outContent.toString());
        outContent.reset();

    //returning sound for Candy
        input = "Candy";
        expected = "Dispensing..."+System.lineSeparator()+"Munch Munch, Yum!"+System.lineSeparator();
        purchase.getTypeSound(input);
        Assert.assertEquals("getTypeSound(\"Candy\") should return " + expected,
                expected, outContent.toString());
    }

    @Test
    public void reduce_quantity_of_itemQuantity_Map_value() {
        Item item = new Item();
        item.itemQuantity.put("a", 1);
        item.itemQuantity.put("b", 5);
        item.itemQuantity.put("c", 4);

        Integer expected = 0;
        purchase.reduceQuantity(item, "a");
        Assert.assertEquals("purchase.reduceQuantity(item, \"a\") should return 0.",
                            expected, item.itemQuantity.get("a"));

        expected = 4;
        purchase.reduceQuantity(item, "b");
        Assert.assertEquals("purchase.reduceQuantity(item, \"b\") should return " + expected,
                            expected, item.itemQuantity.get("b"));
    }
    
    @Test
    public void return_message_when_selecting_item() throws FileNotFoundException {
        purchase.setBalance(5);
        //purchasing A1 with enough balance
        String input = "A1";
        String expected = "Thank you for ordering Potato Crisps! That will be $3.05!"
                        + System.lineSeparator()+"Dispensing..."
                        + System.lineSeparator()+"Crunch Crunch, Yum!"
                        + System.lineSeparator()+"Money remaining: $1.95"+System.lineSeparator();
        purchase.selectItem(item, input);
        Assert.assertEquals("It should return " + expected,
                expected, outContent.toString());
        outContent.reset();
        //try to select item when balance is 0
        purchase.setBalance(0);
        input = "A1";
        expected = "Your current balance $0.00 is not enough to buy this item. Please add more money or select another item."+System.lineSeparator();
        purchase.selectItem(item, input);
        Assert.assertEquals("It should return " + expected,
                expected, outContent.toString());
        outContent.reset();
        //try to select SOLD OUT item
        Item item = new Item();
        item.itemQuantity.put("A1", 0);
        purchase.setBalance(5);
        input = "A1";
        expected = "Sorry, the item is SOLD OUT."+System.lineSeparator();
        purchase.selectItem(item, input);
        Assert.assertEquals("It should return " + expected,
                expected, outContent.toString());
        outContent.reset();
    }

    @Test
    public void return_TotalSalesAmount() {
        item.itemSalesCount.put("A1", 5);
        Double expected = 15.25;
        Assert.assertEquals("It should return " + expected, expected, purchase.returnTotalSales(item), 0);
    }

    @Test
    public void return_change() {
        String expected = "Dispensing change..."
                + System.lineSeparator()+"Quarter: 40"
                + System.lineSeparator()+"Dime: 0"
                + System.lineSeparator()+"Nickle: 0"
                + System.lineSeparator()+"Remaining money: $0.00"+System.lineSeparator();
        purchase.returnChange(10);
        Assert.assertEquals("It should return " + expected,
                expected, outContent.toString());
        outContent.reset();
    }
}
